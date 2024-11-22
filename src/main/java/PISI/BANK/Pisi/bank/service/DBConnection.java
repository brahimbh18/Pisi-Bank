package PISI.BANK.Pisi.bank.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    String url = "jdbc:sqlite:pisi.db"; // Path to your SQLite database

    public Connection getConnection() {
        try {

            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database");
        }
    }
}
