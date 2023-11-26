package hu.nye.progtech.wumplus.service.util;

import java.util.Scanner;

public class IOService {

    public static String readConsole() {
        
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();
        return input;
    }    
    public static void writeConsole(String message) {
        System.out.print(message);
    }
}
