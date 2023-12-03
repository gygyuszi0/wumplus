package hu.nye.progtech.wumplus.ui;

import hu.nye.progtech.wumplus.ui.menu.MenuPrompt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hu.nye.progtech.wumplus.service.util.IOService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class MenuPromptTest {

    @Mock
    private IOService ioServiceMock;

    private MenuPrompt underTest;

    @BeforeEach
    public void setUp() {
        underTest = new MenuPrompt(ioServiceMock);
    }

    @Test
    public void testReadChoice() {
        // given
        given(ioServiceMock.readConsole()).willReturn("3");

        // when
        Integer result = underTest.readChoice();

        // then
        assertEquals(Integer.valueOf(3), result);
        verify(ioServiceMock).writeConsole(anyString());
        verify(ioServiceMock).readConsole();
        verifyNoMoreInteractions(ioServiceMock);
    }
}
