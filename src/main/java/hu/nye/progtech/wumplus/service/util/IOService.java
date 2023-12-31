package hu.nye.progtech.wumplus.service.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * IO műveletek kezelésére szolgáló osztály.
 */
public class IOService {
    private final Scanner scanner = new Scanner(System.in);

    private final Logger logger = LoggerFactory.getLogger(IOService.class);

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

    /**
     * Kiírás a fájlba.
     *
     * @param message a kiírandó szöveg.
     * @param filePath a fájl elérési útja.
     */
    public void writeFile(String message, String filePath) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(message);
            fileWriter.close();
        } catch (IOException e) {
            logger.error("Error writing file: {}", e.getMessage());
        }
    }

    /**
     * Fájl beolvasása.
     *
     * @param filePath a fájl elérési útja.
     *
     * @return a fájl tartalmának stringként összefűzett szöveg.
     */
    public String readFile(String filePath) {
        File file = new File(filePath);
        try {
            Scanner fileReader = new Scanner(file);
            StringBuilder result = new StringBuilder();
            while (fileReader.hasNextLine()) {
                result.append(fileReader.nextLine());
            }
            fileReader.close();
            return result.toString();
        } catch (FileNotFoundException e) {
            logger.error("Error reading file: {}", e.getMessage());
            return "";
        }
    }
}
