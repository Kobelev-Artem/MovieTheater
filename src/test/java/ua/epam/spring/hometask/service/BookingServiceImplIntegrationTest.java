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
import java.time.LocalDateTime;
import java.util.*;

import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.domain.EventRating;
import ua.epam.spring.hometask.domain.User;
import ua.epam.spring.hometask.service.impl.BookingServiceImpl;
import ua.epam.spring.hometask.service.strategy.DiscountStrategy;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class BookingServiceImplIntegrationTest {

    private static final double BASE_PRICE = 100d;
    private static final double DELTA = 0.001d;
    private static final String HOLLYWOOD_AUD_NAME = "Hollywood";

    @Resource
    private BookingServiceImpl bookingService;

    @Resource
    private AuditoriumService auditoriumService;

    private Auditorium auditorium;
    private Event highRatedEvent;
    private Event midRatedEvent;
    private LocalDateTime SATURDAY_DATE_TIME = LocalDateTime.of(2017, 12, 23, 22, 00);
    private LocalDateTime MONDAY_DATE_TIME = LocalDateTime.of(2017, 12, 25, 22, 00);
    private LocalDate WENSDAY_DATE = LocalDate.of(1990, 12, 27);

    @Before
    public void setUp(){
        auditorium = auditoriumService.getByName(HOLLYWOOD_AUD_NAME);
        populateHighRatedEvent();
        populateMidRatedEvent();
    }

    @After
    public void tearDown(){
        //TODO remove all test data
    }

    private void populateMidRatedEvent() {
        midRatedEvent = new Event();
        NavigableSet<LocalDateTime> airDates = new TreeSet<>();
        airDates.add(MONDAY_DATE_TIME);
        midRatedEvent.setAirDates(airDates);

        midRatedEvent.setBasePrice(BASE_PRICE);
        midRatedEvent.setRating(EventRating.MID);
        midRatedEvent.setName("midRatedEvent name");

        NavigableMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<>();
        auditoriums.put(MONDAY_DATE_TIME, auditorium);
        midRatedEvent.setAuditoriums(auditoriums);
    }

    private void populateHighRatedEvent() {
        highRatedEvent = new Event();
        NavigableSet<LocalDateTime> airDates = new TreeSet<>();
        airDates.add(SATURDAY_DATE_TIME);
        highRatedEvent.setAirDates(airDates);

        highRatedEvent.setBasePrice(BASE_PRICE);
        highRatedEvent.setRating(EventRating.HIGH);
        highRatedEvent.setName("highRatedEvent name");

        NavigableMap<LocalDateTime, Auditorium> auditoriums = new TreeMap<>();
        auditoriums.put(SATURDAY_DATE_TIME, auditorium);
        highRatedEvent.setAuditoriums(auditoriums);
    }

    @Test
    public void shouldCorrectlyCalculatePriceForTicketsOnWeekendAndForHighRatedMovie(){
        double actualPrice = bookingService.getTicketsPrice(highRatedEvent, SATURDAY_DATE_TIME, User.anonymousUser, Set.of(1l, 2l, 3l));

        double expectedPrice = BASE_PRICE * BookingServiceImpl.HIGH_RATING_FACTOR * BookingServiceImpl.WEEKEND_FACTOR * 3;
        assertEquals(expectedPrice, actualPrice, DELTA);
    }

    @Test
    public void shouldCorrectlyCalculatePriceForVIPSeats(){
        double actualPrice = bookingService.getTicketsPrice(midRatedEvent, MONDAY_DATE_TIME, User.anonymousUser, Set.of(101l,92l,93l));

        double expectedPrice = BASE_PRICE * 4;
        assertEquals(expectedPrice, actualPrice, DELTA);
    }

    @Test
    public void shouldCorrectlyCalculateWholesaleDiscount(){
        double actualPrice = bookingService.getTicketsPrice(midRatedEvent, MONDAY_DATE_TIME, User.anonymousUser, Set.of(1l,2l,3l, 4l, 5l, 6l, 7l, 8l, 9l, 10l));

        double expectedPrice = BASE_PRICE * 9 + BASE_PRICE / 2;
        assertEquals(expectedPrice, actualPrice, DELTA);
    }

    @Test
    public void shouldCorrectlyCalculateBirthdayDiscount(){
        User user = new User();
        user.setBirthday(WENSDAY_DATE);

        double actualPrice = bookingService.getTicketsPrice(midRatedEvent, MONDAY_DATE_TIME, user, Set.of(91l,92l));

        double expectedPrice = BASE_PRICE * 2 * (1 - DiscountStrategy.BIRTHDAY_DISCOUNT / 100d);
        assertEquals(expectedPrice, actualPrice, DELTA);
    }
}
