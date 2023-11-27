package hu.nye.progtech.wumplus.conduct;

import hu.nye.progtech.wumplus.model.constants.MenuOptions;
import hu.nye.progtech.wumplus.service.util.IOService;
import hu.nye.progtech.wumplus.ui.MenuPrompt;
import hu.nye.progtech.wumplus.ui.PlayerNamePrompt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConductorImplTest {

    @Mock
    private MenuPrompt menuPromptMock;
    @Mock
    private PlayerNamePrompt playerNamePromptMock;
    @Mock
    private IOService ioServiceMock;

    private CunductorImpl underTest;

    @BeforeEach
    void setUp() {
        underTest = new CunductorImpl(menuPromptMock, playerNamePromptMock, ioServiceMock);
    }

    @Test
    void testMainLoop() {
        // given
        given(menuPromptMock.readChoice()).willReturn(MenuOptions.PLAY, MenuOptions.EXIT);
        given(playerNamePromptMock.getPlayerName()).willReturn("TestPlayer");

        // when
        underTest.mainLoop();

        // then
        verify(playerNamePromptMock).getPlayerName();
        verify(menuPromptMock, times(2)).readChoice();
        verifyNoMoreInteractions(playerNamePromptMock, menuPromptMock, ioServiceMock);
    }
}
