package ua.epam.spring.hometask.service.strategy;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BirthdayDiscountStrategy implements DiscountStrategy{

    @Override
    public double calculateDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets){
        if (!user.isAnonymous() && null != user.getBirthday()){
            if ( LocalDate.now().plusDays(6).isAfter(user.getBirthday()) &&
                    LocalDate.now().minusDays(6).isBefore(user.getBirthday()) ){
                return 10d;
            }
        }
        return 0d;
    }
}
