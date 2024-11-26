package PISI.BANK.Pisi.bank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Handle the request for the home page
    @GetMapping("/")
    public String showHomePage() {
        return "home"; // This corresponds to the "home.html" Thymeleaf template
    }
}