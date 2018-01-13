package ua.epam.spring.hometask.dao.impl.mock;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ua.epam.spring.hometask.dao.UserDao;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.util.enums.UserRole;

public class UserDaoMock implements UserDao{

    private static List<User> users = new ArrayList<>();

    @Nullable
    @Override
    public User getUserByEmail(@Nonnull String email) {
        List<User> foundUsers = users.stream().filter(u -> email.equals(u.getEmail())).collect(Collectors.toList());
        if (foundUsers.isEmpty()){
            return null;
        }
        return foundUsers.get(0);
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

    @PostConstruct
    public void initUsers(){
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Snow");
        user1.setEmail("john.snow@gmail.com");
        user1.setPassword("pass");
        user1.setBirthday(LocalDate.of(1990, Month.JANUARY, 15));
        user1.setUserRoles(Set.of(UserRole.CUSTOMER, UserRole.ADMIN));

        User user2 = new User();
        user2.setFirstName("Brad");
        user2.setLastName("Pitt");
        user2.setEmail("brad.pitt@gmail.com");
        user2.setPassword("pass");
        user2.setBirthday(LocalDate.of(1995, Month.DECEMBER, 31));
        user2.setUserRoles(Set.of(UserRole.CUSTOMER));

        users.addAll(List.of(user1, user2));
    }
}
