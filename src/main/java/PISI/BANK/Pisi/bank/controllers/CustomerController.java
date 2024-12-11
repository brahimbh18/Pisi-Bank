package PISI.BANK.Pisi.bank.controllers;

import PISI.BANK.Pisi.bank.config.CustomUserDetails;
import PISI.BANK.Pisi.bank.model.BankAccount;
import PISI.BANK.Pisi.bank.model.Customer;
import PISI.BANK.Pisi.bank.service.BankAccountService;
import PISI.BANK.Pisi.bank.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String debug (@AuthenticationPrincipal UserDetails currentCustomer, @RequestParam String email, @RequestParam String password, HttpSession session, Model model){
        Customer customer = customerService.authenticateCustomer(email, password);

        if (customer != null) {
            List<BankAccount> bankAccounts = bankAccountService.getBankAccountsByCin(customer.getCin());

            CustomUserDetails userDetails = new CustomUserDetails(customer);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);


            model.addAttribute("customer", customer);
            model.addAttribute("bankAccounts", bankAccounts);
            model.addAttribute("bankAccount", bankAccounts.get(0));
            return "dashboard";

        }
        return "redirect:/login?error=invalidCredentials";
    }



    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal CustomUserDetails currentCustomer, Model model) {
        if (currentCustomer != null) {
            Customer customer = currentCustomer.getCustomer();
            List<BankAccount> bankAccounts = bankAccountService.getBankAccountsByCin(customer.getCin());

            model.addAttribute("customer", customer);
            model.addAttribute("bankAccounts", bankAccounts);
            if (!bankAccounts.isEmpty()) {
                model.addAttribute("bankAccount", bankAccounts.get(0));
            } else {
                model.addAttribute("bankAccount", null);
            }
            System.out.println(currentCustomer.getCustomer().toString());
            return "dashboard";
        }
        return "redirect:/login?error=notLoggedIn";
    }

    @GetMapping("/cards")
    public String showCards(@AuthenticationPrincipal CustomUserDetails currentCustomer, Model model) {
        if (currentCustomer != null) {
            Customer customer = currentCustomer.getCustomer();
            List<BankAccount> bankAccounts = bankAccountService.getBankAccountsByCin(customer.getCin());
            model.addAttribute("customer", customer);
            model.addAttribute("bankAccounts", bankAccounts);

            return "cards";
        }
        System.out.println("current customer is " + currentCustomer);
        return "redirect:/login?error=notLoggedIn";
    }


    @GetMapping("/addAccount")
    public String addAccountPage(Model model, HttpSession session) {
        model.addAttribute("customer", session.getAttribute("customer"));
        model.addAttribute("bankAccounts", session.getAttribute("bankAccounts"));

        return "addAccount";
    }


}
