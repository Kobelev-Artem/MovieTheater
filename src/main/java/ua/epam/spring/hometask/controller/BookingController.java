package ua.epam.spring.hometask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.Ticket;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.EventService;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static ua.epam.spring.hometask.constants.WebConstants.TOTAL_PRICE_SESSION_PARAM;
import static ua.epam.spring.hometask.constants.WebConstants.USER_SESSION_PARAM;

@RequestMapping("/tickets")
@Controller
public class BookingController {

    @Resource
    private BookingService bookingService;

    @Resource
    private EventService eventService;

    @GetMapping
    public String ticketsPage(){
        return "tickets";
    }

    @PostMapping
    public String bookTicket(Model model, @SessionAttribute(value = USER_SESSION_PARAM) User user, @RequestParam String eventName, @RequestParam long seat){
        Event event = eventService.getByName(eventName);

        LocalDateTime eventDateTime = event.getAirDates().first();
        Ticket ticket = new Ticket(user, event, eventDateTime, seat);
        bookingService.bookTickets(Set.of(ticket), user);

        model.addAttribute(TOTAL_PRICE_SESSION_PARAM, bookingService.getTicketsPrice(List.of(ticket), user));

        return "myAccount";
    }

}
