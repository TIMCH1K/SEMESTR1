
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome to Grisvo Forum</title>
    <link rel="stylesheet" href="css/sign-up.css?v=1.0">
</head>
<body>
<header class="header-container">
    <div class="header__icon-container">
        <img src="secured/img/Grisvo_logo.png" class="header__logo">
    </div>
    <h1 class="header__signup">Sign In</h1>
    <div class="header__square-icon"></div>
</header>
<main>
    <div class="header-container"   >
        <p class="input__error-message">Произошла ащибка</p>
        <p class="input__error-message"><%=request.getAttribute("errorMessage")%></p>
    </div>
    <form class="main__form-container">
        <a href="SignUp.jsp" class="main__redirect__sign-in">SIGN UP</a>
    </form>

</main>
<footer>

</footer>

</body>
</html>
