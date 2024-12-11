package PISI.BANK.Pisi.bank.config;


import PISI.BANK.Pisi.bank.model.Customer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {
    private final Customer customer;

    public CustomUserDetails(Customer customer) {
        this.customer = customer;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return authorities/roles as needed (e.g., using a role from the Customer object)
        return null; // You can implement role logic here if necessary
    }

    @Override
    public String getPassword() {
        return customer.getPasswdHash(); // Use the password hash stored in your Customer object
    }

    @Override
    public String getUsername() {
        return String.valueOf(customer.getCin()); // Using CIN as the unique username
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Customize as needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Customize as needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Customize as needed
    }

    @Override
    public boolean isEnabled() {
        return true; // Customize as needed
    }

    public Customer getCustomer() {
        return customer;
    }
}
