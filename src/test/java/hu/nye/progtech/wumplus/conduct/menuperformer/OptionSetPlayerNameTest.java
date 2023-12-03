package hu.nye.progtech.wumplus.conduct.menuperformer;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.constants.PlayerConst;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OptionSetPlayerNameTest {

    private OptionSetPlayerName underTest;

    private static final String PLAYER_NAME = "TestPlayer";

    @Mock
    private GameState gameState;

    @BeforeEach
    public void setUp() {
        underTest = new OptionSetPlayerName(PLAYER_NAME);
    }
    @Test
    public void testPerform() {
        // Arrange
        String playerName = "TestPlayer";
        Optional<GameState> gameStateMock = Optional.of(gameState);

        // Act
        Optional<GameState> result = underTest.perform(gameStateMock);

        // Assert
        assertTrue(result.isPresent());

        GameState gameStateResult = result.get();
        assertNotNull(gameStateResult);

        PlayerVO playerVO = gameStateResult.getPlayerVO();

        assertNotNull(playerVO);
        assertEquals(playerName, playerVO.getName());
        assertEquals(PlayerConst.NORTH, playerVO.getDirection());

        MapVO mapVO = gameStateResult.getMapVO();
        assertNotNull(mapVO);
        assertEquals(0, mapVO.getNumberOfColumns());
        assertEquals(0, mapVO.getNumberOfRows());
        assertEquals(1, mapVO.getMap().length);
        assertEquals(0, mapVO.getMap()[0].length);

        assertFalse(gameStateResult.isPlayerDead());
        assertFalse(gameStateResult.isPlayerWon());
    }
}