package hu.nye.progtech.wumplus.conduct.menuperformer;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.service.exception.DBServiceException;
import hu.nye.progtech.wumplus.service.persister.database.DatabaseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OptionSaveToDatabaseTest {

    @Mock
    private DatabaseService databaseServiceMock;

    @Mock
    private Logger loggerMock;

    @Mock
    private GameState gameStateMock;

    @Mock
    private PlayerVO playerVOMock;

    @Mock
    private MapVO mapVOMock;

    private OptionSaveToDatabase underTest;

    @BeforeEach
    public void setUp() {
        underTest = new OptionSaveToDatabase(databaseServiceMock);
        underTest.setLogger(loggerMock);
    }

    @Test
    public void testPerformWithGameStatePresent() throws DBServiceException {
        // Arrange
        Optional<GameState> gameStateParam = Optional.of(gameStateMock);

        // Act
        Optional<GameState> result = underTest.perform(gameStateParam);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(gameStateMock, result.get());
        verify(databaseServiceMock, times(1)).save(any(), any());
        verifyNoInteractions(loggerMock);
    }

    @Test
    public void testPerformWithGameStateNotPresent() throws DBServiceException {
        // Arrange
        Optional<GameState> gameStateParam = Optional.empty();

        // Act
        Optional<GameState> result = underTest.perform( gameStateParam);

        // Assert
        assertFalse(result.isPresent());
        verify(loggerMock, times(1)).info("No game state to save");
        verifyNoInteractions(databaseServiceMock);
    }

    @Test
    public void testPerformWithDBServiceException() throws DBServiceException {
        // Arrange
        doThrow(DBServiceException.class).when(databaseServiceMock).save(any(), any());
        Optional<GameState> gameStateParam = Optional.of(gameStateMock);

        // Act
        Optional<GameState> result = underTest.perform(gameStateParam);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(gameStateMock, result.get());
        verify(loggerMock, times(1)).error("Error saving to database");
    }
}