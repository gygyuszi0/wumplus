package hu.nye.progtech.wumplus.service.persister.database;

import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;

import java.net.URL;
import java.sql.*;

public class DatabaseService {
    private final String jdbcUrl;
    private final String resourceUrl;

    public DatabaseService() {
        this.resourceUrl = getClass().getClassLoader().getResource("").getPath();
        this.jdbcUrl = "jdbc:sqlite:" + resourceUrl + "wumpus";

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC Driver nem található.");
            e.printStackTrace();
            return;
        }

        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            // Kapcsolat létrejött, itt végezze el a szükséges műveleteket
            connection.setAutoCommit(false);
            String insertQuery = "INSERT INTO high_score (player_name, won_game) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, "John Doe");
            preparedStatement.setInt(2, 100);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void save() {
        System.out.println("Saved to database");
    }

    public void load() {
        System.out.println("Loaded from database");
    }

    public void delete() {
        System.out.println("Deleted from database");
    }

    public void update() {
        System.out.println("Updated in database");
    }

    public void create() {
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            // Kapcsolat létrejött, itt végezze el a szükséges műveleteket
            System.out.println("Kapcsolódva az SQLite adatbázishoz.");
        } catch (SQLException e) {
            System.err.println("Hiba a kapcsolódás során: " + e.getMessage());
        }
        System.out.println("Created in database");
    }

    public void read() {
        System.out.println("Read from database");
    }

    public void write() {
        System.out.println("Written to database");
    }

    public void close() {
        System.out.println("Closed database");
    }

    public void open() {
        System.out.println("Opened database");
    }

    public void connect() {
        System.out.println("Connected to database");
    }

    public void disconnect() {
        System.out.println("Disconnected from database");
    }
}
