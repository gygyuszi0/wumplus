package hu.nye.progtech.wumplus.ui.Game;

import hu.nye.progtech.wumplus.model.*;
import hu.nye.progtech.wumplus.service.util.IOService;
import org.slf4j.Logger;

import java.util.Optional;

public class MapWriter {

    private final Logger logger = org.slf4j.LoggerFactory.getLogger(MapWriter.class);


    private final IOService  ioService;

    public MapWriter(IOService ioService) {
        this.ioService = ioService;
    }

    public void writeMap(Optional<GameState> gameState) {
        if (gameState.isPresent()) {
            char[][] map = putPlayerToMap(gameState);
            String mapString = mapToString(map);
            ioService.writeConsole(mapString);
        } else {
            logger.error("Game state is not present.");
        }
    }

    private char[][] putPlayerToMap(Optional<GameState> gameState) {
        if (gameState.isPresent()) {
            char[][] result = gameState.get().getMapElement();
            Integer playerX = gameState.get().getPlayerX();
            Integer playerY = gameState.get().getPlayerY();
            result[playerX][playerY] = 'H';
            return result;
        } else {
            logger.error("Game state is not present.");
            return new char[1][0];
        }
    }

    private String mapToString(char[][] map) {
        StringBuilder sb = new StringBuilder();
        for (char[] row : map) {
            for (char cell : row) {
                sb.append(cell);
            }
            sb.append("\n");
        }
        return sb.toString();  // Convert the StringBuilder to a String and return it.
    }

}
