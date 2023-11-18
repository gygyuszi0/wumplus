package hu.nye.progtech.wumplus.service.command.performer;

import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.constants.CommandConst;
import hu.nye.progtech.wumplus.model.constants.PlayerConst;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TurnPerformerTest {


    private final PlayerVO PLAYER_NORTH = new PlayerVO("north", PlayerConst.NORTH, new CoordinateVO(1,1));
    private final PlayerVO PLAYER_WEST = new PlayerVO("west", PlayerConst.WEST, new CoordinateVO(1,1));
    private final String TURN_RIGHT_COMMAND = String.format("%s %c", CommandConst.TURN, CommandConst.TURN_RIGHT);
    private final String TURN_LEFT_COMMAND = String.format("%s %c", CommandConst.TURN, CommandConst.TURN_LEFT);
    // private TurnPerformer underTest;

    @BeforeEach
    void setUp() {
    }

    @Test
    void northToRight() {
        System.out.println("[TEST\t] : Player turn from north to right (east)");
        // given
        System.out.println("\t\t\tGIVEN\t:" + PLAYER_NORTH);
        // when
        PlayerVO result = TurnPerformer.perform(PLAYER_NORTH, CommandConst.TURN_RIGHT);
        PlayerVO expected = new PlayerVO("north", PlayerConst.EAST, new CoordinateVO(1,1));
        System.out.println("\t\t\tWHEN\t:" + result);
        System.out.println("\t\t\t\t\t:" + expected);
        // then
        Assertions.assertEquals(result, expected);
    }

    @Test
    void northtoLeft() {
        System.out.println("[TEST\t] : Player turn from north to left (west)");
        // given
        System.out.println("\t\t\tGIVEN\t:" + PLAYER_NORTH);
        // when
        PlayerVO result = TurnPerformer.perform(PLAYER_NORTH, CommandConst.TURN_LEFT);
        PlayerVO expected = new PlayerVO("north", PlayerConst.WEST, new CoordinateVO(1,1));
        System.out.println("\t\t\tWHEN\t:" + result);
        System.out.println("\t\t\t\t\t:" + expected);
        // then
        Assertions.assertEquals(result, expected);
    }

    @Test
    void westToRight() {
        System.out.println("[TEST\t] : Player turn from west to right (north)");
        // given
        System.out.println("\t\t\tGIVEN\t:" + PLAYER_WEST);
        // when
        PlayerVO result = TurnPerformer.perform(PLAYER_WEST, CommandConst.TURN_RIGHT);
        PlayerVO expected = new PlayerVO("west", PlayerConst.NORTH, new CoordinateVO(1,1));
        System.out.println("\t\t\tWHEN\t:" + result);
        System.out.println("\t\t\t\t\t:" + expected);
        // then
        Assertions.assertEquals(result, expected);
    }

    @Test
    void westToLeft() {
        System.out.println("[TEST\t] : Player turn from west to left (south)");
        // given
        System.out.println("\t\t\tGIVEN\t:" + PLAYER_WEST);
        // when
        PlayerVO result = TurnPerformer.perform(PLAYER_WEST, CommandConst.TURN_LEFT);
        PlayerVO expected = new PlayerVO("west", PlayerConst.SOUTH, new CoordinateVO(1,1));
        System.out.println("\t\t\tWHEN\t:" + result);
        System.out.println("\t\t\t\t\t:" + expected);
        // then
        Assertions.assertEquals(result, expected);
    }

}