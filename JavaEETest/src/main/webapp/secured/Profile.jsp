<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Profile</title>
    <link rel="stylesheet" href="css/sign-up.css?v=1.0">
</head>
<body>
<header class="header-container">
    <div class="header__icon-container">
        <img src="img/Grisvo_logo.png" class="header__logo">
    </div>
    <div class="header__square-icon" style="background-image: url('${forumUserData.userImage}');"></div>
    <h1 class="header__signup">Profile</h1>
</header>
<main>
    <dev class = "main__form-container">
        <h1>nickname: <%=request.getAttribute("userNickname")%></h1>
        <h1>email: <%=request.getAttribute("userEmail")%></h1>
    </dev>
    <%--<form class="main__form-container" action="/JavaEETest_war/Profile_Registrate" method="post">
        <c:choose>
            <c:when test="${not empty forumUserData}">
                <input type="text" name="forum_nickname" required placeholder="Your forum nickname" value="${forumUserData.forumNickname}" class="main__input">
                <input type="number" name="age" required placeholder="Age" value="${forumUserData.age}" class="main__input">
                <input type="text" name="country" required placeholder="Country" value="${forumUserData.country}" class="main__input">
                <input type="text" name="city" required placeholder="City" value="${forumUserData.city}" class="main__input">
            </c:when>
            <c:otherwise>
                <input type="text" name="forum_nickname" required placeholder="Your forum nickname" class="main__input">
                <input type="number" name="age" required placeholder="Age" class="main__input">
                <input type="text" name="country" required placeholder="Country" class="main__input">
                <input type="text" name="city" required placeholder="City" class="main__input">
            </c:otherwise>
        </c:choose>
        <div>
        <button class="main__button" type="submit">Confirm</button>
        </div>
    </form>--%>
    <form class="image-upload-form" action="/JavaEETest_war/UploadImage" method="post" enctype="multipart/form-data">
        <label for="userImage">Upload your profile image:</label>
        <input type="file" name="userImage" id="userImage" required>
        <button type="submit">Upload Image</button>
    </form>
</main>
<footer>
</footer>
</body>
</html>
