package PISI.BANK.Pisi.bank.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import PISI.BANK.Pisi.bank.model.Client;
import PISI.BANK.Pisi.bank.service.ClientService;

import java.sql.SQLException;

@Controller
public class AccountController {
    ClientService service;
    @PostMapping("/register")
    public String register(@ModelAttribute Client client) throws SQLException {
        System.out.println(client.getPhoneNumber());
        ClientService.insertClient(client);
        ClientService.showClients();
        return "home";
    }

    @PostMapping("/login")
    public String login() {
        return "home";
    }
}
