package hu.nye.progtech.wumplus.service.command.impl;

import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
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
    private PlayerVO PLAYER_CORRECT = new PlayerVO("teszt", PlayerConst.NORTH, new CoordinateVO(2, 3));

    private GameState GAMESTATE_CORRECT_ECPECTED = new GameState(MAP_EXPECTED_CORRECT,PLAYER_CORRECT, false, false);
    
    @BeforeEach
    void setUp() {
        gameState = new GameState(null, PLAYER_CORRECT, false, false);
        underTest = new LootCommand(gameState, lootPerformer);
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
        System.out.println("\t\t\tGIVEN\t:" + underTest.getGameState());
        System.out.println("\t\t\t\t\t:" + underTest.getGameState());
        given(lootPerformer.perform(any(), any())).willReturn(MAP_EXPECTED_CORRECT);
        // when
        underTest.process(LOOT_CORRECT);
        PlayerVO expectedPayer = new PlayerVO(PLAYER_CORRECT.getName(), PLAYER_CORRECT.getDirection(), PLAYER_CORRECT.getCoordinate());
        expectedPayer.setHaveGold(true);
        GameState expected = new GameState(MAP_EXPECTED_CORRECT, expectedPayer, false, false);
        System.out.println("\t\t\tWHEN\t:" + expected);
        System.out.println("\t\t\t\t\t:" + underTest.getGameState());        
        // then
        Assertions.assertEquals(expected, underTest.getGameState());
    }
}