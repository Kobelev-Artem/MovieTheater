package ua.epam.spring.hometask.servlet;

import static ua.epam.spring.hometask.constants.WebConstants.USER_SESSION_PARAM;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.UserService;

public class LoginServlet extends HttpServlet {

    private static final String USER_EMAIL_PARAM = "userEmail";
    private static final String PASSWORD_PARAM = "password";

    @Autowired
    private UserService userService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = request.getParameter(USER_EMAIL_PARAM);
        String password = request.getParameter(PASSWORD_PARAM);
        Map<String, String> messages = validateInputAndGetErrors(userEmail, password);

        if (messages.isEmpty()) {
            User user = userService.getUserByEmail(userEmail);

            if (user != null && user.getPassword().equals(password)) {
                request.getSession().setAttribute(USER_SESSION_PARAM, user);
            } else {
                messages.put("login", "Unknown login, please try again");
            }
        }

        request.setAttribute("messages", messages);
        response.sendRedirect("/");
    }

    private Map<String, String> validateInputAndGetErrors(String userEmail, String password) {
        Map<String, String> messages = new HashMap<>();

        if (userEmail == null || userEmail.isEmpty()) {
            messages.put(USER_EMAIL_PARAM, "Please enter userEmail");
        }

        if (password == null || password.isEmpty()) {
            messages.put(PASSWORD_PARAM, "Please enter password");
        }

        return messages;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

}
