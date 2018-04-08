package ua.epam.spring.hometask.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.epam.spring.hometask.domain.Event;
import ua.epam.spring.hometask.service.EventService;

import javax.annotation.Resource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequestMapping(value = "/admin")
@Controller
public class AdminController {

    @Resource
    private EventService eventService;

    @GetMapping
    public String adminPage(){
        return "admin";
    }

    @PostMapping(value = "/uploadEvent")
    public String uploadEvent(@RequestParam("file") MultipartFile file) throws IOException {
        Gson gson = new Gson();
        String eventString = new String(file.getBytes(), StandardCharsets.UTF_8);
        Event inputEvent = gson.fromJson(eventString, Event.class);
        eventService.save(inputEvent);

        return "admin";
    }

    public EventService getEventService() {
        return eventService;
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }
}
