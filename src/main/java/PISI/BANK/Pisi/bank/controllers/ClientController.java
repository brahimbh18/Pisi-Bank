package PISI.BANK.Pisi.bank.controllers;

import PISI.BANK.Pisi.bank.model.Client;
import PISI.BANK.Pisi.bank.service.ClientService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.userdetails.UserDetails;


@Controller
public class ClientController {
    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal UserDetails currentClient, Model model) {
        System.out.println(currentClient);
        model.addAttribute("client", currentClient);
        return "dashboard";
    }


}
