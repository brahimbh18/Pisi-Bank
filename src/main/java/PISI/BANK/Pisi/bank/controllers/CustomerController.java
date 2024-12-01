package PISI.BANK.Pisi.bank.controllers;

import PISI.BANK.Pisi.bank.model.Customer;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.userdetails.UserDetails;


@Controller
public class CustomerController {
    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal UserDetails currentCustomer, Model model) {
        System.out.println(currentCustomer);
        model.addAttribute("customer", currentCustomer);
        return "dashboard";
    }

    @GetMapping("/debug")
    public String test(@AuthenticationPrincipal UserDetails currentCustomer, Model model, HttpSession session) {
        System.out.println("current customer : " +currentCustomer);
        System.out.println("customer in session : " + session.getAttribute("customer"));
        model.addAttribute("customer", "jon doe");
        return "test";
    }


}
