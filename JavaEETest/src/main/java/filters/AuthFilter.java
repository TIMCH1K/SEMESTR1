package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/secured/*")
public class AuthFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if (request.getSession().getAttribute("authenticatedUser") == null) {
            response.sendRedirect(request.getContextPath() + "/SignIn.jsp");
        } else {
            chain.doFilter(req, res);
        }
    }

    public void destroy() {

    }
}
