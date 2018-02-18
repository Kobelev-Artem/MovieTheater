package ua.epam.spring.hometask.servlet;

import static ua.epam.spring.hometask.constants.WebConstants.AUDITORIUM_PAGE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.service.AuditoriumService;

public class AuditoriumServlet extends HttpServlet {

    @Autowired
    private AuditoriumService auditoriumService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<Auditorium> auditoriums = auditoriumService.getAll();
        req.setAttribute("auditoriums", auditoriums);

        req.getRequestDispatcher(AUDITORIUM_PAGE).forward(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
}
