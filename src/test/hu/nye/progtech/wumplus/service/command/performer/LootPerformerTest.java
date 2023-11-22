package hu.nye.progtech.wumplus.service.command.performer;

import hu.nye.progtech.wumplus.service.exception.MapQueryException;
import hu.nye.progtech.wumplus.service.exception.PerformerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.constants.PlayerConst;

import static org.junit.jupiter.api.Assertions.*;

class LootPerformerTest {
    
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

    private PlayerVO PLAYER_CORRECT = new PlayerVO("teszt", PlayerConst.NORTH, new CoordinateVO(2, 3));
    private PlayerVO PLAYER_WRONG = new PlayerVO("teszt", PlayerConst.SOUTH, new CoordinateVO(2, 3));


    private LootPerformer underTest;
    @BeforeEach
    void setUp() {
        underTest = new LootPerformer();
    }

    @Test
    void performCorrect() throws PerformerException, MapQueryException {
        System.out.println("[TEST\t] : Perform a correct loot");
        // given
        System.out.println("\t\t\tGIVEN\t:" + MAP);
        System.out.println("\t\t\t\t\t:" + PLAYER_CORRECT);
        // when
        MapVO result = underTest.perform(PLAYER_CORRECT, MAP);
        MapVO expected = new MapVO(6, 6, 
            new char[][] {
                {'W', 'W', 'W', 'W', 'W', 'W'},
                {'W', '_', '_', '_', '_', 'W'},
                {'W', 'U', '_', 'P', '_', 'W'},
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
        System.out.println("\t\t\tWHEN\t:" + result);
        System.out.println("\t\t\t\t\t:" + expected);
        // then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void performWrong() {
        System.out.println("[TEST\t] : Perform a wrong loot, the coordinate does not contains gold");
        // given
        System.out.println("\t\t\tGIVEN\t:" + MAP);
        System.out.println("\t\t\t\t\t:" + PLAYER_WRONG);
        // when - then
        Exception result = Assertions.assertThrows(PerformerException.class, () -> underTest.perform(PLAYER_WRONG, MAP));
        String expected = "There are not gold front of the player.";
        System.out.println("\t\t\tWHEN\t:" + result.getMessage());
        System.out.println("\t\t\t\t\t:" + expected);
        // then
        Assertions.assertEquals(expected, result.getMessage());

    }
}