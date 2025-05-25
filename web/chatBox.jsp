<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Chat với AI</title>
    
    <!-- Font đẹp -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
    
    <!-- CSS chat box -->
    <link rel="stylesheet" href="css/stylechat.css">
    
    <!-- JS chat box -->
    <script src="js/chatbox.js"></script>
</head>

<body>

    <section id="chatbox">
        <div id="chatButton" onclick="toggleChat()">
            💬 Hỗ trợ thông tin
        </div>

        <!-- Hộp chat ẩn ban đầu -->
        <div id="chatContainer">
            <div id="chatHeader">
                <span>Chat với AI</span>
                <button onclick="toggleChat()">✖</button>
            </div>
            <div id="chatBox"></div>
            <div id="butt">
                <input type="text" id="userInput" placeholder="Nhập tin nhắn..." onkeypress="handleKeyPress(event)" />
                <button id="buttonChat" onclick="sendMessage()">Gửi</button>
            </div>
        </div>
    </section>

</body>
</html>
