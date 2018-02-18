package ua.epam.spring.hometask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/myAccount")
@Controller
public class MyAccountController {

    @GetMapping
    public String myAccountPage(){
        System.out.println("MyAccountController: Passing through...");
        return "myAccount";
    }

}
