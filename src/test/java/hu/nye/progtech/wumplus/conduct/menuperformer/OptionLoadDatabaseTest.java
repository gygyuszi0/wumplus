package hu.nye.progtech.wumplus.conduct.menuperformer;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.PlayerWithMap;
import hu.nye.progtech.wumplus.service.exception.DBServiceException;
import hu.nye.progtech.wumplus.service.persister.database.DatabaseService;
import hu.nye.progtech.wumplus.service.persister.json.JsonService;
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
public class OptionLoadDatabaseTest {

    @Mock
    private Logger loggerMock;

    @Mock
    private DatabaseService databaseServiceMock;

    @Mock
    private JsonService  jsonServiceMock;

    @Mock
    private GameState gameStateMock;

    @Mock
    private PlayerWithMap playerWithMapMock;
    @Mock
    private MapVO mapVOMock;

    @Mock
    private PlayerVO playerVOMock;


    private OptionLoadDatabase underTest;

    @BeforeEach
    public void setUp() {
        underTest = new OptionLoadDatabase(databaseServiceMock, jsonServiceMock);
    }

    @Test
    public void testPerformWithGameStatePresent() throws DBServiceException {
        // given
        String playerName = "testPlayer";
        when(gameStateMock.getPlayerName()).thenReturn(playerName);
        when(databaseServiceMock.load(playerName)).thenReturn(playerWithMapMock);
        when(playerWithMapMock.getMapVO()).thenReturn(mapVOMock);
        when(playerWithMapMock.getPlayerVO()).thenReturn(playerVOMock);

        // when
        Optional<GameState> result = underTest.perform(Optional.of(gameStateMock));

        // then
        assertTrue(result.isPresent());
        verify(databaseServiceMock, times(1)).load(playerName);
    }

    @Test
    public void testPerformWithGameStateNotPresent() throws DBServiceException {
        // given
        Optional<GameState> gameState = Optional.empty();

        // when
        Optional<GameState> result = underTest.perform(gameState);

        // then
        assertFalse(result.isPresent());
        verifyNoInteractions(databaseServiceMock, loggerMock);
    }

    @Test
    public void testPerformWithDBServiceException() throws DBServiceException {
        // given
        when(gameStateMock.getPlayerName()).thenReturn("testPlayer");
        when(databaseServiceMock.load(anyString())).thenThrow(DBServiceException.class);

        // when
        Optional<GameState> result = underTest.perform(Optional.of(gameStateMock));

        // then
        assertTrue(result.isPresent());
        assertEquals(gameStateMock, result.get());
    }
}