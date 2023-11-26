package hu.nye.progtech.wumplus.ui;

import hu.nye.progtech.wumplus.service.util.IOService;

public class MenuPrompt {
    private static final String MENU_LINE1 = "Please select an option:\n";
    private static final String MENU_LINE2 = "\t1. Create new map\n";
    private static final String MENU_LINE3 = "\t2. Read from file\n";
    private static final String MENU_LINE4 = "\t3. Load from database\n";
    private static final String MENU_LINE5 = "\t4. Save to database\n";   
    private static final String MENU_LINE6 = "\t5. Play\n";
    private static final String MENU_LINE7 = "\t6. Exit\n";
    private static final String MENU_LINE8 = "Your choice: ";
    private static final String MENU_PROMT = MENU_LINE1 + MENU_LINE2 + MENU_LINE3 + MENU_LINE4 + MENU_LINE5 + MENU_LINE6 + MENU_LINE7 + MENU_LINE8;


    public static Integer readChoice() {
        IOService.writeConsole(MENU_PROMT);
        String choice = IOService.readConsole();
        Integer choiceConverted = Integer.parseInt(choice);
        return choiceConverted;
    
    }



}
