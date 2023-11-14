package hu.nye.progtech.wumplus.service.util;

import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.Element;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hu.nye.progtech.wumplus.model.MapVO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @BeforeEach
    void setUp() {
    }

    @Test
    void TestAllCoordinateof() {
        System.out.println("[TEST\t] : Query a non-valid mini map, search all wall element");
        System.out.println("\t\t\tGIVEN\t:" + MAPVO);
        List<CoordinateVO> result = MapQuery.AllCoordinateOf(Element.WALL, MAPVO);
        List<CoordinateVO> expected = List.of(
                new CoordinateVO(0,0),
                new CoordinateVO(0,1),
                new CoordinateVO(0,2),
                new CoordinateVO(1,0),
                new CoordinateVO(2,0)
                );
        System.out.println("\t\t\tWHEN\t:" + result);
        Assertions.assertEquals(result, expected);
    }
}