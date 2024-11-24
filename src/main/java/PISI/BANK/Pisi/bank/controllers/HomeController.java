package PISI.BANK.Pisi.bank.controllers;

import PISI.BANK.Pisi.bank.model.Client;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/home")
    public String login(Principal principal, HttpSession session) {
        Client client = (Client) session.getAttribute("client");
        System.out.println(client.getCin());
        return "register";
    }
}
