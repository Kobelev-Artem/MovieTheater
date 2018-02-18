package ua.epam.spring.hometask.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;

import ua.epam.spring.hometask.service.impl.DiscountServiceImpl;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/applicationContext.xml"})
public class DiscountServiceImplIntegrationTest {

    private static double DELTA = 0.001d;

    @Resource
    private DiscountServiceImpl discountService;

    @Test
    public void shouldApplyWholesaleDiscount(){
        Double resultDiscount = discountService.getDiscount(User.anonymousUser, new Event(), LocalDateTime.now(), 10);

        assertEquals(5d, resultDiscount, DELTA);
    }

    @Test
    public void shouldApplyWholesaleDiscountFor15Tickets(){
        Double resultDiscount = discountService.getDiscount(User.anonymousUser, new Event(), LocalDateTime.now(), 15);

        assertEquals(50d/15d, resultDiscount, DELTA);
    }

    @Test
    public void shouldApplyBirthdayDiscount(){
        User user = new User();
        user.setBirthday(LocalDate.now());

        Double resultDiscount = discountService.getDiscount(user, new Event(), LocalDateTime.now(), 1);

        assertEquals(10d, resultDiscount, DELTA);
    }

    @Test
    public void shouldApplyBirthdayDiscountEvenWhenWholesaleDiscountIsApplicable(){
        User user = new User();
        user.setBirthday(LocalDate.now());

        Double resultDiscount = discountService.getDiscount(user, new Event(), LocalDateTime.now(), 10);

        assertEquals(10d, resultDiscount, DELTA);
    }

}
