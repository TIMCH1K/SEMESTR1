package servlets;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import models.User;
import repository.UsersRepository;
import repository.impl.UsersRepositoryJDBCImpl;
import services.UserFindByIdService;
import services.impl.UserFindByIdServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private UsersRepository usersRepository;
    private UserFindByIdService userFindByIdService;

    @Override
    public void init() throws ServletException {
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        HikariConfig config = new HikariConfig("/hikari.properties");
        HikariDataSource dataSource = new HikariDataSource(config);
        usersRepository = new UsersRepositoryJDBCImpl(dataSource);
        userFindByIdService = new UserFindByIdServiceImpl(usersRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UUID id = (UUID) session.getAttribute("authenticatedUser");
        Optional<User> userOptional = userFindByIdService.find(id);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            request.setAttribute("userNickname", user.getNickname());
            request.setAttribute("userEmail", user.getEmail());
            request.getRequestDispatcher( "secured/Profile.jsp")
                    .forward(request,response);
        }
    }
}