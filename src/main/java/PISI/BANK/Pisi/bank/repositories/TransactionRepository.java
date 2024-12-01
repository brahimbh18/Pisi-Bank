package PISI.BANK.Pisi.bank.repositories;

import PISI.BANK.Pisi.bank.model.Transaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionRepository {

    private final JdbcTemplate jdbcTemplate;

    public TransactionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Log a new transaction (deposit, withdrawal, transfer)
    public void logTransaction(long accountNum, double amount, String type, long receiverAccountNum) {
        String sql;
        // Check if it's a transfer, and include receiver's account number if it is
        if ("transfer".equalsIgnoreCase(type)) {
            sql = "INSERT INTO Transaction (accountNum, date, amount, type, receiverAccountNum) VALUES (?, datetime('now'), ?, ?, ?)";
            jdbcTemplate.update(sql, accountNum, amount, type, receiverAccountNum);
        } else {
            sql = "INSERT INTO Transaction (accountNum, date, amount, type) VALUES (?, datetime('now'), ?, ?)";
            jdbcTemplate.update(sql, accountNum, amount, type);
        }
    }

    public List<Transaction> getTransactionsByCin(int cin) {
        String sql = "SELECT * FROM Transaction WHERE cin = ? GROUP BY cin ORDER BY date DESC";

        return jdbcTemplate.query(sql, new Object[]{cin}, new RowMappers.TransactionRowMapper());
    }
    // Fetch transactions for a given account
    public List<Transaction> getTransactionsByAccount(long accountNum) {
        String sql = "SELECT * FROM Transaction WHERE accountNum = ? ORDER BY date DESC";
        return jdbcTemplate.query(sql, new Object[]{accountNum}, new RowMappers.TransactionRowMapper());
    }
}