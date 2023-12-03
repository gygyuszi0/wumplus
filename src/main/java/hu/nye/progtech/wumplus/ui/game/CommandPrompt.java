package hu.nye.progtech.wumplus.ui.game;

import hu.nye.progtech.wumplus.service.util.IOService;
import org.slf4j.Logger;

/**
 * Read command from console.
 */
public class CommandPrompt {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(CommandPrompt.class);

    private final IOService  ioService;

    public CommandPrompt(IOService ioService) {
        this.ioService = ioService;
    }

    public String readCommand() {
        ioService.writeConsole("Enter command: ");
        return ioService.readConsole();
    }
}
