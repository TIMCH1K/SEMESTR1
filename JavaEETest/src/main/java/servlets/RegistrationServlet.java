package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.sql.DataSource;
import java.util.UUID;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dto.SignUpForm;
import repository.impl.UsersRepositoryJDBCImpl;
import services.SignUpService;
import services.impl.SignUpServiceImpl;
import util.Validation;


@WebServlet("/Registration")
public class RegistrationServlet extends HttpServlet {
    private UsersRepositoryJDBCImpl usersRepositoryJDBC;

    @Override
    public void init() {
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
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (Validation.isValid(nickname, email, password)) {
            SignUpService signUpService = new SignUpServiceImpl(usersRepositoryJDBC);
            SignUpForm form = SignUpForm.builder()
                    .nickname(nickname)
                    .email(email)
                    .password(password)
                    .build();
            UUID id = signUpService.singUp(form);
            if (id != null){
                request.getSession().setAttribute("authenticatedUser", id);
                response.sendRedirect(request.getContextPath() + "/SignIn.jsp");
            } else {
                request.setAttribute("errorMessage", "Упс, бекенд дурак! что-то пошло не так");
                request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage",
                    "Введенные данные недействительны. Проверьте их и повторите попытку.");
            request.getRequestDispatcher("/ErrorPage.jsp").forward(request, response);
        }
    }
}
