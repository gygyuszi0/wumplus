package hu.nye.progtech.wumplus.service.command.impl;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.constants.CommandConst;
import hu.nye.progtech.wumplus.service.command.performer.StepPerformer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StepCommandTest {


    private StepCommand underTest;
    @Mock
    private StepPerformer stepPerformerMock;

    private GameState gameState = new GameState(null, null, false, false);

    private String STEP_CORRECT = CommandConst.STEP;
    private String STEP_WRONG = "LYJHCXF";

    @BeforeEach
    void setUp() {
        underTest = new StepCommand(gameState, stepPerformerMock);
    }

    @Test
    void canProcessCorrect() {
        System.out.println("[TEST\t] : Step with correct command");
        // given
        System.out.println("\t\t\tGIVEN\t:" + STEP_CORRECT);
        System.out.println("\t\t\t\t\t:" + gameState);
        // when
        boolean result = underTest.canProcess(STEP_CORRECT);
        System.out.println("\t\t\tWHEN\t:" + result);
        // then
        Assertions.assertEquals(result, true);
    }

    @Test
    void process() {
    }
}