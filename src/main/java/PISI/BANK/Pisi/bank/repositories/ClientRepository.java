package PISI.BANK.Pisi.bank.repositories;

import PISI.BANK.Pisi.bank.model.Client;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClientRepository {

    private final JdbcTemplate jdbcTemplate;

    public ClientRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void testDatabase() {
        String sql = "SELECT 1";
        try {
            jdbcTemplate.queryForObject(sql, Integer.class);
            System.out.println("Database connection is working!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Client getClientByCin(int cin) {
        String sql = "SELECT * FROM Client WHERE cin = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{cin}, new RowMappers.ClientRowMapper());
    }

    public Client getClientByEmail(String email) {
        String sql = "SELECT * FROM Client WHERE Email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{email}, new RowMappers.ClientRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null; // No client found with that email
        }
    }
    public void createClient(Client client) {
        String sql = "INSERT INTO Client (Cin, FirstName, LastName, PhoneNumber, Email, PasswordHash) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, client.getCin(), client.getFirstName(), client.getLastName(), client.getPhoneNumber(), client.getEmail(), client.getPasswdHash());
        System.out.println("client created");
    }

    public void updateClient(Client client) {
        String sql = "UPDATE Client SET FirstName = ?, LastName = ?, PhoneNumber = ?, Email = ?, PasswordHash = ? WHERE Cin = ?";
        jdbcTemplate.update(sql, client.getFirstName(), client.getLastName(), client.getPhoneNumber(), client.getEmail(), client.getPasswdHash(), client.getCin());
    }

    public void deleteClient(int cin) {
        String sql = "DELETE FROM Client WHERE Cin = ?";
        jdbcTemplate.update(sql, cin);
    }

    public List<Client> getAllClients() {
        String sql = "SELECT * FROM Client";
        return jdbcTemplate.query(sql, new RowMappers.ClientRowMapper());
    }
}
