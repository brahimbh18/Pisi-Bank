package PISI.BANK.Pisi.bank.controllers;

import PISI.BANK.Pisi.bank.model.Client;
import PISI.BANK.Pisi.bank.service.ClientService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final ClientService clientService;

    public AuthController(ClientService clientService) {
        this.clientService = clientService;
    }

    // Registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("client", new Client());
        return "register";  // Thymeleaf template for registration form
    }

    // Handle registration
    @PostMapping("/register")
    public String registerClient(@ModelAttribute Client client, @RequestParam String password) {
        try {
            if (clientService.getClientByCin(client.getCin()) != null) {
                System.out.println("CIN found");
                return "redirect:/register?error=cinAlreadyExists";
            }
            if (clientService.getClientByEmail(client.getEmail()) != null) {
                System.out.println("Email found");
                return "redirect:/register?error=emailAlreadyExists";
            }

            // If CIN and email are unique, create the client
            client.setPasswdHash(BCrypt.hashpw(password, BCrypt.gensalt()));
            clientService.createClient(client);
            System.out.println("Client created successfully");
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
    public String loginClient(@RequestParam String email, @RequestParam String password, HttpSession session) {
        Client client;
        try {
            client = clientService.authenticateClient(email, password);
            if (client != null) {
                System.out.println("done");
                session.setAttribute("authenticatedClient", client);  // Store client session
                return "redirect:/client/dashboard";  // Redirect to client dashboard after login
            }
            System.out.println("not done");
            return "redirect:/login?error=invalidCredentials";  // Return to login page with error message
        } catch (Exception e) {
            return "redirect:/login?err err err";
        }

    }

    // Logout
    @GetMapping("/logout")
    public String logoutClient(HttpSession session) {
        session.invalidate();  // Invalidate the session
        return "redirect:/login";  // Redirect to login page after logout
    }
}