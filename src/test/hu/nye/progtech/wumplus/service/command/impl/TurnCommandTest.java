package hu.nye.progtech.wumplus.service.command.impl;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.constants.CommandConst;
import hu.nye.progtech.wumplus.service.command.performer.TurnPerformer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.constants.PlayerConst;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)

class TurnCommandTest {

    private TurnCommand underTest;
    private final String CORRECT_TURN_LEFT = "turn L";
    private final String CORRECT_TURN_RIGHT = "turn R";
    private final String WRONG_DIRECTION = "turn Z";
    private final String WRONG_COMMAND = "xzy R";
    private final PlayerVO PLAYER = new PlayerVO("teszt", PlayerConst.EAST, new CoordinateVO(1, 1));
    private final MapVO mapVO = new MapVO(6,6 ,
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

    private final GameState GAME_STATE = new GameState(mapVO, PLAYER, false, false);
    @Mock
    private TurnPerformer TURN_PREFORMER;


    @BeforeEach
    void setUp() {
        underTest = new TurnCommand(GAME_STATE, TURN_PREFORMER);
    }

    @Test
    void canProcessCorrectTurn() {
        System.out.println("[TEST\t] : Turn with both correct turn command");
        // given
        System.out.println("\t\t\tGIVEN\t:" + CORRECT_TURN_LEFT);
        System.out.println("\t\t\t\t\t:" + CORRECT_TURN_RIGHT);
        // when
        boolean result_left = underTest.canProcess(CORRECT_TURN_LEFT);
        boolean result_right = underTest.canProcess(CORRECT_TURN_RIGHT);
        System.out.println("\t\t\tWHEN\t:" + result_left);
        System.out.println("\t\t\t\t\t:" + result_right);
        // then
        Assertions.assertEquals(result_left, true);
        Assertions.assertEquals(result_right, true);
    }

    @Test
    void canProcessWrongDirection() {
        System.out.println("[TEST\t] : Turn with wrong direction Z");
        // given
        System.out.println("\t\t\tGIVEN\t:" + WRONG_DIRECTION);
        // when
        boolean result = underTest.canProcess(WRONG_DIRECTION);
        System.out.println("\t\t\tWHEN\t:" + result);
        // then
        Assertions.assertEquals(result, false);
    }

    @Test
    void canProcessWrongCommand() {
        System.out.println("[TEST\t] : Turn with wrong command xzy");
        // given
        System.out.println("\t\t\tGIVEN\t:" + WRONG_COMMAND);
        // when
        boolean result = underTest.canProcess(WRONG_COMMAND);
        System.out.println("\t\t\tWHEN\t:" + result);
        // then
        Assertions.assertEquals(result, false);
    }

    @Test
    void process() {
        System.out.println("[TEST\t] : TurnCommand do a correct turn");
        // given
        given(TURN_PREFORMER.perform(PLAYER, CommandConst.TURN_LEFT)).willReturn(new PlayerVO("teszt", PlayerConst.NORTH, new CoordinateVO(1,1)));
        System.out.println("\t\t\tGIVEN\t:" + PLAYER);
        System.out.println("\t\t\t\t\t:" + CORRECT_TURN_LEFT);
        // when
        underTest.process(CORRECT_TURN_LEFT);
        PlayerVO expectedPlayer = new PlayerVO("teszt", PlayerConst.NORTH, new CoordinateVO(1,1));
        GameState expected = new GameState(mapVO, expectedPlayer, false, false);
        System.out.println("\t\t\tWHEN\t:" + GAME_STATE);
        System.out.println("\t\t\t\t\t:" + underTest.getGameState());
        // then
        Assertions.assertEquals(underTest.getGameState(), expected);
        verify(TURN_PREFORMER).perform(PLAYER, CommandConst.TURN_LEFT);
    }
}