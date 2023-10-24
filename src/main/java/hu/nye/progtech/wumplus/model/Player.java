package hu.nye.progtech.wumplus.model;

import java.util.Objects;

/**
 * Játkos információk.
 */
public class Player {
    private final String name;
    private final Character direction;
    private final Integer numberOfRows;
    private final Boolean haveGold;

    private final Integer score;
    private final Integer numberOfSteps;

    public Player(String name, Character direction, Integer numberOfRows, Boolean haveGold, Integer score, Integer numberOfSteps) {
        this.name = name;
        this.direction = direction;
        this.numberOfRows = numberOfRows;
        this.haveGold = haveGold;
        this.score = score;
        this.numberOfSteps = numberOfSteps;
    }
}
