package PISI.BANK.Pisi.bank.repositories;

import PISI.BANK.Pisi.bank.model.BankAccount;
import PISI.BANK.Pisi.bank.model.CheckingsAccount;
import PISI.BANK.Pisi.bank.model.SavingsAccount;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

    // Example method to create a new client
    public void createAccount(BankAccount account) {
        String sql = "INSERT INTO Account (num, cinClient, date, balance, type, overdraft, interestRate) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql,
                account.getNum(),
                account.getCinClient(),
                account.getDate(),
                account.getBalance(),
                account instanceof CheckingsAccount ? "checkings" : "savings",
                account instanceof CheckingsAccount ? ((CheckingsAccount) account).getOverdraft() : null,
                account instanceof SavingsAccount ? ((SavingsAccount) account).getInterestRate() : null
        );
    }

    public void updateAccount(BankAccount account) {
        String sql = "UPDATE Account SET balance = ?, overdraft = ?, interestRate = ? WHERE num = ?";
        jdbcTemplate.update(sql,
                account.getBalance(),
                account instanceof CheckingsAccount ? ((CheckingsAccount) account).getOverdraft() : null,
                account instanceof SavingsAccount ? ((SavingsAccount) account).getInterestRate() : null,
                account.getNum()
        );
    }

    public void deleteAccount(int num) {
        String sql = "DELETE FROM Account WHERE num = ?";
        jdbcTemplate.update(sql, num);
    }
}
