package hu.nye.progtech.wumplus.conduct.gamecontroller;

import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.constants.CommandConst;
import hu.nye.progtech.wumplus.service.command.impl.GiveUpCommand;
import hu.nye.progtech.wumplus.service.command.impl.LootCommand;
import hu.nye.progtech.wumplus.service.command.impl.StepCommand;
import hu.nye.progtech.wumplus.service.command.impl.TurnCommand;
import hu.nye.progtech.wumplus.service.exception.GameUiException;
import hu.nye.progtech.wumplus.ui.game.CommandPrompt;
import hu.nye.progtech.wumplus.ui.game.HudWriter;
import hu.nye.progtech.wumplus.ui.game.MapWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ControllerImplTest {
    @Mock
    private MapWriter mapWriterMock;

    @Mock
    private HudWriter hudWriterMock;

    @Mock
    private CommandPrompt commandPromptMock;
    @Mock
    private GiveUpCommand giveUpCommandMock;
    @Mock
    private LootCommand lootCommandMock;
    @Mock
    private LootCommand pauseCommandMock;
    @Mock
    private StepCommand stepCommandMock;
    @Mock
    private TurnCommand turnCommandMock;


//
//    private List<Command> allCommandMock =

    @Mock
    private GameState initialGameState;

    @Mock
    private GameState expectedGameState;

    @InjectMocks
    private ControllerImpl underTest;

    @BeforeEach
    public void setUp() {
        underTest = new ControllerImpl(mapWriterMock, hudWriterMock, commandPromptMock, List.of(giveUpCommandMock, lootCommandMock, pauseCommandMock,
                stepCommandMock, turnCommandMock));
    }

    @Test
    public void testStartGame() throws GameUiException {
        // given
        given(initialGameState.deepCopy()).willReturn(initialGameState);
        given(initialGameState.isShouldExit()).willReturn(false);
        given(commandPromptMock.readCommand()).willReturn(CommandConst.GIVE_UP);

        given(expectedGameState.isShouldExit()).willReturn(true);

        given(giveUpCommandMock.canProcess(CommandConst.GIVE_UP)).willReturn(true);
        given(giveUpCommandMock.process(CommandConst.GIVE_UP, Optional.of(initialGameState))).willReturn(Optional.of(expectedGameState));

        // when
        Optional<GameState> result = underTest.startGame(Optional.of(initialGameState));

        // then
        verify(initialGameState).deepCopy();
        verify(expectedGameState).isShouldExit();
        verify(mapWriterMock).writeMap(any());
        verify(hudWriterMock).writeHud(any());
        verify(commandPromptMock).readCommand();
        verify(giveUpCommandMock).canProcess(CommandConst.GIVE_UP);
        verify(giveUpCommandMock).process(CommandConst.GIVE_UP, Optional.of(initialGameState));
        assertEquals(result.get(), initialGameState);
    }

}