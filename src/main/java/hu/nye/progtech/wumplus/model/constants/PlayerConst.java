package hu.nye.progtech.wumplus.model.constants;

import java.util.List;

/**
 * Játkos állapotait tartalmazó konstansok.
 */
public class PlayerConst {
    public static final Character EAST = 'E';
    public static final Character WEST = 'W';
    public static final Character NORTH = 'N';
    public static final Character SOUTH = 'S';
    public static final List<Character> CORRECT_DIRECTIONS = List.of(EAST, WEST, NORTH, SOUTH);

    private PlayerConst() {
    }
}
