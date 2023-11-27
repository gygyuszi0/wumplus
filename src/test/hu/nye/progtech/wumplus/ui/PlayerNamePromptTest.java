package hu.nye.progtech.wumplus.ui;

import hu.nye.progtech.wumplus.service.util.IOService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerNamePromptTest {

    @Mock
    private IOService ioServiceMock;

    private PlayerNamePrompt underTest;

    @BeforeEach
    public void setUp() {
        underTest = new PlayerNamePrompt(ioServiceMock);
    }

    @Test
    public void testGetPlayerName() {
        // given
        when(ioServiceMock.readConsole()).thenReturn("TestPlayer");

        // when
        String result = underTest.getPlayerName();

        // then
        assertEquals("TestPlayer", result);
        verify(ioServiceMock).writeConsole(anyString());
        verify(ioServiceMock).readConsole();
        verifyNoMoreInteractions(ioServiceMock);
    }
}
