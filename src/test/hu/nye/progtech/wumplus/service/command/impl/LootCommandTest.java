package hu.nye.progtech.wumplus.service.command.impl;

import hu.nye.progtech.wumplus.model.*;
import hu.nye.progtech.wumplus.model.constants.PlayerConst;
import hu.nye.progtech.wumplus.service.command.performer.LootPerformer;
import hu.nye.progtech.wumplus.service.exception.MapQueryException;
import hu.nye.progtech.wumplus.service.exception.PerformerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LootCommandTest {


    private LootCommand underTest;
    @Mock
    private LootPerformer lootPerformer;
    private GameState gameState;

    private String LOOT_CORRECT = "loot";
    private String LOOT_WRONG = "YDFXGFD";

    private MapVO MAP_EXPECTED_CORRECT = new MapVO(6, 6, 
        new char[][] {
            {'W', 'W', 'W', 'W', 'W', 'W'},
            {'W', '_', '_', '_', '_', 'W'},
            {'W', 'U', '_', 'P', '_', 'W'},
            {'W', '_', '_', '_', '_', 'W'},
            {'W', '_', '_', 'P', '_', 'W'},
            {'W', 'W', 'W', 'W', 'W', 'W'}
        },
        new boolean[][] {
            {true, true, true, true, true, true},
            {true, false, false, false, false, true},
            {true, false, false, true, false, true},
            {true, false, false, false, false, true},
            {true, false, false, true, false, true},
            {true, true, true, true, true, true}
        }
    );
    private PlayerVO PLAYER_CORRECT = new PlayerVO("teszt", PlayerConst.NORTH, new CoordinateVO(2, 3), new CoordinateVO(1, 1));

    private GameState GAMESTATE_CORRECT_ECPECTED = new GameState(MAP_EXPECTED_CORRECT,PLAYER_CORRECT, false, false);
    
    @BeforeEach
    void setUp() {
        gameState = new GameState(null, PLAYER_CORRECT, false, false);
        underTest = new LootCommand(lootPerformer);
    }

    @Test
    void canProcessCorrect() {
        System.out.println("[TEST\t] : Can process a correct loot command");
        // given
        System.out.println("\t\t\tGIVEN\t:" + LOOT_CORRECT);
        // then
        boolean result = underTest.canProcess(LOOT_CORRECT);
        System.out.println("\t\t\tWHEN\t:" + result);
        // when
        Assertions.assertEquals(result, true);
        
    }

    @Test
    void canProcessWrong() {
        System.out.println("[TEST\t] : Can process a wrong loot command");
        // given
        System.out.println("\t\t\tGIVEN\t:" + LOOT_WRONG);
        // then
        boolean result = underTest.canProcess(LOOT_WRONG);
        System.out.println("\t\t\tWHEN\t:" + result);
        // when
        Assertions.assertEquals(result, false);
    }

    @Test
    void processCorrect() throws PerformerException, MapQueryException {
        System.out.println("[TEST\t] : Process a correct loot command");
        // given
        System.out.println("\t\t\tGIVEN\t:" + Optional.of(GAMESTATE_CORRECT_ECPECTED));
        PlayerVO returnedPlayer = PLAYER_CORRECT.deepCopy();
        returnedPlayer.setHaveGold(true);
        given(lootPerformer.perform(any(), any())).willReturn(new PlayerWithMap(returnedPlayer, MAP_EXPECTED_CORRECT));
        // when
        Optional<GameState> result = underTest.process(LOOT_CORRECT, Optional.of(gameState));

        PlayerVO expectedPlayer = PLAYER_CORRECT.deepCopy();
        expectedPlayer.setHaveGold(true);
        Optional<GameState> expected = Optional.of(new GameState(MAP_EXPECTED_CORRECT, expectedPlayer, false, false));

        System.out.println("\t\t\tWHEN\t:" + expected.get().getPlayerVO());
        System.out.println("\t\t\t\t\t:" + result.get().getPlayerVO());
        // then
        Assertions.assertEquals(expected, result);
    }
}