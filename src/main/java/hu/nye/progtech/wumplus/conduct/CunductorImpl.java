package hu.nye.progtech.wumplus.conduct;

import hu.nye.progtech.wumplus.ui.PlayerNamePrompt;
public class CunductorImpl implements Conductor{

    private String playerName;
    public CunductorImpl() {
    }

    @Override
    public void mainLoop() {
        this.playerName = PlayerNamePrompt.getPlayerName();
        

    }
}
