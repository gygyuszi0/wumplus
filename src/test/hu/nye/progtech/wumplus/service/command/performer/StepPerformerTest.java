package hu.nye.progtech.wumplus.service.command.performer;

import hu.nye.progtech.wumplus.service.exception.MapQueryException;
import hu.nye.progtech.wumplus.service.exception.PerformerException;
import hu.nye.progtech.wumplus.service.exception.PlayerDeadException;
import hu.nye.progtech.wumplus.service.util.MapQuery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.constants.PlayerConst;

import static org.junit.jupiter.api.Assertions.*;

class StepPerformerTest {

    private StepPerformer underTest;

    private PlayerVO PLAYER_CORRECT = new PlayerVO("teszt", PlayerConst.EAST, new CoordinateVO(1, 1));
    private PlayerVO PLAYER_WALL = new PlayerVO("teszt", PlayerConst.NORTH, new CoordinateVO(1, 1));
    private PlayerVO PLAYER_PIT = new PlayerVO("teszt", PlayerConst.NORTH, new CoordinateVO(3, 3));
    private PlayerVO PLAYER_WUMPUS = new PlayerVO("teszt", PlayerConst.NORTH, new CoordinateVO(1, 3));

    private MapVO MAP = new MapVO(6, 6, 
        new char[][] {
            {'W', 'W', 'W', 'W', 'W', 'W'},
            {'W', '_', '_', '_', '_', 'W'},
            {'W', 'U', 'G', 'P', '_', 'W'},
            {'W', '_', '_', '_', '_', 'W'},
            {'W', '_', '_', 'P', '_', 'W'},
            {'W', 'W', 'W', 'W', 'W', 'W'}
        },
        new boolean[][] {
            {true, true, true, true, true, true},
            {true, false, false, false, false, true},
            {true, false, false, true, false, true},
            {true, false, false, false, false, true},
            {true, false, false, true, false, true},
            {true, true, true, true, true, true}
        }
    );

    @BeforeEach
    void setUp() {
        underTest = new StepPerformer();
    }

    @Test
    void performCorrect() throws PerformerException, MapQueryException, PlayerDeadException {
        System.out.println("[TEST\t] : Player can step");
        // given
        System.out.println("\t\t\tGIVEN\t:" + PLAYER_CORRECT);
        System.out.println("\t\t\t\t\t:" + MAP);
        // when
        PlayerVO result = underTest.perform(PLAYER_CORRECT, MAP);
        PlayerVO expected = new PlayerVO("teszt", PlayerConst.EAST, new CoordinateVO(2, 1));
        expected.setNonStatic(0, false, 0, 1);
        System.out.println("\t\t\tWHEN\t:" + result);
        System.out.println("\t\t\t\t\t:" + expected);
        // then
        Assertions.assertEquals(result, expected);
    }

    @Test
    void performWall() {
        System.out.println("[TEST\t] : Player try to step on a wall");
        // given
        System.out.println("\t\t\tGIVEN\t:" + PLAYER_WALL);
        System.out.println("\t\t\t\t\t:" + MAP);
        // when - then
        Exception result = Assertions.assertThrows(PerformerException.class, () -> underTest.perform(PLAYER_WALL, MAP));
        Assertions.assertEquals(result.getMessage(), "You can't step to the wall.");
        System.out.println("\t\t\tWHEN\t:" + result.getMessage());        
    }

    @Test
    void performPit() throws PerformerException, MapQueryException, PlayerDeadException {
        System.out.println("[TEST\t] : Player step to wumpus, then lose an arrow");
        // given
        PLAYER_PIT.setNumberOfArrows(3);
        System.out.println("\t\t\tGIVEN\t:" + PLAYER_PIT);
        System.out.println("\t\t\t\t\t:" + MAP);
        // when
        PlayerVO result = underTest.perform(PLAYER_PIT, MAP);
        PlayerVO expected = new PlayerVO("teszt", PlayerConst.NORTH, new CoordinateVO(3,2));
        expected.setNonStatic(2, false, 0, 1);
        System.out.println("\t\t\tWHEN\t:" + result);
        System.out.println("\t\t\t\t\t:" + expected);
        // then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void performWumpus() {
        System.out.println("[TEST\t] : Player step to wumpus, then die");
        // given
        System.out.println("\t\t\tGIVEN\t:" + PLAYER_WUMPUS);
        System.out.println("\t\t\t\t\t:" + MAP);
        // when - then
        Exception result = Assertions.assertThrows(PlayerDeadException.class, () -> underTest.perform(PLAYER_WUMPUS, MAP));
        Assertions.assertEquals(result.getMessage(), "Step to Wumpus, player is dead.");
        System.out.println("\t\t\tWHEN\t:" + result.getMessage());        
    }
}