package PISI.BANK.Pisi.bank.repositories;

import PISI.BANK.Pisi.bank.model.BankAccount;
import PISI.BANK.Pisi.bank.model.SavingsAccount;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BankAccountRepository {

    private final JdbcTemplate jdbcTemplate;

    public BankAccountRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public BankAccount getAccountByNum(int num) {
        String sql = "SELECT * FROM bankAccount WHERE num = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{num}, new RowMappers.BankAccountRowMapper());
    }


    public List<BankAccount> getBankAccountsByCin(int cin) {
        String sql = "SELECT * FROM BankAccount WHERE customerCin = ?";
        return jdbcTemplate.query(sql, new Object[]{cin}, new RowMappers.BankAccountRowMapper());
    }

    public List<BankAccount> getAllBankAccounts() {
        String sql = "SELECT * FROM BankAccount";
        try {
            return jdbcTemplate.query(sql, new RowMappers.BankAccountRowMapper());
        } catch (Exception e) {
            System.err.println("error : " + e);
            return null;
        }
    }

    // Example method to create a new customer
    public void createAccount(BankAccount account) {
        String sql = "INSERT INTO BankAccount (num, customerCin, date, balance, type, overdraft, interestRate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql,
                    account.getNum(),
                    account.getCustomerCin(),
                    account.getDate(),
                    account.getBalance(),
                    account.getType(),
                    account.getOverdraft(),
                    account.getInterestRate()
            );
            System.out.println("bank account created successfully");
        } catch (Exception e) {
            System.err.println("error : " + e);
        }
    }

    public void updateAccount(BankAccount account) {
        String sql = "UPDATE Account SET balance = ?, overdraft = ?, interestRate = ? WHERE num = ?";
        jdbcTemplate.update(sql,
                account.getBalance(),
                account.getOverdraft(),
                account.getInterestRate(),
                account.getNum()
        );
    }

    public void deleteAccount(int num) {
        String sql = "DELETE FROM Account WHERE num = ?";
        jdbcTemplate.update(sql, num);
    }

    public Long getLastRib() {
        String sql = "SELECT MAX(num) FROM BankAccount";
        long num;
        try {
            num = jdbcTemplate.queryForObject(sql, Long.class);
            return num;
        } catch (Exception e) {
            System.out.println("rib error");
        }
        return 1111222233330000L;
    }
}
