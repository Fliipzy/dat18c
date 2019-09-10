package org.dat18c.ships_and_sails.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController 
{
    @GetMapping("/")
    public String getHome()
    {
        return "Home";
    }

    @GetMapping("/secret")
    public String getSecret()
    {
        return "Secret";
    }
}