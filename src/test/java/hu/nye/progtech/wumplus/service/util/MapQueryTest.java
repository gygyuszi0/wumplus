package hu.nye.progtech.wumplus.service.util;

import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.constants.Element;
import hu.nye.progtech.wumplus.model.constants.PlayerConst;
import hu.nye.progtech.wumplus.service.exception.MapQueryException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hu.nye.progtech.wumplus.model.MapVO;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
            {true, true, true},
            {true, false, false},
            {true, false, false},
    };

    private static final MapVO MAPVO = new MapVO(3, 3, MAP, FIXED);

    private static final PlayerVO PLAYER_EAST = new PlayerVO("teszt", PlayerConst.EAST, new CoordinateVO(1, 1), new CoordinateVO(1, 1));
    private static final PlayerVO PLAYER_NORTH = new PlayerVO("teszt", PlayerConst.NORTH, new CoordinateVO(1, 1), new CoordinateVO(1, 1));

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
        assertEquals(result, expected);
    }

    @Test
    void getFiledFrontOfThePlayerEast() {
        System.out.println("[TEST\t] : Test when player direction is EAST");
        // given
        System.out.println("\t\t\tGIVEN\t:" + PLAYER_EAST);
        System.out.println("\t\t\t\t\t:" + MAPVO);
        // when
        CoordinateVO result = MapQuery.getCoordFrontOfThePlayer(PLAYER_EAST, MAPVO);
        CoordinateVO expected = new CoordinateVO(2, 1);
        System.out.println("\t\t\tWHEN\t:" + result);
        System.out.println("\t\t\t\t\t:" + expected);
        // then
        assertEquals(result, expected);
    }

    @Test
    void getFiledFrontOfThePlayerNorth() {
        System.out.println("[TEST\t] : Test when player direction is NORTH");
        // given
        System.out.println("\t\t\tGIVEN\t:" + PLAYER_NORTH);
        System.out.println("\t\t\t\t\t:" + MAPVO);
        // when
        CoordinateVO result = MapQuery.getCoordFrontOfThePlayer(PLAYER_NORTH, MAPVO);
        CoordinateVO expected = new CoordinateVO(1, 0);
        System.out.println("\t\t\tWHEN\t:" + result);
        System.out.println("\t\t\t\t\t:" + expected);
        // then
        assertEquals(result, expected);

    }

    @Test
    void setElementByCoordinateCorrect() throws MapQueryException {
        System.out.println("[TEST\t] : Set element to the coordinate, if the coordinate is correct.");
        // given
        CoordinateVO coordinateVO = new CoordinateVO(1, 1);
        System.out.println("\t\t\tGIVEN\t:" + MAPVO);
        System.out.println("\t\t\t\t\t:" + coordinateVO);
        // when
        MapVO result = MapQuery.setElementByCoordinate(MAPVO, coordinateVO, Element.WUMP);
        char[][] expectedMap = {
                {'W', 'W', 'W'},
                {'W', 'U', '_'},
                {'W', '_', '_'},
        };
        MapVO expected = new MapVO(3, 3, expectedMap, FIXED);
        System.out.println("\t\t\tWHEN\t:" + result);
        System.out.println("\t\t\t\t\t:" + expected);
        // then
        assertEquals(result, expected);
    }

    @Test
    void setElementByCoordinateWrong() {
        System.out.println("[TEST\t] : Set element to the coordinate, if the coordinate is wrong.");
        // given
        CoordinateVO coordinateVO = new CoordinateVO(-1, 1);
        System.out.println("\t\t\tGIVEN\t:" + MAPVO);
        System.out.println("\t\t\t\t\t:" + coordinateVO);
        // when - given
        Exception result = assertThrows(MapQueryException.class,
                () -> MapQuery.setElementByCoordinate(MAPVO, coordinateVO, Element.WUMP));
    }

    @Test
    void getRowCorrect() throws MapQueryException {
        System.out.println("[TEST\t] : Get correct row from MapVO ");
        // given
        Integer row = 1;
        System.out.println("\t\t\tGIVEN\t:" + MAPVO);
        System.out.println("\t\t\t\t\t:" + row);
        // when
        char[] result = MapQuery.getRow(MAPVO, row);
        char[] expected = new char[]{'W', '_', '_'};
        System.out.println("\t\t\tWHEN\t:" + Arrays.toString(result));
        System.out.println("\t\t\t\t\t:" + Arrays.toString(expected));
        // then
        assertEquals(Arrays.equals(expected, result), true);
    }

    @Test
    void getRowWrong() throws MapQueryException {
        System.out.println("[TEST\t] : Get wrong row from MapVO ");
        // given
        Integer row = -1;
        System.out.println("\t\t\tGIVEN\t:" + MAPVO);
        System.out.println("\t\t\t\t\t:" + row);
        // when - then
        assertThrows(MapQueryException.class, () -> MapQuery.getRow(MAPVO, row));
    }

    @Test
    public void testSerializeMapRegularMap() {
        // given
        char[][] map = {{'A', 'B', 'C'}, {'D', 'E', 'F'}};
        boolean[][] fixed = {{false, false, false}, {false, false, false}};
        MapVO mapVO1 = new MapVO(2, 3, map, fixed);
        // when
        String result = MapQuery.serializeMap(mapVO1);
        String expected1 = "ABC\nDEF\n";
        assertEquals(expected1, result);
    }

    @Test
    public void testSerializeMapEmptyMap() {
        // given
        char[][] map = {};
        boolean[][] fixed = {};
        MapVO mapVO2 = new MapVO(0, 0, map, fixed);
        // when
        String expected = "";
        String result = MapQuery.serializeMap(mapVO2);
        // then
        assertEquals(expected, result);
    }

    @Test
    public void testDeserializeMapRegularMap() {
        // given
        String map1 = "W_\nP_";
        // when
        MapVO expectedMapVO1 = new MapVO(2, 2, new char[][]{{'W', '_'}, {'P', '_'}}, new boolean[][]{{true, false}, {true, false}});
        MapVO result = MapQuery.deserializeMap(map1);
        // then
        assertEquals(expectedMapVO1, result);
    }
    @Test
    public void testDeserializeMapEmptyMap() {
        // given
        String map2 = "";
        // when
        MapVO expectedMapVO2 = new MapVO(0, 0, new char[][]{{}}, new boolean[][]{{}});
        MapVO result = MapQuery.deserializeMap(map2);
        // then
        assertEquals(expectedMapVO2, result);
    }

    @Test
    public void testDeserializeMapNoWallsOrPits() {
        // given
        String map3 = "_U\nGH";
        // when
        MapVO expectedMapVO3 = new MapVO(2, 2, new char[][]{{'_', 'U'}, {'G', 'H'}}, new boolean[][]{{false, false}, {false, false}});
        MapVO result = MapQuery.deserializeMap(map3);
        // then
        assertEquals(expectedMapVO3, result);
    }


    @Test
    public void testGetFieldByCoordinate() throws MapQueryException {
        // given
        MapVO mapVO1 = new MapVO(3, 3, new char[][]{{'A', 'B', 'C'}, {'D', 'E', 'F'}, {'G', 'H', 'I'}}, new boolean[][]{{false, false, false}, {false, false, false}, {false, false, false}});
        CoordinateVO coordinateVO1 = new CoordinateVO(1, 1);
        // when
        Character result = MapQuery.getFieldByCoordinate(coordinateVO1, mapVO1);
        // then
        assertEquals('E', result);
    }

    @Test
    public void testGetFieldByCoordinateInvalid() {
        // given
        MapVO mapVO2 = new MapVO(2, 2, new char[][]{{'A', 'B'}, {'C', 'D'}}, new boolean[][]{{false, false}, {false, false}});
        CoordinateVO coordinateVO2 = new CoordinateVO(2, 2);
        // when - then
        MapQueryException exception = assertThrows(MapQueryException.class, () -> MapQuery.getFieldByCoordinate(coordinateVO2, mapVO2));
        assertEquals("This coordinate is out of the map : CoordinateVO{x=2, y=2}", exception.getMessage());
    }

    @Test
    public void testGetFieldByCoordinateEmptyMap() {
        // given
        MapVO mapVO2 = new MapVO(0, 0, new char[][]{}, new boolean[][]{});
        CoordinateVO coordinateVO2 = new CoordinateVO(0, 0);
        // when - then
        MapQueryException exception = assertThrows(MapQueryException.class, () -> MapQuery.getFieldByCoordinate(coordinateVO2, mapVO2));
        assertEquals("This coordinate is out of the map : CoordinateVO{x=0, y=0}", exception.getMessage());
    }

}
