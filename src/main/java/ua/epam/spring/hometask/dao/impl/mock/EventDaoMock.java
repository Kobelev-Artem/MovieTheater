package ua.epam.spring.hometask.dao.impl.mock;

import static ua.epam.spring.hometask.constants.Constants.Events.BEST_MOVIE_EVENT_NAME;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;
import ua.epam.spring.hometask.service.AuditoriumService;

public class EventDaoMock implements EventDao{

    @Resource
    private AuditoriumService auditoriumService;

    private static List<Event> events = new ArrayList<>();

    @Nullable
    @Override
    public Event getByName(@Nonnull String name) {
        return events.stream().filter(e -> name.equalsIgnoreCase(e.getName())).findFirst().get();
    }

    @Nonnull
    @Override
    public Set<Event> getForDateRange(@Nonnull LocalDate from, @Nonnull LocalDate to) {
        return getForDateTimeRangeImpl(from.atStartOfDay().minusSeconds(1), to.plusDays(1).atStartOfDay());
    }

    @Nonnull
    @Override
    public Set<Event> getNextEvents(@Nonnull LocalDateTime to) {
        return getForDateTimeRangeImpl(LocalDateTime.now().minusSeconds(1), to.plusSeconds(1));
    }

    private Set<Event> getForDateTimeRangeImpl(@Nonnull LocalDateTime from, @Nonnull LocalDateTime to){
        return events.stream()
                .filter(e -> e.getAirDates().stream()
                        .anyMatch(time -> time.isAfter(from) && time.isBefore(to)))
                .collect(Collectors.toSet());
    }

    @Override
    public Event save(@Nonnull Event event) {
        events.add(event);
        return event;
    }

    @Override
    public void remove(@Nonnull Event event) {
        events.remove(event);
    }

    @Override
    public Event getById(@Nonnull Long id) {
        return events.stream().filter(e -> id.equals(e.getId())).findFirst().get();
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return events;
    }

    @PostConstruct
    public void initEvents(){
        Event event = new Event();
        event.addAirDateTime(LocalDateTime.now(), auditoriumService.getAll().iterator().next());
        event.setName(BEST_MOVIE_EVENT_NAME);
        event.setRating(EventRating.MID);
        event.setBasePrice(30d);

        events.add(event);
    }

    public AuditoriumService getAuditoriumService() {
        return auditoriumService;
    }

    public void setAuditoriumService(AuditoriumService auditoriumService) {
        this.auditoriumService = auditoriumService;
    }
}
