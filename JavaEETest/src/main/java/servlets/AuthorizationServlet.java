package servlets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.UUID;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dto.SignInForm;
import repository.impl.UsersRepositoryJDBCImpl;
import services.SignInService;
import services.impl.SignInServiceImpl;

@WebServlet("/Authorization")
public class AuthorizationServlet extends HttpServlet {
    private UsersRepositoryJDBCImpl usersRepositoryJDBC;

    @Override
    public void init() throws ServletException {
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        HikariConfig config = new HikariConfig("/hikari.properties");
        DataSource dataSource = new HikariDataSource(config);
        usersRepositoryJDBC = new UsersRepositoryJDBCImpl(dataSource);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String nickname = request.getParameter("nickname");
        String password = request.getParameter("password");
        SignInForm form = SignInForm.builder()
                .nickname(nickname)
                .password(password)
                .build();
        SignInService service = new SignInServiceImpl(usersRepositoryJDBC);
        UUID userId = service.singIn(form);
        if (userId != null) {
            request.getSession().setAttribute("authenticatedUser", userId);
            response.sendRedirect(request.getContextPath() + "/profile");
        } else {
            request.setAttribute("errorMessage", "Invalid nickname or password");
            request.getRequestDispatcher("ErrorPage.jsp").forward(request, response);
        }
    }
}
