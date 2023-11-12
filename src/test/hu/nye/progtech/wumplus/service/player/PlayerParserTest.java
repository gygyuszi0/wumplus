package hu.nye.progtech.wumplus.service.player;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hu.nye.progtech.wumplus.model.PlayerVO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    private PlayerParser underTest;

    @BeforeEach
    void setUp() {
        underTest = new PlayerParser(TEST_PLAYER);
    }

    @Test
    void parsePlayer() {
        System.out.println("[TEST\t] : Parse a player from correct raw map");
        // given
        System.out.println("\t\t\tGIVEN\t:" + TEST_PLAYER);
        System.out.println("\t\t\t\t\t:" + RAW_MAP);
        // when
        PlayerVO result = underTest.parsePlayer(RAW_MAP);
        PlayerVO expected = new PlayerVO(TEST_PLAYER, 'E', 'B', 5);
        System.out.println("\t\t\tWHEN\t:" + result);
        // then
        Assertions.assertEquals(expected, result);
        


    }

    @Test
    void setGameLogicInformation() {
    }
}