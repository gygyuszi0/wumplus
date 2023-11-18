package hu.nye.progtech.wumplus.service.map.impl;

import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.constants.PlayerConst;
import hu.nye.progtech.wumplus.service.exception.MapValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerMapValidatorTest {
    private static final char[][] MAP = {
        {'W', 'W', 'W', 'W', 'W', 'W'},
        {'W', '_', '_', '_', '_', 'W'},
        {'W', '_', '_', '_', '_', 'W'},
        {'W', '_', '_', '_', 'P', 'W'},
        {'W', 'U', '_', '_', '_', 'W'},
        {'W', 'W', 'W', 'W', 'W', 'W'}
};
    private static final boolean[][] FIXED = {
        {true,  true,   true,   true,   true,   true},
        {true,  false,  false,  false,  false,  true},
        {true,  false,  false,  false,  false,  true},
        {true,  false,  false,  false,  true,   true},
        {true,  false,  false,  false,  false,  true},
        {true,  true,   true,   true,   true,   true},
    };

    private static final PlayerVO PLAYER_CORRECT = new PlayerVO("teszt", PlayerConst.EAST, new CoordinateVO(1,1));
    private static final PlayerVO PLAYER_ON_WALL = new PlayerVO("teszt", PlayerConst.EAST, new CoordinateVO(0,1));
    private static final PlayerVO PLAYER_ON_PIT = new PlayerVO("teszt", PlayerConst.EAST, new CoordinateVO(4,3));
    private static final PlayerVO PLAYER_ON_WUMPUS = new PlayerVO("teszt", PlayerConst.EAST, new CoordinateVO(1,4));
    private static final PlayerVO PLAYER_NOT_ON_MAPX6 = new PlayerVO("teszt", PlayerConst.EAST, new CoordinateVO(6,4));
    private static final PlayerVO PLAYER_NOT_ON_MAPX = new PlayerVO("teszt", PlayerConst.EAST, new CoordinateVO(-1,4));

    private static final MapVO MAPVO = new MapVO(6, 6, MAP, FIXED);
    private static PlayerMapValidator underTest;
    @BeforeEach
    void setUp() {
    }

    @Test
    void CorrectPlayer() throws MapValidationException {
        System.out.println("[TEST\t] : Check a player with correct coordinate");
        System.out.println("\t\t\tGIVEN\t:" + MAPVO);
        System.out.println("\t\t\t\t\t:" + PLAYER_CORRECT);

        underTest = new PlayerMapValidator(PLAYER_CORRECT);

        boolean result = underTest.validateMap(MAPVO);
        System.out.println("\t\t\tWHEN\t:" + result);

        Assertions.assertEquals(result, true);
    }

    @Test
    void OnWall() {
        System.out.println("[TEST\t] : Validate map with a player on a wall");
        System.out.println("\t\t\tGIVEN\t:" + MAPVO);
        System.out.println("\t\t\t\t\t:" + PLAYER_ON_WALL);

        underTest = new PlayerMapValidator(PLAYER_ON_WALL);

        Exception exception = Assertions.assertThrows(MapValidationException.class, () -> underTest.validateMap(MAPVO));
        Assertions.assertEquals(exception.getMessage(), "Player on wall");
        System.out.println("\t\t\tTHEN\t:" + exception.getMessage());
    }

    @Test
    void OnPit() {
        System.out.println("[TEST\t] : Validate map with a player on a pit");
        System.out.println("\t\t\tGIVEN\t:" + MAPVO);
        System.out.println("\t\t\t\t\t:" + PLAYER_ON_PIT);

        underTest = new PlayerMapValidator(PLAYER_ON_PIT);

        Exception exception = Assertions.assertThrows(MapValidationException.class, () -> underTest.validateMap(MAPVO));
        Assertions.assertEquals(exception.getMessage(), "Player on pit");
        System.out.println("\t\t\tTHEN\t:" + exception.getMessage());


    }

    @Test
    void OnWumpus() {
        System.out.println("[TEST\t] : Validate map with a player on a Wumpus");
        System.out.println("\t\t\tGIVEN\t:" + MAPVO);
        System.out.println("\t\t\t\t\t:" + PLAYER_ON_WUMPUS);
        
        underTest = new PlayerMapValidator(PLAYER_ON_WUMPUS);

        Exception exception = Assertions.assertThrows(MapValidationException.class, () -> underTest.validateMap(MAPVO));
        Assertions.assertEquals(exception.getMessage(), "Player on Wumpus");
        System.out.println("\t\t\tTHEN\t:" + exception.getMessage());
    }

    @Test
    void NotOnMapX() {
        System.out.println("[TEST\t] : Validate map with a player out of map (x-coordinate = -1)");
        System.out.println("\t\t\tGIVEN\t:" + MAPVO);
        System.out.println("\t\t\t\t\t:" + PLAYER_NOT_ON_MAPX);

        underTest = new PlayerMapValidator(PLAYER_NOT_ON_MAPX);

        Exception exception = Assertions.assertThrows(MapValidationException.class, () -> underTest.validateMap(MAPVO));
        Assertions.assertEquals(exception.getMessage(), "Player not on map, x-coordinate out of range");
        System.out.println("\t\t\tTHEN\t:" + exception.getMessage());
    }

    @Test
    void NotOnMapX6() {
        System.out.println("[TEST\t] : Validate map with a player out of map (x-coordinate = 6)");
        System.out.println("\t\t\tGIVEN\t:" + MAPVO);
        System.out.println("\t\t\t\t\t:" + PLAYER_NOT_ON_MAPX6);

        underTest = new PlayerMapValidator(PLAYER_NOT_ON_MAPX6);

        Exception exception = Assertions.assertThrows(MapValidationException.class, () -> underTest.validateMap(MAPVO));
        Assertions.assertEquals(exception.getMessage(), "Player not on map, x-coordinate out of range");
        System.out.println("\t\t\tTHEN\t:" + exception.getMessage());
    }
}