package hu.nye.progtech.wumplus.service.util;

import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.constants.Element;
import hu.nye.progtech.wumplus.model.constants.PlayerConst;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hu.nye.progtech.wumplus.model.MapVO;

import java.util.List;

class MapQueryTest {

    private static final char[][] MAP = {
        {'W', 'W', 'W'},
        {'W', '_', '_'},
        {'W', '_', '_'},
    };
    // private static final char[][] MAP = {
    //     {'W', 'W', 'W', 'W', 'W', 'W'},
    //     {'W', '_', '_', '_', '_', 'W'},
    //     {'W', '_', '_', '_', '_', 'W'},
    //     {'W', '_', '_', '_', '_', 'W'},
    //     {'W', '_', '_', '_', '_', 'W'},
    //     {'W', 'W', 'W', 'W', 'W', 'W'}
    // };

    private static final boolean[][] FIXED = {
            {true,  true,   true },
            {true,  false,  false},
            {true,  false,  false},
    };

    private static final MapVO MAPVO = new MapVO(3, 3, MAP, FIXED);

    private static final PlayerVO PLAYER_EAST = new PlayerVO("teszt", PlayerConst.EAST, new CoordinateVO(1,1));
    private static final PlayerVO PLAYER_NORTH = new PlayerVO("teszt", PlayerConst.NORTH, new CoordinateVO(1,1));

    @BeforeEach
    void setUp() {
    }

    @Test
    void TestAllCoordinateof() {
        System.out.println("[TEST\t] : Query a non-valid mini map, search all wall element");
        System.out.println("\t\t\tGIVEN\t:" + MAPVO);
        List<CoordinateVO> result = MapQuery.allCoordinateOf(Element.WALL, MAPVO);
        List<CoordinateVO> expected = List.of(
                new CoordinateVO(0, 0),
                new CoordinateVO(1, 0),
                new CoordinateVO(2, 0),
                new CoordinateVO(0, 1),
                new CoordinateVO(0, 2)
                );
        System.out.println("\t\t\tWHEN\t:" + result);
        Assertions.assertEquals(result, expected);
    }

    @Test
    void getFiledFrontOfThePlayerEast() {
        System.out.println("[TEST\t] : Test when player direction is EAST");
        // given
        System.out.println("\t\t\tGIVEN\t:" + PLAYER_EAST);
        System.out.println("\t\t\t\t\t:" + MAPVO);
        // when
        CoordinateVO result = MapQuery.getCoordFrontOfThePlayer(PLAYER_EAST, MAPVO);
        CoordinateVO expected = new CoordinateVO(2,1);
        System.out.println("\t\t\tWHEN\t:" + result);
        System.out.println("\t\t\t\t\t:" + expected);
        // then
        Assertions.assertEquals(result, expected);
    }

    @Test
    void getFiledFrontOfThePlayerNorth() {
        System.out.println("[TEST\t] : Test when player direction is NORTH");
        // given
        System.out.println("\t\t\tGIVEN\t:" + PLAYER_NORTH);
        System.out.println("\t\t\t\t\t:" + MAPVO);
        // when
        CoordinateVO result = MapQuery.getCoordFrontOfThePlayer(PLAYER_NORTH, MAPVO);
        CoordinateVO expected = new CoordinateVO(1,0);
        System.out.println("\t\t\tWHEN\t:" + result);
        System.out.println("\t\t\t\t\t:" + expected);
        // then
        Assertions.assertEquals(result, expected);

    }
}