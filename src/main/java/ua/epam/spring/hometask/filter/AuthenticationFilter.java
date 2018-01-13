package ua.epam.spring.hometask.filter;

import static ua.epam.spring.hometask.constants.WebConstants.USER_SESSION_PARAM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.UserService;
import ua.epam.spring.hometask.util.enums.UserRole;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticationFilter implements Filter {

    @Autowired
    private UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (userHasAdminRole(request)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.sendRedirect("/");
        }
    }

    private boolean userHasAdminRole(HttpServletRequest request){
        HttpSession session = request.getSession(false);

        if(session != null && session.getAttribute(USER_SESSION_PARAM) != null){
            User user = (User)session.getAttribute(USER_SESSION_PARAM);
            return userService.hasRole(user, UserRole.ADMIN);
        }
        return false;
    }

    @Override
    public void destroy() {

    }
}
