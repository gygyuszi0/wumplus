package hu.nye.progtech.wumplus.service.persister.database;

import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.PlayerWithMap;
import hu.nye.progtech.wumplus.model.constants.DBQuery;
import hu.nye.progtech.wumplus.service.exception.DBServiceException;
import hu.nye.progtech.wumplus.service.util.MapQuery;

import java.sql.*;

public class DatabaseService {
    private final String jdbcUrl;
    private final String resourceUrl;

    public DatabaseService() throws DBServiceException {
        this.resourceUrl = getClass().getClassLoader().getResource("").getPath();
        this.jdbcUrl = "jdbc:sqlite:" + resourceUrl + "wumpus";

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new DBServiceException("SQLite JDBC Driver nem található.");
        }
    }

    public void save(PlayerVO playerVO, MapVO mapVO) throws DBServiceException {
        if (haveThisPlayerHighScore(playerVO)) {
            updatePlayerHighScore(playerVO);
        } else {
            insertPlayerHighScore(playerVO);
        }
        if (haveThisPlayerSavedPlayer(playerVO)) {
            updatePlayerSavedPlayer(playerVO);
        } else {
            insertPlayerSavedPlayer(playerVO);
        }
        if (haveThisPlayerSavedMap(playerVO)) {
            updatePlayerSavedMap(playerVO, mapVO);
        } else {
            insertPlayerSavedMap(playerVO, mapVO);
        }

    }

    private boolean haveThisPlayerHighScore(PlayerVO playerVO) throws DBServiceException {
        boolean result = false;

        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {

            String selectQuery = "SELECT * FROM high_score WHERE player_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, playerVO.getName());
            ResultSet resultSet = preparedStatement.executeQuery();

            result = resultSet.next();
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            throw new DBServiceException("Error when search player high score: " + e.getMessage());
        }


        return result;
    }

    private void updatePlayerHighScore(PlayerVO playerVO) throws DBServiceException {
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            String updateQuery = "UPDATE high_score SET won_game = ? WHERE player_name = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setInt(1, playerVO.getScore());
            preparedStatement.setString(2, playerVO.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DBServiceException("Error when update player high score: " + e.getMessage());
        }
    }

    private void insertPlayerHighScore(PlayerVO playerVO) throws DBServiceException {
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            String insertQuery = "INSERT INTO high_score (player_name, won_game) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, playerVO.getName());
            preparedStatement.setInt(2, playerVO.getScore());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DBServiceException("Error when insert player high score: " + e.getMessage());
        }
    }

    private boolean haveThisPlayerSavedPlayer(PlayerVO playerVO) throws DBServiceException {
        boolean result = false;

        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {

            String selectQuery = DBQuery.SELECT_PLAYER_IN_SAVED_PLAYER;
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, playerVO.getName());
            ResultSet resultSet = preparedStatement.executeQuery();

            result = resultSet.next();
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            throw new DBServiceException("Error when search player saved state: " + e.getMessage());
        }
        return result;
    }

    private void updatePlayerSavedPlayer(PlayerVO playerVO) throws DBServiceException {
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            String updateQuery = DBQuery.UPDATE_PLAYER_IN_SAVED_PLAYER;
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, playerVO.getDirection().toString());
            preparedStatement.setInt(2, playerVO.getCoordX());
            preparedStatement.setInt(3, playerVO.getCoordY());
            preparedStatement.setInt(4, playerVO.getNumberOfArrows());
            preparedStatement.setBoolean(5, playerVO.getHaveGold());
            preparedStatement.setString(6, playerVO.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DBServiceException("Error when update player saved state: " + e.getMessage());
        }
    }

    private void insertPlayerSavedPlayer(PlayerVO playerVO) throws DBServiceException {
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            String insertQuery = DBQuery.INSERT_PLAYER_IN_SAVED_PLAYER;
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, playerVO.getName());
            preparedStatement.setString(2, playerVO.getDirection().toString());
            preparedStatement.setInt(3, playerVO.getCoordX());
            preparedStatement.setInt(4, playerVO.getCoordY());
            preparedStatement.setInt(5, playerVO.getNumberOfArrows());
            preparedStatement.setBoolean(6, playerVO.getHaveGold());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DBServiceException("Error when insert player saved state: " + e.getMessage());
        }
    }

    private boolean haveThisPlayerSavedMap(PlayerVO playerVO) throws DBServiceException {
        boolean result = false;

        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {

            String selectQuery = DBQuery.SELECT_PLAYER_IN_SAVED_MAP;
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, playerVO.getName());
            ResultSet resultSet = preparedStatement.executeQuery();

            result = resultSet.next();
            preparedStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            throw new DBServiceException("Error when search player saved map: " + e.getMessage());
        }
        return result;
    }

    private void updatePlayerSavedMap(PlayerVO playerVO, MapVO mapVO) throws DBServiceException {
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            String updateQuery = DBQuery.UPDATE_PLAYER_IN_SAVED_MAP;
            PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setString(1, MapQuery.serializeMap(mapVO));
            preparedStatement.setInt(2, mapVO.getNumberOfRows());
            preparedStatement.setString(3, playerVO.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DBServiceException("Error when update player saved map: " + e.getMessage());
        }
    }

    private void insertPlayerSavedMap(PlayerVO playerVO, MapVO mapVO) throws DBServiceException {
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            String insertQuery = DBQuery.INSERT_PLAYER_IN_SAVED_MAP;
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setString(1, playerVO.getName());
            preparedStatement.setString(2, MapQuery.serializeMap(mapVO));
            preparedStatement.setInt(3, mapVO.getNumberOfRows());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new DBServiceException("Error when insert player saved map: " + e.getMessage());
        }
    }

    public PlayerWithMap load(String playerName) throws DBServiceException {
        PlayerVO playerVO = loadPlayerFromSavedPlayer(playerName);
        MapVO mapVO = loadMapFromSavedMap(playerName);
        return new PlayerWithMap(playerVO, mapVO);
    }

    private PlayerVO loadPlayerFromSavedPlayer(String playerName) throws DBServiceException {
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            String selectQuery = DBQuery.SELECT_PLAYER_IN_SAVED_PLAYER;
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, playerName);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Character loadedDirection = resultSet.getString(DBQuery.SAVED_PLAYER_DIR).charAt(0);
            Integer coordX = resultSet.getInt(DBQuery.SAVED_PLAYER_POS_X);
            Integer coordY = resultSet.getInt(DBQuery.SAVED_PLAYER_POS_Y);
            Integer loadedArrow = resultSet.getInt(DBQuery.SAVED_PLAYER_NUM_ARROW);
            Boolean loadedGold = resultSet.getBoolean(DBQuery.SAVED_PLAYER_HAVE_GOLD);
            PlayerVO playerVO = new PlayerVO(playerName, loadedDirection, new CoordinateVO(coordX, coordY));
            playerVO.setNonStatic(loadedArrow, loadedGold, 0,0);
            return playerVO;
        } catch (SQLException e) {
            throw new DBServiceException("Error when load player from saved player: " + e.getMessage());
        }
    }

    private MapVO loadMapFromSavedMap(String playerName) throws DBServiceException {
        try (Connection connection = DriverManager.getConnection(jdbcUrl)) {
            String selectQuery = DBQuery.SELECT_PLAYER_IN_SAVED_MAP;
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, playerName);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            String loadedMap = resultSet.getString(DBQuery.SAVED_MAP_MAP);
            return MapQuery.deserializeMap(loadedMap);
        } catch (SQLException e) {
            throw new DBServiceException("Error when load map from saved map: " + e.getMessage());
        }
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
