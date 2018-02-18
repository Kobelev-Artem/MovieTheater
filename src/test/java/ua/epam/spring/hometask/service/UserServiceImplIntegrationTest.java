package ua.epam.spring.hometask.service;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.*;

import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.impl.UserServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/applicationContext.xml"})
public class UserServiceImplIntegrationTest {

    private static final String MARK_TWAIN_EMAIL = "mark.twain@email.com";

    private User markTwain;

    @Resource
    private UserServiceImpl userService;

    @Before
    public void setUp(){
        markTwain = new User();
        markTwain.setEmail(MARK_TWAIN_EMAIL);
        markTwain.setFirstName("Mark");
        markTwain.setLastName("Twain");
        markTwain.setBirthday(LocalDate.of(1835, 11, 30));
    }

    @After
    public void tearDown() throws Exception {
        List<User> users = (List<User>)userService.getAll();
        for (int i = 0; i < users.size(); i++){
            userService.remove(users.get(i));
        }
    }

    @Test
    public void shouldSaveUser(){
        assertEquals(0, userService.getAll().size());
        userService.save(markTwain);
        assertEquals(1, userService.getAll().size());
    }

    @Test
    public void shouldFIndUserByEmail(){
        userService.save(markTwain);

        User resultUser = userService.getUserByEmail(MARK_TWAIN_EMAIL);
        assertUserByNameAndEmailAndBirthday(markTwain, resultUser);
    }

    @Test
    public void shouldRemoveUser(){
        userService.save(markTwain);
        int usersCount = userService.getAll().size();
        userService.remove(markTwain);
        assertEquals(usersCount - 1, userService.getAll().size());
    }

    public void assertUserByNameAndEmailAndBirthday(User expectedUser, User actualUser){
        assertEquals(expectedUser.getFirstName(), actualUser.getFirstName());
        assertEquals(expectedUser.getLastName(), actualUser.getLastName());
        assertEquals(expectedUser.getEmail(), actualUser.getEmail());
        assertEquals(expectedUser.getBirthday(), actualUser.getBirthday());
    }


}
