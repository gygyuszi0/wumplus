package hu.nye.progtech.wumplus.ui;

import hu.nye.progtech.wumplus.service.util.IOService;

public class PlayerNamePrompt {
    private final String PROMPT_LINE1 = "Wumplus game -------------------------------";
    private final String PROMPT_LINE2 = "> player name: ";
    private final String PROMPT = PROMPT_LINE1 + "\n" + PROMPT_LINE2;

    private final IOService ioService;

    public PlayerNamePrompt(IOService ioService) {  
        this.ioService = ioService;
    }

    public String getPlayerName() {
        ioService.writeConsole(PROMPT);
        String playerName = ioService.readConsole();
        return playerName;
    }
}
