package hu.nye.progtech.wumplus.service.util;

import java.util.Scanner;

public class IOService {
    private final Scanner scanner = new Scanner(System.in);
    public String readConsole() {
        String input = scanner.nextLine();
        return input;
    }
    public void writeConsole(String message) {
        System.out.print(message);
    }
}
