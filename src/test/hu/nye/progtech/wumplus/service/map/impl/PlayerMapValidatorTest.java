package hu.nye.progtech.wumplus.service.map.impl;

import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.Element;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.service.exception.MapValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    private static final PlayerVO PLAYER_CORRECT = new PlayerVO("teszt", Element.EAST, new CoordinateVO(1,1));
    private static final PlayerVO PLAYER_ON_WALL = new PlayerVO("teszt", Element.EAST, new CoordinateVO(0,1));
    private static final PlayerVO PLAYER_ON_PIT = new PlayerVO("teszt", Element.EAST, new CoordinateVO(4,3));
    private static final PlayerVO PLAYER_ON_WUMPUS = new PlayerVO("teszt", Element.EAST, new CoordinateVO(1,4));

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
}