package ua.epam.spring.hometask.service.strategy;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;

public interface DiscountStrategy {

    double BIRTHDAY_DISCOUNT = 10d;
    double WHOLESALE_DISCOUNT = 5d;

    double calculateDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets);
}
