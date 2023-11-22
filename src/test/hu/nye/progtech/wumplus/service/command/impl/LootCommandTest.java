package hu.nye.progtech.wumplus.service.command.impl;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.service.command.performer.LootPerformer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LootCommandTest {


    private LootCommand underTest;
    private LootPerformer lootPerformer = new LootPerformer();
    private GameState gameState;

    private String LOOT_CORRECT = "loot";
    private String LOOT_WRONG = "YDFXGFD";

    @BeforeEach
    void setUp() {
        gameState = new GameState(null, null, false, false);
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
    void process() {
    }
}