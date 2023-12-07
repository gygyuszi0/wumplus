package hu.nye.progtech.wumplus.ui.menu;

import hu.nye.progtech.wumplus.model.constants.MenuOptions;
import hu.nye.progtech.wumplus.service.util.IOService;

/**
 * Menu megjelenítésére szolgáló osztály.
 */
public class MenuPrompt {

    private final String menuLine1 = "Please select an option:\n";
    private final String menuLine2 = "\t1. Create new map\n";
    private final String menuLine3 = "\t2. Read from file\n";
    private final String menuLine4 = "\t3. Load from database\n";
    private final String menuLine5 = "\t4. Save to database\n";
    private final String menuLine6 = "\t5. Play\n";
    private final String menuLine7 = "\t6. Exit\n";
    private final String menuLine8 = "Your choice: ";
    private final String menuPromt = menuLine1 + menuLine2 + menuLine3 + menuLine4 + menuLine5 + menuLine6 + menuLine7 + menuLine8;

    private final IOService ioService;

    public MenuPrompt(IOService ioService) {    
        this.ioService = ioService;
    }

    /**
     * Menu megjelenítése.
     *
     * @return Kiválasztott menüpont.
     */
    public Integer readChoice() {
        ioService.writeConsole(menuPromt);
        String choice = ioService.readConsole();
        try {
            Integer choiceConverted = Integer.parseInt(choice);
            return choiceConverted;
        } catch (NumberFormatException e) {
            return MenuOptions.INVALID;
        }
    }
}
