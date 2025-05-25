function toggleChat() {
    let chatContainer = document.getElementById("chatContainer");
    chatContainer.style.display = (chatContainer.style.display === "none" || chatContainer.style.display === "") ? "block" : "none";
}

async function sendMessage() {
    let userInput = document.getElementById("userInput").value.trim();
    if (userInput === "") return;
    let databaseScript = `   `;

    let requestData = {
        message: userInput,
        database: databaseScript,
        instruction: "chỉ trả lời câu hỏi liên quan đến tiếng nhật và dữ liệu có trong database.\n\
nếu không nằm trong dữ liệu tiếng nhật hoặc database thì trả lời 'vui lòng hỏi liên quan đến tiếng nhật hoặc các khóa học của chúng tôi ' "
    };

    let chatBox = document.getElementById("chatBox");
    chatBox.innerHTML += `<div><b>Bạn:</b> ${userInput}</div>`;
    document.getElementById("userInput").value = "";

    try {
        let response = await fetch("/SWP/aiGe", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(requestData)
        });

        let data = await response.json();
        let botResponse = data.response || "Không có phản hồi từ AI.";
        chatBox.innerHTML += `<div><b>AI:</b> ${botResponse}</div>`;
    } catch (error) {
        console.error("Lỗi kết nối AI:", error);
        chatBox.innerHTML += `<div style="color:red;"><b>Lỗi:</b> Không thể kết nối AI!</div>`;
    }

    chatBox.scrollTop = chatBox.scrollHeight;
}

function handleKeyPress(event) {
    if (event.key === "Enter") {
        event.preventDefault();
        sendMessage();
    }
}