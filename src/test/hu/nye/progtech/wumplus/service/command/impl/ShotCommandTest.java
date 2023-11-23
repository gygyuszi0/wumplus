package hu.nye.progtech.wumplus.service.command.impl;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.service.command.performer.ShotPerformer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ShotCommandTest {

    private ShotCommand underTest;
    @Mock
    private ShotPerformer shotPerformer;

    @Mock
    private GameState gameState;

    private String COMMAND_CORRECT = "shot";
    private String COMMAND_WRONG = "sfd";

    @BeforeEach
    void setUp() {
        underTest = new ShotCommand(gameState, shotPerformer);
    }

    @Test
    void canProcessCorrect() {
        System.out.println("[TEST\t] : Shot command can process with correct command");
        // given
        System.out.println("\t\t\tGIVEN\t:" + COMMAND_CORRECT);
        // when 
        boolean result = underTest.canProcess(COMMAND_CORRECT);
        System.out.println("\t\t\tWHEN\t:" + result);
        // then
        Assertions.assertEquals(result, true);
    }

    @Test
    void canProcessWrong() {
        System.out.println("[TEST\t] : Shot command can process with wrong command");
        // given
        System.out.println("\t\t\tGIVEN\t:" + COMMAND_WRONG);
        // when
        boolean result = underTest.canProcess(COMMAND_WRONG);
        System.out.println("\t\t\tWHEN\t:" + result);
        // then
        Assertions.assertEquals(result, false);
    }

    @Test
    void process() {
    }
}