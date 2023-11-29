package hu.nye.progtech.wumplus.model.constants;

public class DBQuery {

    public static final String SELECT_PLAYER_FROM_HIGH_SCORE = "SELECT * FROM high_score WHEN player_name == ?";
    public static final String INSERT_PLAYER_TO_HIGH_SCORE = "INSERT INTO high_score (player_name, score) VALUES (?, ?)";
    public static final String UPDATE_PLAYER_SCORE_IN_HIGH_SCORE = "UPDATE high_score SET won_game = ? WHERE player_name = ?";



    public static final String SAVED_PLAYER_NAME = "name";
    public static final String SELECT_PLAYER_IN_SAVED_PLAYER = "SELECT * FROM saved_player WHERE " + SAVED_PLAYER_NAME + " = ?";
    public static final String SAVED_PLAYER_DIR = "dir";
    public static final String SAVED_PLAYER_POS_X = "pos_x";
    public static final String SAVED_PLAYER_POS_Y = "pos_y";
    public static final String SAVED_PLAYER_NUM_ARROW = "num_arrow";
    public static final String SAVED_PLAYER_HAVE_GOLD = "have_gold";
    public static final String INSERT_PLAYER_IN_SAVED_PLAYER = "INSERT INTO saved_player (" + SAVED_PLAYER_NAME + ", " + SAVED_PLAYER_DIR + ", " + SAVED_PLAYER_POS_X + ", " + SAVED_PLAYER_POS_Y + ", " + SAVED_PLAYER_NUM_ARROW + ", " + SAVED_PLAYER_HAVE_GOLD + ") VALUES (?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_PLAYER_IN_SAVED_PLAYER = "UPDATE saved_player SET " + SAVED_PLAYER_DIR + " = ?, " + SAVED_PLAYER_POS_X + " = ?, " + SAVED_PLAYER_POS_Y + " = ?, " + SAVED_PLAYER_NUM_ARROW + " = ?, " + SAVED_PLAYER_HAVE_GOLD + " = ? WHERE " + SAVED_PLAYER_NAME + " = ?";

    public static final String SAVED_MAP_MAP = "map";
    public static final String SELECT_PLAYER_IN_SAVED_MAP = "SELECT * FROM saved_" + SAVED_MAP_MAP + " WHERE " + SAVED_PLAYER_NAME + " = ?";
    public static final String SAVED_MAP_DIM = "dim";
    public static final String INSERT_PLAYER_IN_SAVED_MAP = "INSERT INTO saved_" + SAVED_MAP_MAP + " (" + SAVED_PLAYER_NAME + ", " + SAVED_MAP_MAP + ", " + SAVED_MAP_DIM + ") VALUES (?, ?, ?)";
    public static final String UPDATE_PLAYER_IN_SAVED_MAP = "UPDATE saved_" + SAVED_MAP_MAP + " SET map = ?, " + SAVED_MAP_DIM + " = ? WHERE " + SAVED_PLAYER_NAME + " = ?";


}
