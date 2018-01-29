package ua.epam.spring.hometask.servlet;

import static ua.epam.spring.hometask.constants.WebConstants.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.BookingService;


public class TicketServlet extends HttpServlet {

    @Autowired
    private EventDao eventDao;

    @Autowired
    private BookingService bookingService;

    /** 😆
     * shows all user's tickets
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(TICKETS_PAGE).forward(request, response);
    }

    /**
     * creates ticket and adds it to the user
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User currentUser = (User)request.getSession().getAttribute(USER_SESSION_PARAM);
        String eventName = request.getParameter("eventName");
        Event event = eventDao.getByName(eventName);
        LocalDateTime eventDateTime = event.getAirDates().first();
        long seat = Long.valueOf(request.getParameter("seat"));

        Ticket ticket = new Ticket(currentUser, event, eventDateTime, seat);

        bookingService.bookTickets(Set.of(ticket), currentUser);
        request.getSession().setAttribute(TICKET_SESSION_PARAM, ticket);
        request.getSession().setAttribute(TOTAL_PRICE_SESSION_PARAM, bookingService.getTicketsPrice(List.of(ticket), currentUser));

        request.getRequestDispatcher(CHECKOUT_PAGE).forward(request, response);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
}
