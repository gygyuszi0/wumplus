package hu.nye.progtech.wumplus.model.constants;

public class DBQuery {

    public static final String SELECT_PLAYER_FROM_HIGH_SCORE = "SELECT * FROM high_score WHEN player_name == ?";
    public static final String INSERT_PLAYER_TO_HIGH_SCORE = "INSERT INTO high_score (player_name, score) VALUES (?, ?)";
    public static final String UPDATE_PLAYER_SCORE_IN_HIGH_SCORE = "UPDATE high_score SET won_game = ? WHERE player_name = ?";

    public static final String HAVE_PLAYER_IN_SAVED_PLAYER = "SELECT * FROM saved_player WHERE name = ?";
    public static final String INSERT_PLAYER_IN_SAVED_PLAYER = "INSERT INTO saved_player (name, dir, pos_x, pos_y, num_arrow, have_gold) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String UPDATE_PLAYER_IN_SAVED_PLAYER = "UPDATE saved_player SET dir = ?, pos_x = ?, pos_y = ?, num_arrow = ?, have_gold = ? WHERE name = ?";
}
