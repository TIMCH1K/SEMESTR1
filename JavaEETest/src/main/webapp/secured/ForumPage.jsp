<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Forum Topics</title>
    <link rel="stylesheet" type="text/css" href="css/forum_page.css?v=1.0">
</head>
<body>
<header class="header-container">
    <div class="header__icon-container">
        <img src="img/Grisvo_logo.png" alt="Grisvo Logo" class="header__logo">
    </div>
    <h1 class="header__ForumTopics">Forum Topics</h1>
</header>
<main>
    <section class="topics-container">
        <c:forEach var="post" items="${posts}">
            <article class="topic">
                <h2 class="topic__title">${post.title}</h2>
                <p class="topic__author">By: ${post.user.username}</p>
                <p class="topic__snippet">${post.description}</p>
            </article>
        </c:forEach>
        <div  class="main__post-container">
            <form action="${pageContext.request.contextPath}/CreatePostServlet" method="post">
                <input type="text" name="title" placeholder="Title" required>
                <textarea name="description" placeholder="Description" required></textarea>
                <input type="submit" value="save"/>
            </form>
        </div>
        <article class="topic" onclick="window.location='Rules.jsp';">
        <h2 class="topic__title">Правила форума Grisvo</h2>
        <p class="topic__author">By: Admin</p>
        <p class="topic__snippet">Нажмите чтобы прочитать.</p>
        </article>
        <article class="topic" onclick="window.location='Perhot_Topic.jsp';">
            <h2 class="topic__title">Как избавиться от перхоти раз и навсегда?</h2>
            <p class="topic__author">By: Macho_man</p>
            <p class="topic__snippet">ребят, нашёл крутой лайфхак, как выкинуть перхоть из своей жизни нафиг, и это прям вау эффект. если задрало, что твоя голова ведет себя подозрительно и сыпет как будто на улице идет снег, оставайтесь на связи, ща буду сплетничать про секретный метод. 🌨️🚫 #БезПерхоти #СкальпГлоуАп</p>
        </article>
        <article class="topic" onclick="window.location='Selling_Garage.jsp';">
            <h2 class="topic__title">Продам гараж.</h2>
            <p class="topic__author">By: Podaawd</p>
            <p class="topic__snippet">Гараж вместительный, не воняет. Находится в городе Омск...</p>
        </article>
    </section>
</main>
</body>
</html>
