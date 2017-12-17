package ua.epam.spring.hometask.service.impl;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import ua.epam.spring.hometask.domain.*;
import ua.epam.spring.hometask.service.AuditoriumService;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.util.ecxeption.AuditoriumNotFoundForEventOnTime;
import ua.epam.spring.hometask.util.ecxeption.EventNotFoundOnTime;

public class BookingServiceImpl implements BookingService {

    @Resource
    private AuditoriumService auditoriumService;

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) {
        if (!event.getAirDates().contains(dateTime)){
            throw new EventNotFoundOnTime(event, dateTime);
        }
        if (event.getAuditoriums().keySet().contains(dateTime)){
            throw new AuditoriumNotFoundForEventOnTime(event, dateTime);
        }
        double basePrice = event.getBasePrice();
        if (null != event.getRating() && EventRating.HIGH.equals(event.getRating())){
            basePrice = basePrice * 1.2;
        }
        //TODO: add multiply for weekends
        //TODO: add discount for user
        Auditorium auditorium = event.getAuditoriums().get(dateTime);
        long vipSeatsCount = seats.stream().filter(s -> auditorium.getVipSeats().contains(s)).count();

        //set twice price for VIP seats
        return basePrice * (seats.size() + vipSeatsCount);
    }

    //TODO get current user from session
    public void bookTickets(@Nonnull Set<Ticket> tickets, @Nonnull User user){
        for (Ticket ticket : tickets){
            bookTicket(ticket, user);
        }
    }

    public void bookTicket(@Nonnull Ticket ticket, @Nonnull User user){
        ticket.getEvent().addPurchasedTicket(ticket);
        if (!user.isAnonymous()){
            user.addTicket(ticket);
        }
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime){
        return event.getPurchasedTickets().stream().filter(t -> t.getDateTime().equals(dateTime)).collect(Collectors.toSet());
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets) {

    }
}
