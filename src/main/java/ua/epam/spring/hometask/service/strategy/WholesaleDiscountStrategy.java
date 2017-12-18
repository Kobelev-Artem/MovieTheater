package ua.epam.spring.hometask.service.strategy;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

public class WholesaleDiscountStrategy implements DiscountStrategy{

    @Override
    public double calculateDiscount(final @Nullable User user, final @Nonnull Event event, final @Nonnull LocalDateTime airDateTime, final long numberOfTickets) {
        long countOfTickets = numberOfTickets;
        if (!user.isAnonymous()){
            countOfTickets += user.getTickets().size() % 10;
        }

        long numberOfTicketsWithDiscount = countOfTickets / 10;

        return (numberOfTicketsWithDiscount * WHOLESALE_DISCOUNT * 10) / (double)countOfTickets;
    }
}
