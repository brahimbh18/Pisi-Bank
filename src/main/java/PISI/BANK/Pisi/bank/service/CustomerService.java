package PISI.BANK.Pisi.bank.service;

import PISI.BANK.Pisi.bank.model.Customer;
import PISI.BANK.Pisi.bank.repositories.CustomerRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void createCustomer(Customer customer) {
        // Check if a customer already exists with the same CIN or email

        if (customerRepository.getCustomerByCin(customer.getCin()) != null) {
            throw new IllegalArgumentException("Customer with this CIN already exists.");
        }

        if (customerRepository.getCustomerByEmail(customer.getEmail()) != null) {
            throw new IllegalArgumentException("Customer with this email already exists.");
        }
        // If no existing customer, create the new customer
        customerRepository.createCustomer(customer);
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
            //System.out.println(BCrypt.hashpw(password, BCrypt.gensalt()));
            System.out.println(customer.getPasswdHash());
            customer = BCrypt.checkpw(password, customer.getPasswdHash()) ? customer : null;
            System.out.println(customer);
            return customer;
        }
        return null;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }
}
