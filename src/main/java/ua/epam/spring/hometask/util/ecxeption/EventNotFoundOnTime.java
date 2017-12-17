package ua.epam.spring.hometask.util.ecxeption;

import ua.epam.spring.hometask.domain.Event;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;

public class EventNotFoundOnTime extends IllegalArgumentException{

    public EventNotFoundOnTime(){
        super();
    }

    public EventNotFoundOnTime(@Nonnull Event event, @Nonnull LocalDateTime dateTime){
        super("Event " + event.getName() + " not found on time " + dateTime);
    }
}
