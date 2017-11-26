package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.DomainObject;

import javax.annotation.Nonnull;
import java.util.Collection;

public interface AbstractDomainObjectDao<T extends DomainObject>  {

    T save(@Nonnull T object);

    void remove(@Nonnull T object);

    T getById(@Nonnull Long id);

    @Nonnull
    Collection<T> getAll();

}
