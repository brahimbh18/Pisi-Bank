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

    // Example method to create a new customer
    public void createAccount(BankAccount account) {
        String sql = "INSERT INTO BankAccount (customerCin, date, balance, type, overdraft, interestRate) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                account.getCustomerCin(),
                account.getDate(),
                account.getBalance(),
                account.getType(),
                account.getOverdraft(),
                account.getInterestRate()
        );
        System.out.println("bank account created successfully");
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

    public long getLastRib() {
        String sql = "SELECT MAX(num) FROM Bank Account";
        long num;
        num = jdbcTemplate.queryForObject(sql, Long.class);
        return num;
    }
}
