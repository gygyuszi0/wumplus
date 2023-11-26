package hu.nye.progtech.wumplus.ui;

import hu.nye.progtech.wumplus.service.util.IOService;

public class PlayerNamePrompt {
    private static final String PROMPT_LINE1 = "Wumplus game -------------------------------";
    private static final String PROMPT_LINE2 = "> player name: ";
    private static final String PROMPT = PROMPT_LINE1 + "\n" + PROMPT_LINE2;

    public static String getPlayerName() {
        IOService.writeConsole(PROMPT);
        String playerName = IOService.readConsole();
        return playerName;
    }
}
