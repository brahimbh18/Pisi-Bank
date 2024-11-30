package PISI.BANK.Pisi.bank.controllers;

import PISI.BANK.Pisi.bank.model.BankAccount;
import PISI.BANK.Pisi.bank.model.Customer;
import PISI.BANK.Pisi.bank.repositories.BankAccountRepository;
import PISI.BANK.Pisi.bank.service.BankAccountService;
import PISI.BANK.Pisi.bank.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AuthController {

    private final CustomerService customerService;
    private final BankAccountService bankAccountService;

    public AuthController(CustomerService customerService, BankAccountService bankAccountService) {
        this.customerService = customerService;
        this.bankAccountService = bankAccountService;
    }

    // Registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "register";  // Thymeleaf template for registration form
    }

    // Handle registration
    @PostMapping("/register")
    public String registerCustomer(@ModelAttribute Customer customer, @ModelAttribute BankAccount bankAccount, @RequestParam String password) {
        try {
            if (customerService.getCustomerByCin(customer.getCin()) != null) {
                System.out.println("CIN found");
                return "redirect:/register?error=cinAlreadyExists";
            }
            if (customerService.getCustomerByEmail(customer.getEmail()) != null) {
                System.out.println("Email found");
                return "redirect:/register?error=emailAlreadyExists";
            }

            // If CIN and email are unique, create the customer
            customer.setPasswdHash(BCrypt.hashpw(password, BCrypt.gensalt()));
            customerService.createCustomer(customer);
            System.out.println("Customer created successfully");
            bankAccount.setCustomerCin(customer.getCin());
            try {
                bankAccountService.createBankAccount(bankAccount);
                System.out.println("registered");
            } catch(Exception e) {
                System.out.println("bank account creation error");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return "redirect:/register?error=serverError";
        }

        return "redirect:/login";
    }

    // Login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";  // Thymeleaf template for login form
    }

    // Handle login (authentication)
    @PostMapping("/login")
    public String loginCustomer() {
        Customer customer;
        System.out.println("Login Login Login");
//        try {
//            System.out.println("aaaaaaaaaaaaaaaaaaa");
//            customer = customerService.authenticateCustomer(email, password);
//            if (customer != null) {
//                System.out.println("done");
//                session.setAttribute("authenticatedCustomer", customer);  // Store customer session
//                return "redirect:/customer/dashboard";  // Redirect to customer dashboard after login
//            }
//            System.out.println("not done");
//            return "redirect:/login?error=invalidCredentials";  // Return to login page with error message
//        } catch (Exception e) {
//            return "redirect:/login?err err err";
//        }
        return "dashboard";
    }

    @PostMapping("debug")
    public String debug (@RequestParam String email, @RequestParam String password, HttpSession session, Model model){
        System.out.println("email : " + email);
        System.out.println("password : " + password);
        Customer customer;
        List<BankAccount> bankAccounts;
        try {
            customer = customerService.authenticateCustomer(email, password);
            bankAccounts = bankAccountService.getBankAccountsByCin(customer.getCin());
            if (customer != null) {
                System.out.println("successfull login");
                session.setAttribute("customer", customer);
                //session.setAttribute("bankAccounts", bankAccounts);

                model.addAttribute("customer", customer);
                //model.addAttribute("bankAccounts", bankAccounts);
                System.out.println("customer in session : " + session.getAttribute("customer"));
                return "dashboard";
            }
            return "login";
        } catch (Exception e) {
            return "redirect:/login?error=invalidCredentials";
        }
    }

    // Logout
    @GetMapping("/logout")
    public String logoutCustomer(HttpSession session) {
        session.invalidate();  // Invalidate the session
        return "redirect:/login";  // Redirect to login page after logout
    }
}