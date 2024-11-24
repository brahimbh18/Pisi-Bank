package PISI.BANK.Pisi.bank.service;

import PISI.BANK.Pisi.bank.model.Client;

import java.sql.*;
import java.util.Vector;

public class ClientService {
    static DBConnection db = new DBConnection();
    static Connection connection;
    public static void insertClient(Client client) throws SQLException {
        String createTableQuery = """
            CREATE TABLE IF NOT EXISTS Client (
                Cin INTEGER PRIMARY KEY,
                FirstName TEXT NOT NULL,
                LastName TEXT NOT NULL,
                PhoneNumber TEXT CHECK(PhoneNumber GLOB '[0-9]*'), -- Numeric-only validation
                Email TEXT UNIQUE NOT NULL, -- Ensures unique email addresses
                PasswordHash TEXT NOT NULL -- Securely hashed and salted password
            );
        """;

        String insertQuery = "INSERT INTO Client VALUES (?, ?, ?, ?, ?, ?);";

        connection = db.getConnection();

        // Check existing schema and drop if necessary
        try (Statement stmt = connection.createStatement()) {
            // Drop the table if schema mismatch is suspected
            stmt.executeUpdate("DROP TABLE IF EXISTS Client");
            stmt.executeUpdate(createTableQuery);
//            System.out.println("Table created successfully!");
        } catch (SQLException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }

        // Insert data
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, client.getCin());
            preparedStatement.setString(2, client.getFirstName());
            preparedStatement.setString(3, client.getLastName());
            preparedStatement.setString(4, client.getPhoneNumber());
            preparedStatement.setString(5, client.getEmail());
            preparedStatement.setString(6, client.getPasswdHash());

            preparedStatement.executeUpdate();
//            System.out.println("Client inserted successfully.");
        } catch (SQLException e) {
            System.err.println("Error inserting client: " + e.getMessage());
        }
    }

    public static Client getClient(int cin) {
        String query = "Select * FROM Client WHERE cin == ?;";
        connection = db.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, cin);
            
            try (ResultSet rs = preparedStatement.executeQuery()) {
                Client client;
                while (rs.next()) {
                    int cinResult = rs.getInt("Cin");
                    String firstName = rs.getString("FirstName");
                    String lastName = rs.getString("LastName");
                    String phoneNumber = rs.getString("PhoneNumber");
                    String email = rs.getString("Email");
                    String psswd = rs.getString("PasswordHash");
                    client = new Client(cinResult, firstName, lastName, phoneNumber, email, psswd);
                    System.out.println(cinResult + ": " + firstName + " " + lastName + " - " + phoneNumber);
                    return client;
                }
            }

        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
        }
        return null;
    }
    public static Vector<Client> getClients() {
        String query = "Select * FROM Client;";
        connection = db.getConnection();

        try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
            Client client;
            Vector<Client> clients = new Vector<>();
            while (rs.next()) {

                int cin = rs.getInt("Cin");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String phoneNumber = rs.getString("PhoneNumber");
                String email = rs.getString("Email");
                String psswd = rs.getString("PasswordHash");
                client = new Client(cin, firstName, lastName, phoneNumber, email, psswd);
                clients.add(client);
                System.out.println("Cin: " + cin + ", FirstName: " + firstName +
                        ", LastName: " + lastName + ", PhoneNumber: " + phoneNumber);
            }

            return clients;
        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
        }
        return null;
    }
}