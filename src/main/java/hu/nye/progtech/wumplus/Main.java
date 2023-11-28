package hu.nye.progtech.wumplus;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import hu.nye.progtech.wumplus.conduct.Conductor;
import hu.nye.progtech.wumplus.conduct.CunductorImpl;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.service.util.IOService;
import hu.nye.progtech.wumplus.ui.MenuPrompt;
import hu.nye.progtech.wumplus.ui.PlayerNamePrompt;
//import hu.nye.progtech.wumplus.service.map.MapReadException;
//import hu.nye.progtech.wumplus.service.map.impl.DefaultMapParser;
//import hu.nye.progtech.wumplus.service.map.impl.MapFromFile;


/**
 * Wumplus Main function.
 */
public class Main {
    /**
     * Wumplus-game main függvény.
     *
     * @param args
     *
     */
    public static void main(String[] args) throws FileNotFoundException {
        IOService ioService = new IOService();  
        MenuPrompt menuPrompt = new MenuPrompt(ioService);  
        PlayerNamePrompt playerNamePrompt = new PlayerNamePrompt(ioService);

        Conductor conductor = new CunductorImpl(menuPrompt, playerNamePrompt, ioService);
        conductor.mainLoop();
    }
}