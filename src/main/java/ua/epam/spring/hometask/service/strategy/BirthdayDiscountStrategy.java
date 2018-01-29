package ua.epam.spring.hometask.service.strategy;

import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class BirthdayDiscountStrategy implements DiscountStrategy{

    private static final long DAYS_RANGE = 6l;

    @Override
    public double calculateDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets){
        if (!user.isAnonymous() && null != user.getBirthday()){
            LocalDate eventDay = LocalDate.of(airDateTime.getYear(), airDateTime.getMonth(), airDateTime.getDayOfMonth());
            LocalDate adjustedBirthday = user.getBirthday().withYear(eventDay.getYear());
            adjustYearInAboutNewYear(eventDay, adjustedBirthday);

            if (eventDay.plusDays(DAYS_RANGE).isAfter(adjustedBirthday) &&
                    eventDay.minusDays(DAYS_RANGE).isBefore(adjustedBirthday) ){
                return BIRTHDAY_DISCOUNT;
            }
        }
        return 0d;
    }

    private void adjustYearInAboutNewYear(final LocalDate today, final LocalDate adjustedBirthday) {
        if (Month.DECEMBER.equals(adjustedBirthday.getMonth()) && Month.JANUARY.equals(today.getMonth())){
            adjustedBirthday.minusYears(1);
        }
        if (Month.JANUARY.equals(adjustedBirthday.getMonth()) && Month.DECEMBER.equals(today.getMonth())){
            adjustedBirthday.plusYears(1);
        }
    }
}
