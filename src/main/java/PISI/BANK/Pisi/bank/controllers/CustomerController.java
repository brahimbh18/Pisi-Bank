package PISI.BANK.Pisi.bank.controllers;

import PISI.BANK.Pisi.bank.model.BankAccount;
import PISI.BANK.Pisi.bank.model.Customer;
import PISI.BANK.Pisi.bank.service.BankAccountService;
import PISI.BANK.Pisi.bank.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class CustomerController {
    private final CustomerService customerService;
    private final BankAccountService bankAccountService;

    public CustomerController(CustomerService customerService, BankAccountService bankAccountService) {
        this.customerService = customerService;
        this.bankAccountService = bankAccountService;
    }


    @PostMapping("/debug")
    public String debug (@RequestParam String email, @RequestParam String password, HttpSession session, Model model){
        Customer customer;
        List<BankAccount> bankAccounts;
        customer = customerService.authenticateCustomer(email, password);
        if (customer != null) {
            bankAccounts = bankAccountService.getBankAccountsByCin(customer.getCin());
            session.setAttribute("customer", customer);
            session.setAttribute("bankAccounts", bankAccounts);
            session.setAttribute("bankAccount", bankAccounts.get(0));

            model.addAttribute("customer", customer);
            model.addAttribute("bankAccounts", bankAccounts);
            model.addAttribute("bankAccount", bankAccounts.get(0));
            System.out.println("customer in session : " + session.getAttribute("customer"));
            return "dashboard";

        }
        return "redirect:/login?error=invalidCredentials";
    }


//    @PostMapping("/login")
//    public String debug (@RequestParam String email, @RequestParam String password, HttpSession session, Model model){
//        Customer customer;
//        List<BankAccount> bankAccounts;
//        System.out.println("email : " + email + "password : " + password);
//        customer = customerService.authenticateCustomer(email, password);
//        if (customer != null) {
//            System.out.println("gooood");
//            bankAccounts = bankAccountService.getBankAccountsByCin(customer.getCin());
//            session.setAttribute("customer", customer);
//            session.setAttribute("bankAccounts", bankAccounts);
//            session.setAttribute("bankAccount", bankAccounts.get(0));
//
////            for (BankAccount b: bankAccounts) {
////                System.out.println(b.getNum());
////            }
//
//            model.addAttribute("customer", customer);
//            model.addAttribute("bankAccounts", bankAccounts);
//            model.addAttribute("bankAccount", bankAccounts.get(0));
//            System.out.println("customer in session : " + session.getAttribute("customer"));
//            return "dashboard";
////
////        }
//        System.out.println("baad");
//        return "redirect:/login?error=invalidCredentials";
//    }


    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal UserDetails currentCustomer, HttpSession session, Model model) {
        Object sessionCustomer = session.getAttribute("customer");
        if (sessionCustomer != null) {
            System.out.println("dashboard success " + session.getAttribute("customer"));
            model.addAttribute("customer", sessionCustomer);
        } else {
            System.out.println("No customer found in session.");
        }
        model.addAttribute("bankAccounts", session.getAttribute("bankAccounts"));
        return "dashboard";
    }

    @GetMapping("/cards")
    public String showCards(@AuthenticationPrincipal UserDetails currentCustomer, Model model, HttpSession session) {

        System.out.println("Customer in session: " + session.getAttribute("customer"));
        model.addAttribute("customer", session.getAttribute("customer"));
        model.addAttribute("bankAccounts", session.getAttribute("bankAccounts"));
        return "cards";
    }


}
