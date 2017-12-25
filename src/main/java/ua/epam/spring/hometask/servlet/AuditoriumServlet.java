package ua.epam.spring.hometask.servlet;

import org.springframework.context.ApplicationContext;
import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.service.AuditoriumService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class AuditoriumServlet extends HttpServlet {

    private AuditoriumService auditoriumService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<Auditorium> auditoriums = auditoriumService.getAll();
        req.setAttribute("auditoriums", auditoriums);

        req.getRequestDispatcher("auditoriums.jsp").forward(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        ApplicationContext ac = (ApplicationContext) config.getServletContext().getAttribute("applicationContext");

        this.auditoriumService = (AuditoriumService)ac.getBean("auditoriumService");
    }
}
