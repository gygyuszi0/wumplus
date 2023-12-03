package hu.nye.progtech.wumplus.conduct;

import hu.nye.progtech.wumplus.model.constants.MenuOptions;
import hu.nye.progtech.wumplus.conduct.menuperformer.OptionPerformer;
import hu.nye.progtech.wumplus.service.persister.database.DatabaseService;
import hu.nye.progtech.wumplus.ui.menu.LeaderBoardWriter;
import hu.nye.progtech.wumplus.ui.menu.MenuPrompt;
import hu.nye.progtech.wumplus.ui.menu.PlayerNamePrompt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConductorImplTest {

    @Mock
    private MenuPrompt menuPromptMock;
    @Mock
    private PlayerNamePrompt playerNamePromptMock;
    @Mock
    private DatabaseService databaseService;
    @Mock
    private LeaderBoardWriter leaderBoardWriter;

    private CunductorImpl underTest;

    private List<OptionPerformer> optionPerformers = new ArrayList<>();


    @BeforeEach
    void setUp() {
        underTest = new CunductorImpl(menuPromptMock, playerNamePromptMock, optionPerformers, databaseService, leaderBoardWriter);
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
        verifyNoMoreInteractions(playerNamePromptMock, menuPromptMock);
    }
}
