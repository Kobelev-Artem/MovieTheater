package ua.epam.spring.hometask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/")
@Controller
public class HomeController {

    @GetMapping
    public String homePage(){
        System.out.println("HomeController: Passing through...");
        return "homePage";
    }

}
