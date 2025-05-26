<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div class="form-box register">
    <form action="${pageContext.request.contextPath}/login" method="post">
        <input type="hidden" name="action" value="signup">
        <h1>Registration</h1>

        <div class="input-box">
            <input type="email" name="email" placeholder="Email" 
                   value="<c:out value='${email}' default='' />" required>
            <i class='bx bxs-user'></i>
        </div>
            
        <div class="input-box">
        <input type="password" name="password" placeholder="Password" required minlength="8">
            <i class='bx bxs-envelope'></i>
        </div>

        <div class="input-box">
            <input type="password" name="repass" placeholder="Cormfirm Password" required>
            <i class='bx bxs-lock-alt'></i>
        </div>

        <button type="submit" class="btn">Register</button>
        
        <div class="social-icons" style="margin-top: 10px">
            <a href="#"><i class='bx bxl-google'></i></a>
            <a href="#"><i class='bx bxl-facebook'></i></a>
            <a href="#"><i class='bx bxl-github'></i></a>
            <a href="#"><i class='bx bxl-linkedin'></i></a>
        </div>

        <c:if test="${not empty message_signup}">
            <p class="error-message" style="color: red">${message_signup}</p>
        </c:if>
    </form>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function () {
        var showRegisterForm = "${registerActive}";
        if (showRegisterForm.trim() === "active") {
            document.querySelector(".container").classList.add("active");
        }
    });
</script>
