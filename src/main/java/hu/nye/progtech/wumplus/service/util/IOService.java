package hu.nye.progtech.wumplus.service.util;

import java.util.Scanner;

/**
 * IO műveletek kezelésére szolgáló osztály.
 */
public class IOService {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Beolvasás a konzolról.
     *
     * @return a beolvasott szöveg.
     */
    public String readConsole() {
        String input = scanner.nextLine();
        return input;
    }

    /**
     * Kiírás a konzolra.
     *
     * @param message a kiírandó szöveg.
     */
    public void writeConsole(String message) {
        System.out.print(message);
    }
}
