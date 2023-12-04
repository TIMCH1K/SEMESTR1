package servlets;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import models.Post;
import models.User;
import repository.impl.PostRepositoryJDBCImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@WebServlet("/CreatePostServlet")
public class CreatePostServlet extends HttpServlet {
    PostRepositoryJDBCImpl postRepositoryJDBC;

    @Override
    public void init() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        HikariConfig config = new HikariConfig("/hikari.properties");
        HikariDataSource dataSource = new HikariDataSource(config);
        try {
            postRepositoryJDBC = new PostRepositoryJDBCImpl(dataSource.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.print(123123);
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        UUID userId = (UUID) request.getSession().getAttribute("authenticatedUser");
        User user = User.builder()
                .id(userId)
                .build();
        Post post = Post.builder()
                .title(title)
                .description(description)
                .user(user)
                .build();
        try {
            postRepositoryJDBC.save(post);
            response.sendRedirect(request.getContextPath() + "/secured/ForumPage.jsp");
        } catch (SQLException e) {
            throw new ServletException("Error creating post", e);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Post> posts = postRepositoryJDBC.getSome(5);
            request.setAttribute("posts", posts);
            request.getRequestDispatcher("/secured/ForumPage.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error retrieving posts", e);
        }
    }

}
