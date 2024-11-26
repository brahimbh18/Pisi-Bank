package PISI.BANK.Pisi.bank.controllers;

import PISI.BANK.Pisi.bank.model.Client;
import PISI.BANK.Pisi.bank.service.ClientService;
import jakarta.servlet.http.HttpSession;
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
    public String registerClient(@ModelAttribute Client client) {
        if (clientService.getClientByCin(client.getCin()) != null) {
            return "redirect:/register?error=cinAlreadyExists";
        }
        if (clientService.getClientByEmail(client.getEmail()) != null) {
            return "redirect:/register?error=emailAlreadyExists";
        }
        clientService.createClient(client);  // Call service to create client
        return "redirect:/login";  // Redirect to login after successful registration
    }

    // Login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";  // Thymeleaf template for login form
    }

    // Handle login (authentication)
    @PostMapping("/login")
    public String loginClient(@RequestParam String email, @RequestParam String password, HttpSession session) {
        if (clientService.authenticateClient(email, password)) {
            session.setAttribute("authenticatedClient", email);  // Store client session
            return "redirect:/client/dashboard";  // Redirect to client dashboard after login
        }
        return "redirect:/login?error=invalidCredentials";  // Return to login page with error message
    }

    // Logout
    @GetMapping("/logout")
    public String logoutClient(HttpSession session) {
        session.invalidate();  // Invalidate the session
        return "redirect:/login";  // Redirect to login page after logout
    }
}