package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.Event;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

public interface EventDao extends AbstractDomainObjectDao<Event>{



    @Nullable
    Event getByName(@Nonnull String name);

    @Nonnull
    Set<Event> getForDateRange(@Nonnull LocalDate from,
                               @Nonnull LocalDate to);

    @Nonnull Set<Event> getNextEvents(@Nonnull LocalDateTime to);
}
