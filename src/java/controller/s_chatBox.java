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
                courseData.append("Course: ").append(course.getTitle())
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
            textPart.addProperty("text",
                    "Hãy trả lời bằng tiếng Việt. Nếu có thể, hãy dùng ngôn ngữ tự nhiên và dễ hiểu nhất.\n\n "
                            + " trả lời dựa trên dữ liệu trong database và chia thành các ý gạch đầu dòng và có thể tìm kiếm thêm thông tin ngoài nếu hỏi liên quan đến các course trong database "
                            + "kèm theo thông tin về course  trong database và trả lời là chúng tôi có course:"
                            + ". Nếu không có liên quan đến dữ liệu trong database , "
                            + "hãy trả lời: 'Vui lòng hỏi câu liên quan đến danh sách course.\n" +
                            "Database Course:\n" + courseData.toString() +
                            "\nUser Question: " + userMessage);
            
            parts.add(textPart);
            contentObj.add("parts", parts);
            contents.add(contentObj);
            requestBody.add("contents", contents);
            
            URL url = new URL(API_URL + "?key=" + API_KEY);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = requestBody.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
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
        }   catch (SQLException ex) {
            Logger.getLogger(s_chatBox.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
