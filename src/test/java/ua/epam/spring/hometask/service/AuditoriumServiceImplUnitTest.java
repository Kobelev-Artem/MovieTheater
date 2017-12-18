package ua.epam.spring.hometask.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import java.util.Set;

import ua.epam.spring.hometask.domain.Auditorium;
import ua.epam.spring.hometask.service.impl.AuditoriumServiceImpl;

public class AuditoriumServiceImplUnitTest {

    private static final String BERLIN_AUDITORIUM = "Berlin";
    private static final String PARIS_AUDITORIUM = "Paris";

    private AuditoriumServiceImpl auditoriumService;
    private Auditorium berlinAuditorium ;
    private Auditorium parisAuditorium ;

    @Before
    public void setUp(){
        auditoriumService = new AuditoriumServiceImpl();
        auditoriumService.setAuditoriums(createAuditoriums());
    }

    @Test
    public void shouldFindAuditoriumByName(){
        Auditorium actualAuditorium = auditoriumService.getByName(BERLIN_AUDITORIUM);
        assertEquals(berlinAuditorium, actualAuditorium);
    }

    @Test
    public void shouldFIndAuditoriumByNameIgnoringCase(){
        Auditorium actualAuditorium = auditoriumService.getByName(PARIS_AUDITORIUM.toUpperCase());
        assertEquals(parisAuditorium, actualAuditorium);
    }

    private Set<Auditorium> createAuditoriums(){
        berlinAuditorium = new Auditorium();
        berlinAuditorium.setName(BERLIN_AUDITORIUM);
        berlinAuditorium.setNumberOfSeats(100l);
        berlinAuditorium.setVipSeats(Set.of(11l, 12l, 13l));

        parisAuditorium = new Auditorium();
        parisAuditorium.setName(PARIS_AUDITORIUM);
        parisAuditorium.setNumberOfSeats(50l);
        parisAuditorium.setVipSeats(Set.of(21l, 22l, 23l));

        return Set.of(berlinAuditorium, parisAuditorium);
    }

}
