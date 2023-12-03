package hu.nye.progtech.wumplus.ui.game;

import hu.nye.progtech.wumplus.model.CoordinateVO;
import hu.nye.progtech.wumplus.model.GameState;
import hu.nye.progtech.wumplus.model.PlayerVO;
import hu.nye.progtech.wumplus.service.util.IOService;
import org.slf4j.Logger;

import java.util.Optional;

public class HudWriter {
    public static final String SEPARATOR = "------------------------------\n";
    public static final String DIRECTION = "Direction: ";
    public static final String ARROWS = "Arrows: ";
    public static final String GOLD = "Gold : ";
    public static final String BASE = "Base : ";
    private final Logger logger = org.slf4j.LoggerFactory.getLogger(HudWriter.class);

    private final IOService  ioService;


    public HudWriter(IOService ioService) {
        this.ioService = ioService;
    }


    public void writeHud(Optional<GameState> gameState) {
        if (gameState.isPresent()) {
            PlayerVO player = gameState.get().getPlayerVO();
            CoordinateVO baseCoordinate = player.getStartCoordinate();
            ioService.writeConsole(SEPARATOR);
            ioService.writeConsole(BASE + writeCoordinate(baseCoordinate) + '\t');
            ioService.writeConsole(DIRECTION + player.getDirection() + "\n");
            ioService.writeConsole(ARROWS + player.getNumberOfArrows() + "\t\t");
            ioService.writeConsole(GOLD + player.getHaveGold() + '\n');
            ioService.writeConsole(SEPARATOR);
        } else {
            logger.error("Game state is not present.");
        }
    }

    private String writeCoordinate(CoordinateVO coordinate) {
        return String.format("(%d, %d)", coordinate.getCoordX(), coordinate.getCoordY());
    }
}

