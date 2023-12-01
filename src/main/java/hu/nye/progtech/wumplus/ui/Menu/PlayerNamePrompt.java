package hu.nye.progtech.wumplus.ui.Menu;

import hu.nye.progtech.wumplus.service.util.IOService;

/**
 * Játékos nevének bekérése.
 */
public class PlayerNamePrompt {
    private final String promptLine1 = "Wumplus game -------------------------------";
    private final String promptLine2 = "> player name: ";
    private final String prompt = promptLine1 + "\n" + promptLine2;

    private final IOService ioService;

    public PlayerNamePrompt(IOService ioService) {  
        this.ioService = ioService;
    }

    /**
     * Játékos nevének bekérése.
     *
     * @return Játékos neve.
     */
    public String getPlayerName() {
        ioService.writeConsole(prompt);
        String playerName = ioService.readConsole();
        return playerName;
    }
}
