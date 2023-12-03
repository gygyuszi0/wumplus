package hu.nye.progtech.wumplus.conduct.menuperformer;

import java.util.Optional;

import hu.nye.progtech.wumplus.conduct.gamecontroller.Controller;
import hu.nye.progtech.wumplus.model.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class OptionPlayTest {

    @Mock
    private Controller controllerMock;

    @Mock
    private Logger loggerMock;

    @Mock
    private GameState gameStateMock;
    @Mock
    private GameState expectedGameStateMock;

    private OptionPlay underTest;

    @BeforeEach
    public void setUp() {
        underTest = new OptionPlay(controllerMock);
    }

    @Test
    public void testPerformWithGameStatePresent() {
        // given
        Optional<GameState> expectedResult = Optional.of(expectedGameStateMock);
        when(controllerMock.startGame(any(Optional.class))).thenReturn(expectedResult);

        // when
        Optional<GameState> result = underTest.perform(Optional.of(gameStateMock));

        // then
        assertEquals(expectedResult, result);
        verify(controllerMock, times(1)).startGame(any(Optional.class));
        verifyNoInteractions(loggerMock);
    }

    @Test
    public void testPerformWithGameStateNotPresent() {
        // given
        Optional<GameState> emptyGameState = Optional.empty();
        
        // when
        Optional<GameState> result = underTest.perform(emptyGameState);

        // then
        assertFalse(result.isPresent());
        verifyNoInteractions(controllerMock);
    }
}