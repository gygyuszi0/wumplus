package hu.nye.progtech.wumplus.service.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.service.exception.MapParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MapParserTest {

    private static final int NUMBER_OF_ROWS = 6;
    private static final int NUMBER_OF_COLUMNS = 6;

    private static final List<String> RAW_MAP = List.of(
            "6 B 5 E",
            "WWWWWW",
            "W____W",
            "W____W",
            "W____W",
            "W____W",
            "WWWWWW"
    );

    private static final List<String> RAW_MAP_WITH_WRONG_CHARACTERS = List.of(
            "6 B 5 E",
            "WWWWWB",
            "W____W",
            "W____W",
            "W____W",
            "W____W",
            "WWWWWW"
    );

    private static final List<String> RAW_MAP_WITH_FEWER_ROWS = List.of(
            "6 B 5 E",
            "WWWWWW",
            "W____W",
            "W____W"
    );

    private static final List<String> RAW_MAP_WITH_FEWER_COLS = List.of(
            "6 B 5 E",
            "WWW",
            "W__",
            "W__",
            "W__",
            "W__",
            "WWW"
    );

    private static final char[][] MAP = {
            {'W', 'W', 'W', 'W', 'W', 'W'},
            {'W', '_', '_', '_', '_', 'W'},
            {'W', '_', '_', '_', '_', 'W'},
            {'W', '_', '_', '_', '_', 'W'},
            {'W', '_', '_', '_', '_', 'W'},
            {'W', 'W', 'W', 'W', 'W', 'W'}
    };

    private static final boolean[][] FIXED = {
            {true,  true,   true,   true,   true,   true},
            {true,  false,  false,  false,  false,  true},
            {true,  false,  false,  false,  false,  true},
            {true,  false,  false,  false,  false,  true},
            {true,  false,  false,  false,  false,  true},
            {true,  true,   true,   true,   true,   true},
    };

    private static final MapVO EXPECTED_MAP = new MapVO(NUMBER_OF_ROWS, NUMBER_OF_COLUMNS, MAP, FIXED);

    private MapParser underTest;

    @BeforeEach
    public void setUp() {
        underTest = new MapParser(RAW_MAP);
    }



    @Test
    public void testValidMapVo() throws MapParseException {
        System.out.println("[TEST\t] : Return valid mapVo representation");
        // given in setup
        // when
        MapVO result = underTest.parseMap(RAW_MAP);
        System.out.println("\t\t\t\t\t:" + result);
        System.out.println("\t\t\t\t\t:" + EXPECTED_MAP);
        // then
        assertEquals(EXPECTED_MAP, result);
    }
    
    @Test
    void testFewerRows() throws MapParseException {
        System.out.println("[TEST\t] : If the map contains too few rows, will throw exception ");
        // given 
        // when
        // then
        Exception exception = assertThrows(MapParseException.class, () -> underTest.parseMap(RAW_MAP_WITH_FEWER_ROWS));
        System.out.println("\t\t\t\t\t:" + exception.getMessage());
        assertEquals("Number of rows must be 6", exception.getMessage());
    }

    @Test
    void testFewerCols() {
        System.out.println("[TEST\t] : The map contains too few columsn but correct rows");
        // given
        // when
        // then
        Exception exception = assertThrows(MapParseException.class, () -> underTest.parseMap(RAW_MAP_WITH_FEWER_COLS));
        System.out.println("\t\t\t\t\t:" + exception.getMessage());
        assertEquals("Number of columns must be 6", exception.getMessage());

    }

    @Test
    void testValidCharacters() throws MapParseException {
        System.out.println("[TEST\t] : Test character validator for correct map");
        // given
        // when
        MapVO result = underTest.parseMap(RAW_MAP);
        System.out.println("\t\t\t\t\t:" + result);
        System.out.println("\t\t\t\t\t:" + EXPECTED_MAP);

        // then
        assertEquals(EXPECTED_MAP, result);

        

    }

    @Test
    void testWrongCharacters() {
        System.out.println("[TEST\t] : The map contains a wrong character, example B");
        // given
        // when
        // then
        Exception exception = assertThrows(MapParseException.class, () -> underTest.parseMap(RAW_MAP_WITH_WRONG_CHARACTERS));
        assertEquals("Row contains invalid characters", exception.getMessage());
        System.out.println("\t\t\tTHEN\t: exception\t" + exception.getMessage());

    }
}