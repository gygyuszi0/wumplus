package hu.nye.progtech.wumplus.model;

import java.util.Objects;

/**
 * Játkos információk.
 */
public class PlayerVO {

    // Játék elején ismerjük
    private final String name;

    // Fileból derül ki
    private final Character direction;
    private final Character coordinateCols;
    private final int coordinateRows;

    // Játéklogia elemei
    private Integer numberOfArrows;
    private Boolean haveGold;
    private Integer score;
    private Integer numberOfSteps;

    public PlayerVO(String name, Character direction, Character coordinateCols, int coordinateRows) {
        this.name = name;
        this.direction = direction;
        this.coordinateCols = coordinateCols;
        this.coordinateRows = coordinateRows;
    }

    @Override
    public String toString() {
        return "PlayerVO{" +
                "name='" + name + '\'' +
                ", direction=" + direction +
                ", coordinateCols=" + coordinateCols +
                ", coordinateRows=" + coordinateRows +
                ", numberOfArrows=" + numberOfArrows +
                ", haveGold=" + haveGold +
                ", score=" + score +
                ", numberOfSteps=" + numberOfSteps +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerVO playerVO = (PlayerVO) o;
        return coordinateRows == playerVO.coordinateRows && Objects.equals(name, playerVO.name) && Objects.equals(direction, playerVO.direction) && Objects.equals(coordinateCols, playerVO.coordinateCols) && Objects.equals(numberOfArrows, playerVO.numberOfArrows) && Objects.equals(haveGold, playerVO.haveGold) && Objects.equals(score, playerVO.score) && Objects.equals(numberOfSteps, playerVO.numberOfSteps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, direction, coordinateCols, coordinateRows, numberOfArrows, haveGold, score, numberOfSteps);
    }
}
