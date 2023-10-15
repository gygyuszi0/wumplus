package hu.nye.progtech.wumplus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Consolról olvasó osztály.
 */
public class ConsolRead {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Egy sor beolvasása consolról.
     *
     * @return Beolvasott szöveg
     */
    public static String read() {
        String result = new String();
        try {
            result = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

}
