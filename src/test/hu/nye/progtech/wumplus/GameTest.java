package hu.nye.progtech.wumplus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

import hu.nye.progtech.wumplus.ConsolRead;
import hu.nye.progtech.wumplus.model.Game

@ExtendWith(MockitoExtension.class)
class GameTest {

    @Mock
    private static ConsolRead consolMock;
    private static Game underTest;

    @BeforeEach
    void setUp() {
        underTest = new Game();
    }

    @Test
    void getPlayerName() {
    }

    @Test
    void setPlayerName() {
    }

    @Test
    void mainLoop() {
        // GIVEN
        
    }
}