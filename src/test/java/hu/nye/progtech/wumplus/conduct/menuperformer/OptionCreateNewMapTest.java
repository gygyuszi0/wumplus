package hu.nye.progtech.wumplus.conduct.menuperformer;

import hu.nye.progtech.wumplus.model.GameState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OptionCreateNewMapTest {

    @Mock
    private GameState gameStateMock;

    private OptionCreateNewMap underTest;

    @BeforeEach
    public void setUp() {
        underTest = new OptionCreateNewMap();
    }

    @Test
    public void testPerformWithGameStatePresent() {
        // given
        // when
        Optional<GameState> result = underTest.perform(Optional.of(gameStateMock));

        // then
        assertTrue(result.isPresent());
        assertEquals(gameStateMock, result.get());
    }

    @Test
    public void testPerformWithGameStateNotPresent() {
        // given
        Optional<GameState> emptyGameState = Optional.empty();
        
        // when
        Optional<GameState> result = underTest.perform(emptyGameState);

        // then
        assertFalse(result.isPresent());
    }
}