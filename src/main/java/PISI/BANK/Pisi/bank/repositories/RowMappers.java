package PISI.BANK.Pisi.bank.repositories;


import PISI.BANK.Pisi.bank.model.*;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMappers {

    public static class ClientRowMapper implements RowMapper<Client> {
        @Override
        public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
            Client client = new Client();
            client.setCin(rs.getInt("Cin"));
            client.setFirstName(rs.getString("FirstName"));
            client.setLastName(rs.getString("LastName"));
            client.setPhoneNumber(rs.getString("PhoneNumber"));
            client.setEmail(rs.getString("Email"));
            client.setPasswdHash(rs.getString("PasswordHash"));
            return client;
        }
    }

    public static class BankAccountRowMapper implements RowMapper<BankAccount> {
        @Override
        public BankAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
            String type =rs.getString("type");
            BankAccount account;

            if (("checkings").equalsIgnoreCase(type)) {
                CheckingsAccount checkingsAccount = new CheckingsAccount();
                checkingsAccount.setOverdraft(rs.getInt("overdraft"));
                account = checkingsAccount;
            } else if (("savings").equalsIgnoreCase(type)) {
                SavingsAccount savingsAccount = new SavingsAccount();
                savingsAccount.setInterestRate(rs.getInt("interestRate"));
                account = savingsAccount;
            } else {
                throw new IllegalArgumentException("Unkown account type: " + type);
            }

            account.setNum(rs.getInt("num"));
            account.setCinClient(rs.getInt("cinClient"));
            account.setDate(rs.getString("date"));
            account.setBalance(rs.getFloat("balance"));
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
