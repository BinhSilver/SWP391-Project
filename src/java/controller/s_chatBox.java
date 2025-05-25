package controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dao.CoursesDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Course;

@WebServlet(name = "s_chatBox", urlPatterns = {"/aiGe"})
public class s_chatBox extends HttpServlet {

    private static final String API_URL = "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-pro:generateContent";
    private static final String API_KEY = "AIzaSyAJ7VXvuofd4DUQKo93hg7WLNrLZY6JApU";
    private static final CoursesDAO coursedao = new CoursesDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
response.setCharacterEncoding("UTF-8");
response.setContentType("application/json; charset=UTF-8");
        try {
            BufferedReader reader = request.getReader();
            StringBuilder requestData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                requestData.append(line);
            }

            JsonObject requestJson = JsonParser.parseString(requestData.toString()).getAsJsonObject();
            String userMessage = requestJson.has("message") ? requestJson.get("message").getAsString() : "";

            if (userMessage.trim().isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("{\"error\": \"Message cannot be empty\"}");
                return;
            }

            List<Course> allCourses = coursedao.getAllCoursesforchatbox();
            StringBuilder courseData = new StringBuilder();
            for (Course course : allCourses) {
                courseData.append("Course: ").append(course.getCourseID())
                        .append(", Title: ").append(course.getTitle())
                        .append(", Description: ").append(course.getDescription())
                        .append("\n");
            }

            if (courseData.length() == 0) {
                response.getWriter().write("{\"response\": \"Vui lòng hỏi câu liên quan đến danh sách course .\"}");
                return;
            }

           JsonObject requestBody = new JsonObject();
JsonArray contents = new JsonArray();
JsonObject contentObj = new JsonObject();
JsonArray parts = new JsonArray();

JsonObject textPart = new JsonObject();
textPart.addProperty("text",  "Trả lời bằng tiếng Việt rõ ràng và dễ hiểu.\n\n"
    + "Chỉ trả lời nếu câu hỏi liên quan đến các khóa học tiếng Nhật trong danh sách dưới đây, hoặc liên quan đến kiến thức về tiếng Nhật (từ vựng, ngữ pháp, học tiếng).\n"
    + "Nếu không liên quan đến tiếng nhật, hãy trả lời: 'Vui lòng hỏi liên quan đến tiếng Nhật hoặc các khóa học của chúng tôi.'\n\n"
    + "Dữ liệu khóa học:\n" + courseData.toString());
parts.add(textPart);
contentObj.add("parts", parts);
contents.add(contentObj);
requestBody.add("contents", contents);
            System.out.println(textPart.toString());
            URL url = new URL(API_URL + "?key=" + API_KEY);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

// Gửi dữ liệu bằng OutputStreamWriter thay vì getBytes
            try (OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), StandardCharsets.UTF_8)) {
                writer.write(requestBody.toString());
                writer.flush();
            }

            int status = conn.getResponseCode();
            InputStream inputStream = (status < 400) ? conn.getInputStream() : conn.getErrorStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));

            StringBuilder responseStr = new StringBuilder();
            while ((line = br.readLine()) != null) {
                responseStr.append(line.trim());
            }

            String aiResponseText = "Vui lòng hỏi câu liên quan đến danh sách course .";
            try {
                JsonObject jsonResponse = JsonParser.parseString(responseStr.toString()).getAsJsonObject();
                if (jsonResponse.has("candidates")) {
                    JsonArray candidates = jsonResponse.getAsJsonArray("candidates");
                    if (!candidates.isEmpty()) {
                        JsonObject firstCandidate = candidates.get(0).getAsJsonObject();
                        JsonObject content = firstCandidate.getAsJsonObject("content");
                        JsonArray partsArray = content.getAsJsonArray("parts");
                        if (!partsArray.isEmpty() && partsArray.get(0).getAsJsonObject().has("text")) {
                            aiResponseText = partsArray.get(0).getAsJsonObject().get("text").getAsString();
                        }
                    }
                }
            } catch (Exception e) {
                aiResponseText = "Lỗi xử lý phản hồi từ AI.";
            }

            JsonObject clientResponse = new JsonObject();
            clientResponse.addProperty("response", aiResponseText.replace("**", "<br>"));

            response.setContentType("application/json");
            response.getWriter().write(clientResponse.toString());
        } catch (SQLException ex) {
            Logger.getLogger(s_chatBox.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
