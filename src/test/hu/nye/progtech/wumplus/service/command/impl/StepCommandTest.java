package hu.nye.progtech.wumplus.service.command.impl;

import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.constants.CommandConst;
import hu.nye.progtech.wumplus.model.constants.PlayerConst;
import hu.nye.progtech.wumplus.service.command.performer.StepPerformer;
import hu.nye.progtech.wumplus.service.exception.MapQueryException;
import hu.nye.progtech.wumplus.service.exception.PerformerException;
import hu.nye.progtech.wumplus.service.exception.PlayerDeadException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

import java.util.Map;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class StepCommandTest {


    private StepCommand underTest;
    @Mock
    private StepPerformer stepPerformerMock;

    private GameState gameState;

    private String STEP_CORRECT = CommandConst.STEP;
    private String STEP_WRONG = "LYJHCXF";

    @BeforeEach
    void setUp() {
        PlayerVO PLAYER = new PlayerVO("teszt", PlayerConst.NORTH, new CoordinateVO(1,2), new CoordinateVO(1, 1));
        PLAYER.setNonStatic(3, false, 0, 0);
        gameState = new GameState(null, PLAYER, false, false);
        underTest = new StepCommand(stepPerformerMock);
    }

    @Test
    void canProcessCorrect() {
        System.out.println("[TEST\t] : Step with correct command");
        // given
        System.out.println("\t\t\tGIVEN\t:" + STEP_CORRECT);
        // when
        boolean result = underTest.canProcess(STEP_CORRECT);
        System.out.println("\t\t\tWHEN\t:" + result);
        // then
        Assertions.assertEquals(result, true);
    }

    @Test
    void canProcessWrong() {
        System.out.println("[TEST\t] : Step with wrong command");
        // given
        System.out.println("\t\t\tGIVEN\t:" + STEP_WRONG);
        // when
        boolean result = underTest.canProcess(STEP_WRONG);
        System.out.println("\t\t\tWHEN\t:" + result);
        // then
        Assertions.assertEquals(result, false);
    }

    @Test
    void processCorrect() throws PerformerException, MapQueryException, PlayerDeadException {
        System.out.println("[TEST\t] : Perform a correct step command");

        // given
        System.out.println("\t\t\tGIVEN\t:" + gameState);
        System.out.println("\t\t\t\t\t:" + STEP_CORRECT);

        PlayerVO PLAYER_STEPED = new PlayerVO("teszt", PlayerConst.NORTH, new CoordinateVO(1,1), new CoordinateVO(1, 1));
        PLAYER_STEPED.setNonStatic(0,false,0,1);
        given(stepPerformerMock.perform(any(), any())).willReturn(PLAYER_STEPED);

        // when
        Optional<GameState> result = underTest.process(STEP_CORRECT, Optional.of(gameState));
        Optional<GameState> expected = Optional.of(new GameState(null, PLAYER_STEPED, false, false));
        System.out.println("\t\t\t\t\t:" + expected.get().getPlayerVO());
        System.out.println("\t\t\t\t\t:" + result.get().getPlayerVO());


        // then
        Assertions.assertEquals(expected, result);
        verify(stepPerformerMock).perform(any(), any());

    }

    @Test
    void processWall() throws PerformerException, MapQueryException, PlayerDeadException {
        System.out.println("[TEST\t] : Try to perform a step to the wall");

        // given
        System.out.println("\t\t\tGIVEN\t:" + gameState);
        System.out.println("\t\t\t\t\t:" + STEP_CORRECT);
        
        given(stepPerformerMock.perform(any(), any())).willThrow(new PerformerException("You can't step to the wall."));
        // when
        Optional<GameState> result = underTest.process(STEP_CORRECT, Optional.of(gameState));

        PlayerVO expectedPlayer = new PlayerVO("teszt", PlayerConst.NORTH, new CoordinateVO(1,2), new CoordinateVO(1, 1));
        expectedPlayer.setNonStatic(3,false, 0, 0);
        Optional<GameState> expected = Optional.of(new GameState(null, expectedPlayer, false, false));
        // then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void processPit() throws PlayerDeadException, PerformerException, MapQueryException {
        System.out.println("[TEST\t] : Perform a correct step command to the pit");

        // given
        System.out.println("\t\t\tGIVEN\t:" + gameState);
        System.out.println("\t\t\t\t\t:" + STEP_CORRECT);

        PlayerVO PLAYER_STEPED = new PlayerVO("teszt", PlayerConst.NORTH, new CoordinateVO(1,1), new CoordinateVO(1, 1));
        PLAYER_STEPED.setNonStatic(2,false, 0, 1);
        given(stepPerformerMock.perform(any(), any())).willReturn(PLAYER_STEPED);

        // when
        Optional<GameState> result = underTest.process(STEP_CORRECT, Optional.of(gameState));
        Optional<GameState> expected = Optional.of(new GameState(null, PLAYER_STEPED, false, false));

        // then
        Assertions.assertEquals(expected, result);
        verify(stepPerformerMock).perform(any(), any());
    }

    @Test
    void processWumpus() throws PlayerDeadException, PerformerException, MapQueryException {
        System.out.println("[TEST\t] : Step to the wumpus, then player die");

        // given
        System.out.println("\t\t\tGIVEN\t:" + gameState);
        System.out.println("\t\t\t\t\t:" + STEP_CORRECT);
        
        given(stepPerformerMock.perform(any(), any())).willThrow(new PlayerDeadException("Step to Wumpus, player is dead."));
        // when
        Optional<GameState> result = underTest.process(STEP_CORRECT, Optional.of(gameState));
        PlayerVO expectedPlayer = new PlayerVO("teszt", PlayerConst.NORTH, new CoordinateVO(1,2), new CoordinateVO(1, 1));
        expectedPlayer.setNonStatic(3,false, 0, 0);
        GameState expected = new GameState(null, expectedPlayer, false, false);
        expected.setPlayerDead(true);
        System.out.println("\t\t\t\t\t:" + expected);

        // then
        Assertions.assertEquals(expected, gameState);
        
    }
}