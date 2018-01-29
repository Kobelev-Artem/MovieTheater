package ua.epam.spring.hometask.service.impl;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ua.epam.spring.hometask.domain.*;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.DiscountService;
import ua.epam.spring.hometask.util.ecxeption.AuditoriumNotFoundForEventOnTime;
import ua.epam.spring.hometask.util.ecxeption.EventNotFoundOnTime;

public class BookingServiceImpl implements BookingService {

    public static final double HIGH_RATING_FACTOR = 1.2;
    public static final double WEEKEND_FACTOR = 1.3;

    @Resource
    private DiscountService discountService;

    @Override
    public double getTicketsPrice(@Nonnull List<Ticket> tickets, @Nullable User user){
        double totalPrice = 0;
        for (Ticket ticket : tickets){
            totalPrice += getTicketsPrice(ticket.getEvent(), ticket.getDateTime(), user, Set.of(ticket.getSeat()));
        }
        return totalPrice;
    }

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) {
        checkCorrectDateAndAuditorium(event, dateTime);

        double price = event.getBasePrice();
        price = multiplyPriceForHighRatedMovies(event, price);
        price = multiplyPriceForWeekend(dateTime, price);
        price = multiplyPriceForVIPSeats(event, dateTime, seats, price);
        price = applyDiscountToPrice(event, dateTime, user, seats, price);

        return price;
    }

    private double applyDiscountToPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats, double price) {
        return price * (1 - discountService.getDiscount(user, event, dateTime, seats.size()) / 100);
    }

    private double multiplyPriceForVIPSeats(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nonnull Set<Long> seats, double price) {
        Auditorium auditorium = event.getAuditoriums().get(dateTime);
        long vipSeatsCount = seats.stream().filter(s -> auditorium.getVipSeats().contains(s)).count();
        return price * (seats.size() + vipSeatsCount);
    }

    private double multiplyPriceForWeekend(@Nonnull LocalDateTime dateTime, double basePrice) {
        if (DayOfWeek.SATURDAY.equals(dateTime.getDayOfWeek()) || DayOfWeek.SUNDAY.equals(dateTime.getDayOfWeek())){
            basePrice *= WEEKEND_FACTOR;
        }
        return basePrice;
    }

    private double multiplyPriceForHighRatedMovies(@Nonnull Event event, double basePrice) {
        if (null != event.getRating() && EventRating.HIGH.equals(event.getRating())){
            basePrice *= HIGH_RATING_FACTOR;
        }
        return basePrice;
    }

    private void checkCorrectDateAndAuditorium(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        if (!event.getAirDates().contains(dateTime)){
            throw new EventNotFoundOnTime(event, dateTime);
        }
        if (!event.getAuditoriums().keySet().contains(dateTime)){
            throw new AuditoriumNotFoundForEventOnTime(event, dateTime);
        }
    }

    //TODO get current user from session
    @Override
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
}
