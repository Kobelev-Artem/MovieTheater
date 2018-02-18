package ua.epam.spring.hometask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/")
@Controller
public class HomeController {

    @RequestMapping(method = RequestMethod.GET)
    public String homePage(){
        System.out.println("HomeController: Passing through...");
        return "homePage";
    }

}
