package ua.epam.spring.hometask.service.impl;

import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.UserService;
import ua.epam.spring.hometask.util.enums.UserRole;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.Resource;
import java.util.Collection;

public class UserServiceImpl implements UserService{

    @Resource
    private UserDao userDao;

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public boolean hasRole(@Nonnull User user, @Nonnull UserRole role) {
        return user.getUserRoles().contains(role);
    }

    @Override
    public User save(@Nonnull User user) {
        return userDao.save(user);
    }

    @Override
    public void remove(@Nonnull User user) {
        userDao.remove(user);
    }

    @Override
    public User getById(@Nonnull Long id) {
        throw new UnsupportedOperationException("error_-^^-_");
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return userDao.getAll();
    }
}
