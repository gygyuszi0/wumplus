package hu.nye.progtech.wumplus;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.conduct.Conductor;
import hu.nye.progtech.wumplus.conduct.CunductorImpl;
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
        Conductor conductor = new CunductorImpl();
        conductor.mainLoop();

//        Scanner scanner = new Scanner(System.in);
//
//        // kérünk a felhasználótól egy szöveget
//        System.out.print("Írj be egy szöveget: ");
//        String userInput = scanner.nextLine();
//
//        // kiírjuk a beolvasott szöveget
//        System.out.println("Beolvasott szöveg: " + userInput);
//
//        // ne felejtsük el lezárni a scannert
//        scanner.close();
    }
}