package ua.epam.spring.hometask.dao.impl.mock;

import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.User;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDaoMock implements UserDao{

    private static List<User> users = new ArrayList<>();

    /*TODO add annotation isEmail*/
    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        return users.stream().filter(u -> email.equals(u.getEmail())).findFirst().orElseGet(null);
    }

    @Override
    public User save(@Nonnull User user) {
        users.add(user);
        return user;
    }

    @Override
    public void remove(@Nonnull User user) {
        users.remove(user);
    }

    @Override
    public User getById(@Nonnull Long id) {
        return users.stream().filter(e -> id.equals(e.getId())).findFirst().get();
    }

    @Nonnull
    @Override
    public Collection<User> getAll() {
        return users;
    }
}
