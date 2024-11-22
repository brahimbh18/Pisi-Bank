package PISI.BANK.Pisi.bank.service;

import PISI.BANK.Pisi.bank.model.Client;
import org.hibernate.annotations.processing.SQL;

import java.sql.*;

public class ClientService {
    static DBConnection db = new DBConnection();
    static Connection connection;
    public static void insertClient(Client client) throws SQLException {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS Client ("
                + "Cin INTEGER PRIMARY KEY, "
                + "FirstName TEXT, "
                + "LastName TEXT, "
                + "PhoneNumber TEXT"
                + ");";

        String insertQuery = "INSERT INTO Client (Cin, FirstName, LastName, PhoneNumber) VALUES (?, ?, ?, ?);";

        connection = db.getConnection();
        try (Statement stmt = connection.createStatement()){
            stmt.executeUpdate(createTableQuery);
        } catch (RuntimeException e) {
            System.err.println("Error creating table: " + e.getMessage());
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, client.getCin());
            preparedStatement.setString(2, client.getFirstName());
            preparedStatement.setString(3, client.getLastName());
            preparedStatement.setString(4, client.getPhoneNumber());
    
            preparedStatement.executeUpdate();
            System.out.println("Client inserted successfully.");
        } catch (SQLException e) {
            System.err.println("Error inserting client: " + e.getMessage());
        }
    }

    public static void showClients() {
        String query = "Select * FROM Client;";
        connection = db.getConnection();

        try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                // Assuming 'Cin', 'FirstName', 'LastName', and 'PhoneNumber' are the columns
                int cin = rs.getInt("Cin");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String phoneNumber = rs.getString("PhoneNumber");

                System.out.println("Cin: " + cin + ", FirstName: " + firstName +
                        ", LastName: " + lastName + ", PhoneNumber: " + phoneNumber);
            }
        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
        }
    }
}