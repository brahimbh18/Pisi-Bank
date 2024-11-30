package PISI.BANK.Pisi.bank.repositories;


import PISI.BANK.Pisi.bank.model.*;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMappers {

    public static class CustomerRowMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setCin(rs.getInt("Cin"));
            customer.setFirstName(rs.getString("FirstName"));
            customer.setLastName(rs.getString("LastName"));
            customer.setPhoneNumber(rs.getString("PhoneNumber"));
            customer.setEmail(rs.getString("Email"));
            customer.setPasswdHash(rs.getString("PasswordHash"));
            return customer;
        }
    }

    public static class BankAccountRowMapper implements RowMapper<BankAccount> {
        @Override
        public BankAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
            String type =rs.getString("type");
            BankAccount account = new BankAccount();

            account.setNum(rs.getInt("num"));
            account.setCustomerCin(rs.getInt("cinCustomer"));
            account.setDate(rs.getString("date"));
            account.setBalance(rs.getFloat("balance"));
            account.setOverdraft(rs.getInt("overdraft"));
            account.setInterestRate(rs.getInt("interestRate"));
            return account;
        }
    }

    public static class TransactionRowMapper implements RowMapper<Transaction> {
        @Override
        public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
            Transaction transaction = new Transaction();
            transaction.setId(rs.getInt("id"));
            transaction.setAccountNum(rs.getInt("accountNum"));
            transaction.setDate(rs.getString("date"));
            transaction.setAmount(rs.getDouble("amount"));
            transaction.setType(rs.getString("type"));
            transaction.setTargetAccountNum(rs.getObject("targetAccountNum", Integer.class)); // Can be null
            return transaction;
        }
    }
}
