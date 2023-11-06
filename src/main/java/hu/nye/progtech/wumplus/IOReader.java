package hu.nye.progtech.wumplus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.List;

/**
 * Consolról olvasó osztály.
 */
public class IOReader {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Egy sor beolvasása consolról.
     *
     * @return Beolvasott szöveg
     */
    public static String consloeRead() {
        String result = new String();
        try {
            result = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * File olvasást megvalósító függvény.
     *
     * @param filaName
     *
     * @return
     *
     */
    public static BufferedReader fileRead(String filaName) {
        InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(filaName);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        return bufferedReader;
    }
}
