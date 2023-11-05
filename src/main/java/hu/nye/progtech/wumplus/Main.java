package hu.nye.progtech.wumplus;

import hu.nye.progtech.wumplus.model.Game;
import hu.nye.progtech.wumplus.service.map.MapReadException;
import hu.nye.progtech.wumplus.service.map.MapReader;
import hu.nye.progtech.wumplus.service.map.impl.DefaultMapParser;
import hu.nye.progtech.wumplus.service.map.impl.MapFromFile;
import hu.nye.progtech.wumplus.IOReader;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.nio.Buffer;
import java.util.List;

/**
 * Wumplus Main function.
 */
public class Main {
    public static void main(String[] args) throws MapReadException {
//        Game wumplusGame = new Game();
//        wumplusGame.mainLoop();

        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("map/wumpuszinput.txt");
        BufferedReader file = new BufferedReader(new InputStreamReader(inputStream));

        MapFromFile fromFile = new MapFromFile(file);
        List<String> map = fromFile.readMap();

        DefaultMapParser parser = new DefaultMapParser();
        parser.parseMap(map);

//        System.out.println(map);


    }
}