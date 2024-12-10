package PISI.BANK.Pisi.bank.repositories;

import PISI.BANK.Pisi.bank.config.DatabaseConfig;
import PISI.BANK.Pisi.bank.model.Customer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository {

    private final JdbcTemplate jdbcTemplate;

    public CustomerRepository() {
        this.jdbcTemplate = DatabaseConfig.jdbcTemplate();
    }

    public Customer getCustomerByCin(int cin) {
        String sql = "SELECT * FROM Customer WHERE cin = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{cin}, new RowMappers.CustomerRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Customer getCustomerByEmail(String email) {
        String sql = "SELECT * FROM Customer WHERE Email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{email}, new RowMappers.CustomerRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null; // No customer found with that email
        }
    }

    public Customer getCustomerByRib(long rib) {
        String sql = "SELECT * FROM Customer as c, BankAccount as b WHERE num = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{rib}, new RowMappers.CustomerRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void createCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer object cannot be null");
        }

        String sql = "INSERT INTO Customer (cin, firstName, lastName, dateOfBirth, placeOfBirth, maritalStatus, " +
                "gender, phoneNumber, email, passwordHash, jobType, incomeCategory) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            jdbcTemplate.update(sql,
                    customer.getCin(),
                    customer.getFirstName(),
                    customer.getLastName(),
                    customer.getDateOfBirth(),
                    customer.getPlaceOfBirth(),
                    customer.getMaritalStatus(),
                    customer.getGender(),
                    customer.getPhoneNumber(),
                    customer.getEmail(),
                    customer.getPasswdHash(),
                    customer.getJobType(),
                    customer.getIncomeCategory()
            );

            System.out.println("Customer created successfully: " + customer.getCin());
        } catch (Exception e) {
            System.err.println("Failed to create customer: " + e.getMessage());
            throw new RuntimeException("Error creating customer", e);
        }
    }


    public void updateCustomer(Customer customer) {
        String sql = "UPDATE Customer SET FirstName = ?, LastName = ?, PhoneNumber = ?, Email = ?, PasswordHash = ? WHERE Cin = ?";
        jdbcTemplate.update(sql, customer.getFirstName(), customer.getLastName(), customer.getPhoneNumber(), customer.getEmail(), customer.getPasswdHash(), customer.getCin());
    }

    public void deleteCustomer(int cin) {
        String sql = "DELETE FROM Customer WHERE Cin = ?";
        jdbcTemplate.update(sql, cin);
    }

    public List<Customer> getAllCustomers() {
        String sql = "SELECT * FROM Customer";
        return jdbcTemplate.query(sql, new RowMappers.CustomerRowMapper());
    }
}
