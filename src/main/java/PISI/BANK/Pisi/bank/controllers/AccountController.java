package PISI.BANK.Pisi.bank.controllers;

import jakarta.servlet.http.HttpSession;
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
    public String register(@ModelAttribute Client client, HttpSession session) throws SQLException {
//        System.out.println(client.getCin());
//        System.out.println(client.getFirstName());
//        System.out.println(client.getLastName());
//        System.out.println(client.getPhoneNumber());
//        System.out.println(client.getEmail());
//        System.out.println(client.getPasswdHash());
        if (ClientService.getClient(client.getCin()) == null) {
//            ClientService.insertClient(client);
//            session.setAttribute("client", client);
            return "home";
        }

        return "exits";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Client client) throws SQLException {
        if (ClientService.getClient(client.getCin()) != null) {

            return "home";
        }
        return "index";
    }
}
