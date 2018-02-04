package ua.epam.spring.hometask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static ua.epam.spring.hometask.constants.WebConstants.HOME_PAGE;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String homePage(){
        System.out.println("HomeController: Passing through...");
        return HOME_PAGE;
    }

}
