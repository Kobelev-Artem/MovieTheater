package ua.epam.spring.hometask.filter;

import static ua.epam.spring.hometask.constants.WebConstants.USER_SESSION_PARAM;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (userLoggedIn(request)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect("/");
        }
    }

    private boolean userLoggedIn(HttpServletRequest request){
        HttpSession session = request.getSession(false);

        return session != null && session.getAttribute(USER_SESSION_PARAM) != null;
    }

    @Override
    public void destroy() {

    }
}
