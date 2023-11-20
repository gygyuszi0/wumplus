package hu.nye.progtech.wumplus.service.player;

import hu.nye.progtech.wumplus.model.CoordinateVO;

import hu.nye.progtech.wumplus.model.constants.PlayerConst;
import hu.nye.progtech.wumplus.service.exception.MapParseException;
import hu.nye.progtech.wumplus.service.exception.PlayerParserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hu.nye.progtech.wumplus.model.PlayerVO;

import java.util.List;

class PlayerParserTest {

    public static final String TEST_PLAYER = "testPlayer";
    private static final List<String> RAW_MAP = List.of(
            "6 B 5 E",
            "WWWWWW",
            "W____W",
            "W____W",
            "W____W",
            "W____W",
            "WWWWWW"
    );
    private static final List<String> RAW_MAP_WRONG_DIRECTION = List.of(
            "6 B 5 Z",
            "WWWWWW",
            "W____W",
            "W____W",
            "W____W",
            "W____W",
            "WWWWWW"
    );
    private static final List<String> RAW_MAP_NOT_ZERO_WUMPUS = List.of(
            "6 B 5 E",
            "WWWWWW",
            "W____W",
            "W__U_W",
            "W____W",
            "W____W",
            "WWWWWW"
    );
    private PlayerParser underTest;

    @BeforeEach
    void setUp() {
        underTest = new PlayerParser(TEST_PLAYER);
    }

    @Test
    void CorrectMapZeroWumpus() throws PlayerParserException, MapParseException {
        System.out.println("[TEST\t] : Parse a player from correct raw map, with zero wumpus");
        // given
        System.out.println("\t\t\tGIVEN\t:" + TEST_PLAYER);
        System.out.println("\t\t\t\t\t:" + RAW_MAP);
        // when
        PlayerVO result = underTest.parsePlayer(RAW_MAP);
        PlayerVO expected = new PlayerVO(TEST_PLAYER, PlayerConst.EAST, new CoordinateVO(1, 4));
        System.out.println("\t\t\tWHEN\t:" + result);
        // then
        Assertions.assertEquals(expected, result);
    }

    @Test
    void CorrectMapNonZeroWumpus() throws MapParseException, PlayerParserException {
        System.out.println("[TEST\t] : Parse a player from correct raw map, with non zero wumpus");
        // given
        System.out.println("\t\t\tGIVEN\t:" + TEST_PLAYER);
        System.out.println("\t\t\t\t\t:" + RAW_MAP_NOT_ZERO_WUMPUS);
        // when
        PlayerVO result = underTest.parsePlayer(RAW_MAP_NOT_ZERO_WUMPUS);
        PlayerVO expected = new PlayerVO(TEST_PLAYER, PlayerConst.EAST, new CoordinateVO(1, 4));
        expected.setNumberOfArrows(1);
        System.out.println("\t\t\tWHEN\t:" + result);
        // then
        Assertions.assertEquals(expected, result);


    }

    @Test
    void WrongDirection() {
        System.out.println("[TEST\t] : Parse player with wrong direction");
        // given
        System.out.println("\t\t\tGIVEN\t:" + TEST_PLAYER);
        System.out.println("\t\t\t\t\t:" + RAW_MAP_WRONG_DIRECTION);
        // when
        // then
        Exception exception = Assertions.assertThrows(PlayerParserException.class, () -> underTest.parsePlayer(RAW_MAP_WRONG_DIRECTION));
        Assertions.assertEquals("Wrong direction, expected one of : [N, E, S, W]", exception.getMessage());
        System.out.println("\t\t\tTHNE\t:" + exception.getMessage());
        

    }
}