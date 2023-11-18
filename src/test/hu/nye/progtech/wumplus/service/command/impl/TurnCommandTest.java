package hu.nye.progtech.wumplus.service.command.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TurnCommandTest {

    private TurnCommand underTest;
    private final String CORRECT_TURN_LEFT = "turn L";
    private final String CORRECT_TURN_RIGHT = "turn R";
    private final String WRONG_DIRECTION = "turn Z";
    private final String WRONG_COMMAND = "xzy R";

    @BeforeEach
    void setUp() {
        underTest = new TurnCommand();
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
    }
}