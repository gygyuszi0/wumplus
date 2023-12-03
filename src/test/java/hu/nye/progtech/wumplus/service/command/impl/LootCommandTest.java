package hu.nye.progtech.wumplus.service.command.impl;

import hu.nye.progtech.wumplus.model.*;
import hu.nye.progtech.wumplus.model.constants.CommandConst;
import hu.nye.progtech.wumplus.service.command.performer.LootPerformer;
import hu.nye.progtech.wumplus.service.exception.MapQueryException;
import hu.nye.progtech.wumplus.service.exception.PerformerException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LootCommandTest {

    @Mock
    private LootPerformer lootPerformerMock;

    @Mock
    private Logger loggerMock;

    @Mock
    private PlayerVO playerVOMock;

    @Mock
    private MapVO mapVOMock;

    @Mock
    private GameState gameStateMock;

    private LootCommand underTest;

    @BeforeEach
    public void setUp() {
        underTest = new LootCommand(lootPerformerMock);
    }

    @Test
    public void testCanProcessWithValidInput() {
        // given
        String validInput = CommandConst.LOOT;

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
    public void testProcessWithGameStatePresent() throws PerformerException, MapQueryException {
        // given
        when(lootPerformerMock.perform(any(PlayerVO.class), any(MapVO.class))).thenReturn(new PlayerWithMap(playerVOMock, mapVOMock));
        when(gameStateMock.getPlayerVO()).thenReturn(playerVOMock);
        when(gameStateMock.getMapVO()).thenReturn(mapVOMock);

        // when
        Optional<GameState> result = underTest.process(CommandConst.LOOT, Optional.of(gameStateMock));

        // then
        assertTrue(result.isPresent());
        verify(lootPerformerMock, times(1)).perform(playerVOMock, mapVOMock);
        verify(gameStateMock, times(1)).setPlayerVO(playerVOMock);
        verify(gameStateMock, times(1)).setMapVO(mapVOMock);
        verifyNoInteractions(loggerMock);
    }

    @Test
    public void testProcessWithGameStateNotPresent() {
        // given
        Optional<GameState> gameState = Optional.empty();

        // when
        Optional<GameState> result = underTest.process(CommandConst.LOOT, gameState);

        // then
        assertFalse(result.isPresent());
    }

    @Test
    public void testProcessWithError() throws PerformerException, MapQueryException {
        // given
        when(lootPerformerMock.perform(any(PlayerVO.class), any(MapVO.class))).thenThrow(PerformerException.class);
        when(gameStateMock.getPlayerVO()).thenReturn(playerVOMock);
        when(gameStateMock.getMapVO()).thenReturn(mapVOMock);

        // when
        Optional<GameState> result = underTest.process(CommandConst.LOOT, Optional.of(gameStateMock));

        // then
        assertTrue(result.isPresent());
        assertEquals(Optional.of(gameStateMock), result);
    }
}