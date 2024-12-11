package PISI.BANK.Pisi.bank.service;

import PISI.BANK.Pisi.bank.config.CustomUserDetails;
import PISI.BANK.Pisi.bank.model.Customer;
import PISI.BANK.Pisi.bank.repositories.CustomerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String createCustomer(Customer customer) {
        // Check if a customer already exists with the same CIN or email

        if (customerRepository.getCustomerByCin(customer.getCin()) != null) {
            System.out.println("Customer with this CIN already exists.");
            return "cinAlreadyExists";
        }

        if (customerRepository.getCustomerByEmail(customer.getEmail()) != null) {
            System.out.println("Customer with this email already exists.");
            return "emailAlreadyExists";
        }
        customer.setPasswdHash(BCrypt.hashpw(customer.getPasswdHash(), BCrypt.gensalt()));
        // If no existing customer, create the new customer
        customerRepository.createCustomer(customer);
        return "";
    }

    public Customer getCustomerByCin(int cin) {
        return customerRepository.getCustomerByCin(cin);
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepository.getCustomerByEmail(email);
    }

    public Customer getCustomerByRib(int rib) {
        return customerRepository.getCustomerByRib(rib);
    }
    public void updateCustomer(Customer customer) {
        customerRepository.updateCustomer(customer);
    }

    public void deleteCustomer(int cin) {
        customerRepository.deleteCustomer(cin);
    }

    public Customer authenticateCustomer(String email, String password) {
        Customer customer = customerRepository.getCustomerByEmail(email);
        if (customer != null) {
            customer = BCrypt.checkpw(password, customer.getPasswdHash()) ? customer : null;
            return customer;
        }
        return null;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String email = username;
        Customer customer = customerRepository.getCustomerByEmail(email);
        if (customer == null) {
            throw new UsernameNotFoundException("Customer not found with EMAIL: " + email);
        }

        return new CustomUserDetails(customer);
    }
}
