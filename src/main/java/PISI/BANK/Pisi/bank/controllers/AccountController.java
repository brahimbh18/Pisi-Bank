package PISI.BANK.Pisi.bank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import PISI.BANK.Pisi.bank.model.Client;
import PISI.BANK.Pisi.bank.service.ClientService;

@Controller
public class AccountController {
    ClientService service;
    @PostMapping("/register")
    public String register(@ModelAttribute Client client) {
        service.createClient(client);
        return "home";
    }

    @PostMapping("/login")
    public String login() {
        Client c = service.getAccount((long)0);
        System.out.println(c);
        return "home";
    }
}
