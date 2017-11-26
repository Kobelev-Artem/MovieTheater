package ua.epam.spring.hometask.service.impl;

import org.apache.commons.lang3.NotImplementedException;
import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.UserService;

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
    public User save(@Nonnull User user) {
        return userDao.save(user);
    }

    @Override
    public void remove(@Nonnull User user) {
        userDao.remove(user);
    }

    @Override
    public User getById(@Nonnull Long id) {
        throw new NotImplementedException("error_-^^-_");
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return userDao.getAll();
    }
}
