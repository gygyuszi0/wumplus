package hu.nye.progtech.wumplus.service.command.impl;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.constants.CommandConst;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PauseCommandTest {

    @Mock
    private Logger loggerMock;

    @Mock
    private GameState gameStateMock;

    private PauseCommand underTest;


    @BeforeEach
    public void setUp() {
        underTest = new PauseCommand();
    }
    @Test
    public void testCanProcessWithValidInput() {
        // given
        String validInput = CommandConst.PAUSE;

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
        when(gameStateMock.deepCopy()).thenReturn(gameStateMock);
        when(gameStateMock.isPause()).thenReturn(true);

        // when
        Optional<GameState> result = underTest.process(CommandConst.PAUSE, Optional.of(gameStateMock));

        // then
        assertTrue(result.isPresent());
        assertTrue(result.get().isPause());
        verify(gameStateMock, times(1)).deepCopy();
        verify(gameStateMock, times(1)).setPause(true);
        verifyNoInteractions(loggerMock);
    }

    @Test
    public void testProcessWithGameStateNotPresent() {
        // given
        Optional<GameState> gameState = Optional.empty();
        // when
        Optional<GameState> result = underTest.process(CommandConst.PAUSE, gameState);
        // then
        assertFalse(result.isPresent());
    }
}