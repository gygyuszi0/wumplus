package hu.nye.progtech.wumplus.ui.menu;

import hu.nye.progtech.wumplus.service.util.IOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LeaderBoardWriter {

    public static final String BANNER = "---------- Top players ----------\n";
    public static final String HEADER = "\tname\t: score\n";
    public static final String PLAYER = "\t%s\t: %s\n";
    public static final String SEPARATOR = "\n------------------------------------\n";
    private final Logger logger = LoggerFactory.getLogger(LeaderBoardWriter.class);

    private final IOService ioService;

    public LeaderBoardWriter(IOService ioService) {
        this.ioService = ioService;
    }
    public void printHighScores(List<List<String>> playersScore){
        ioService.writeConsole(BANNER);
        try {
            ioService.writeConsole(HEADER);
            for (List<String> player : playersScore) {
                ioService.writeConsole(String.format(PLAYER, player.get(0), player.get(1)));
            }
        } catch (Exception e) {
            logger.error("Error while printing high scores" + e);
        }
        ioService.writeConsole(SEPARATOR);
    }
}
