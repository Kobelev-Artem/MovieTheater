package ua.epam.spring.hometask.servlet;

import static ua.epam.spring.hometask.constants.WebConstants.HOME_PAGE;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomePageServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(HOME_PAGE).forward(request, response);
    }
}
