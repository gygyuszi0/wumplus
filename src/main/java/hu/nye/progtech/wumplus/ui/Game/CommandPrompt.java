package hu.nye.progtech.wumplus.ui.Game;

import hu.nye.progtech.wumplus.service.util.IOService;
import org.slf4j.Logger;

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
