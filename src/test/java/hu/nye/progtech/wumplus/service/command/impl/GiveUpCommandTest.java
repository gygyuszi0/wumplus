package hu.nye.progtech.wumplus.service.command.impl;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.constants.CommandConst;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GiveUpCommandTest {

    @Mock
    private GameState gameStateMock;

    private GiveUpCommand underTest;

    @BeforeEach
    public void setUp() {
        underTest = new GiveUpCommand();
    }
    @Test
    public void testCanProcessWithValidInput() {
        // given
        String validInput = CommandConst.GIVE_UP;

        // when
        boolean result = underTest.canProcess(validInput);

        // then
        assertTrue(result);
    }

    @Test
    public void testCanProcessWithInvalidInput() {
        // given
        String invalidInput = "INVALID";

        // when
        boolean result = underTest.canProcess(invalidInput);

        // then
        assertFalse(result);
    }

    @Test
    public void testProcessWithGameStatePresent() {
        // given
        when(gameStateMock.isGiveUp()).thenReturn(true);

        // when
        Optional<GameState> result = underTest.process(CommandConst.GIVE_UP, Optional.of(gameStateMock));

        // then
        assertTrue(result.isPresent());
        assertTrue(result.get().isGiveUp());
        verify(gameStateMock, times(1)).setGiveUp(true);
    }

    @Test
    public void testProcessWithGameStateNotPresent() {
        // when
        Optional<GameState> result = underTest.process(CommandConst.GIVE_UP, Optional.empty());

        // then
        assertFalse(result.isPresent());
    }
}