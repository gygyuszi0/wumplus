package hu.nye.progtech.wumplus.service.util;

import java.util.Scanner;

public class IOService {
    private static final Scanner scanner = new Scanner(System.in);
    public static String readConsole() {
        String input = scanner.nextLine();
        return input;
    }
    public static void writeConsole(String message) {
        System.out.print(message);
    }
}
