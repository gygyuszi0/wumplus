package hu.nye.progtech.wumplus.service.command.impl;

import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.constants.CommandConst;
import hu.nye.progtech.wumplus.service.command.performer.StepPerformer;
import hu.nye.progtech.wumplus.service.exception.MapQueryException;
import hu.nye.progtech.wumplus.service.exception.PerformerException;
import hu.nye.progtech.wumplus.service.exception.PlayerDeadException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Map;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StepCommandTest {

    @Mock
    private StepPerformer stepPerformerMock;

    @Mock
    private GameState gameStateMock;

    @Mock
    private PlayerVO playerVOMock;
    @Mock
    private CoordinateVO  coordinateVOMock;

    private StepCommand underTest;

    @BeforeEach
    public void setUp() {
        underTest = new StepCommand(stepPerformerMock);
    }

    @Test
    public void testCanProcess() {
        // Act
        boolean result = underTest.canProcess(CommandConst.STEP);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testCanProcessWithInvalidCommand() {
        // Act
        boolean result = underTest.canProcess("INVALID_COMMAND");

        // Assert
        assertFalse(result);
    }

    @Test
    public void testProcessWithGameStatePresentAndNoExceptions() throws PerformerException, PlayerDeadException, MapQueryException {
        // Arrange
        when(gameStateMock.getPlayerVO()).thenReturn(playerVOMock);
        when(stepPerformerMock.perform(playerVOMock, gameStateMock.getMapVO())).thenReturn(playerVOMock);
        when(playerVOMock.getCoordinate()).thenReturn(coordinateVOMock);

        // Act
        Optional<GameState> result = underTest.process(CommandConst.STEP, Optional.of(gameStateMock));

        // Assert
        assertTrue(result.isPresent());
        assertEquals(gameStateMock, result.get());
        verify(gameStateMock, times(1)).setPlayerVO(playerVOMock);
    }

    @Test
    public void testProcessWithGameStatePresentAndPerformerException() throws PerformerException, PlayerDeadException, MapQueryException {
        // Arrange
        when(gameStateMock.getPlayerVO()).thenReturn(playerVOMock);
        when(stepPerformerMock.perform(playerVOMock, gameStateMock.getMapVO())).thenThrow(PerformerException.class);

        // Act
        Optional<GameState> result = underTest.process(CommandConst.STEP, Optional.of(gameStateMock));

        // Assert
        assertTrue(result.isPresent());
        assertEquals(gameStateMock, result.get());
    }

    @Test
    public void testProcessWithGameStatePresentAndPlayerDeadException() throws PerformerException, PlayerDeadException, MapQueryException {
        // Arrange
        when(gameStateMock.getPlayerVO()).thenReturn(playerVOMock);
        when(stepPerformerMock.perform(playerVOMock, gameStateMock.getMapVO())).thenThrow(PlayerDeadException.class);

        // Act
        Optional<GameState> result = underTest.process(CommandConst.STEP, Optional.of(gameStateMock));

        // Assert
        assertTrue(result.isPresent());
        assertEquals(gameStateMock, result.get());
        verify(gameStateMock, times(1)).setPlayerDead(true);
    }

    @Test
    public void testProcessWithGameStatePresentAndMapQueryException() throws PerformerException, PlayerDeadException, MapQueryException {
        // Arrange
        when(gameStateMock.getPlayerVO()).thenReturn(playerVOMock);
        when(stepPerformerMock.perform(playerVOMock, gameStateMock.getMapVO())).thenThrow(MapQueryException.class);

        // Act
        Optional<GameState> result = underTest.process(CommandConst.STEP, Optional.of(gameStateMock));

        // Assert
        assertTrue(result.isPresent());
        assertEquals(gameStateMock, result.get());
    }

    @Test
    public void testProcessWithNoGameState() {
        // Arrange
        Optional<GameState> gameStateParam = Optional.empty();

        // Act
        Optional<GameState> result = underTest.process(CommandConst.STEP, gameStateParam);

        // Assert
        assertFalse(result.isPresent());
    }
}