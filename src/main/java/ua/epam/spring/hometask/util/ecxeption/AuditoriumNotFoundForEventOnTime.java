package ua.epam.spring.hometask.util.ecxeption;

import ua.epam.spring.hometask.domain.Event;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;

public class AuditoriumNotFoundForEventOnTime extends IllegalArgumentException{

    public AuditoriumNotFoundForEventOnTime(){
        super();
    }

    public AuditoriumNotFoundForEventOnTime(@Nonnull Event event, @Nonnull LocalDateTime dateTime){
        super("Auditorium not found for event " + event.getName() + " on time " + dateTime);
    }
}
