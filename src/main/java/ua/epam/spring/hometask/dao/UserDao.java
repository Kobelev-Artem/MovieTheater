package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface UserDao extends AbstractDomainObjectDao<User>{
    @Nullable
    User getUserByEmail(@Nonnull String email);
}
