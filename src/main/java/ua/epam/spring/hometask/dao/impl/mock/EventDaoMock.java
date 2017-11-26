package ua.epam.spring.hometask.dao.impl.mock;

import ua.epam.spring.hometask.dao.EventDao;
import ua.epam.spring.hometask.domain.Event;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EventDaoMock implements EventDao{

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
}
