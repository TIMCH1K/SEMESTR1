
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
        <img src="img/Grisvo_logo.png" class="header__logo">
    </div>
    <h1 class="header__signup">Sign In</h1>
    <div class="header__square-icon"></div>
</header>
<main>
        <form class="main__form-container" action="/JavaEETest_war/Authorization" method="post">
            <input type="text" name="nickname" required placeholder="Your nickname" class="main__input">
            <input type="password" name="password" required placeholder="Password" class="main__input">
            <div class="main__buttons">
                <button class="main__button" type="submit">CONFIRM</button>
                <p class="main__text">Don't have an account?</p>
                <a href="SignUp.jsp" class="main__redirect__sign-in">SIGN UP</a>
            </div>
        </form>

    </main>
<footer>

</footer>

</body>
</html>
