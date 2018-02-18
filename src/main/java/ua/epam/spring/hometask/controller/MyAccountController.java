package ua.epam.spring.hometask.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/myAccount")
@Controller
public class MyAccountController {

    @RequestMapping(method = RequestMethod.GET)
    public String myAccountPage(){
        System.out.println("MyAccountController: Passing through...");
        return "myAccount";
    }

}
