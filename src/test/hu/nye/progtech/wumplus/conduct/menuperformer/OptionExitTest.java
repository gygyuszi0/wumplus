package hu.nye.progtech.wumplus.conduct.menuperformer;

import java.util.Optional;

import hu.nye.progtech.wumplus.model.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OptionExitTest {

    @Mock
    private GameState gameStateMock;

    private OptionExit underTest;

    @BeforeEach
    public void setUp() {
        underTest = new OptionExit();
    }

    @Test
    public void testPerformWithGameStatePresent() {
        // given
        Optional<GameState> gameState = Optional.of(gameStateMock);

        // when
        Optional<GameState> result = underTest.perform(gameState);

        // then
        assertTrue(result.isPresent());
        assertEquals(gameStateMock, result.get());
    }

    @Test
    public void testPerformWithGameStateNotPresent() {
        // given
        Optional<GameState> gameState = Optional.empty();

        // when
        Optional<GameState> result = underTest.perform(gameState);

        // then
        assertFalse(result.isPresent());
    }
}