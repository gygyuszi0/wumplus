package hu.nye.progtech.wumplus;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import hu.nye.progtech.wumplus.conduct.Conductor;
import hu.nye.progtech.wumplus.conduct.CunductorImpl;
import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.model.PlayerWithMap;
import hu.nye.progtech.wumplus.model.constants.PlayerConst;
import hu.nye.progtech.wumplus.service.exception.DBServiceException;
import hu.nye.progtech.wumplus.service.persister.database.DatabaseService;
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
    public static void main(String[] args) throws FileNotFoundException, DBServiceException {
        char[][] map = new char[][]{
                {'W', 'W', '_'},
                {'W', 'P', '_'},
                {'_', 'G', 'U'}
        };

        boolean[][] fixed = new boolean[3][3];
        MapVO mapVO = new MapVO(3, 3, map, fixed);
        PlayerVO playerVO = new PlayerVO("teszt", PlayerConst.NORTH, new CoordinateVO(0, 0));
        playerVO.setNonStatic(3, false, 2, 4);

        DatabaseService databaseService = new DatabaseService();
        databaseService.save(playerVO, mapVO);

        PlayerWithMap loadedPlayer = databaseService.load("teszt");
        System.out.println(loadedPlayer.getPlayerVO());
        System.out.println(loadedPlayer.getMapVO());

        IOService ioService = new IOService();
        MenuPrompt menuPrompt = new MenuPrompt(ioService);  
        PlayerNamePrompt playerNamePrompt = new PlayerNamePrompt(ioService);

        Conductor conductor = new CunductorImpl(menuPrompt, playerNamePrompt, ioService);
        conductor.mainLoop();
    }
}