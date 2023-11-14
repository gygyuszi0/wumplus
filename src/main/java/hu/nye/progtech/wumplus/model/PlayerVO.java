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
    private final Character coordinateCols; // 1-től indexel
    private final int coordinateRows; // 1-től indexel

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

        this.numberOfArrows = 0;
        this.haveGold = false;
        this.score = 0;
        this.numberOfSteps = 0;
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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PlayerVO playerVO = (PlayerVO) o;
        return coordinateRows == playerVO.coordinateRows &&
                Objects.equals(name, playerVO.name) &&
                Objects.equals(direction, playerVO.direction) &&
                Objects.equals(coordinateCols, playerVO.coordinateCols) &&
                Objects.equals(numberOfArrows, playerVO.numberOfArrows) &&
                Objects.equals(haveGold, playerVO.haveGold) &&
                Objects.equals(score, playerVO.score) &&
                Objects.equals(numberOfSteps, playerVO.numberOfSteps);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, direction, coordinateCols, coordinateRows, numberOfArrows, haveGold, score, numberOfSteps);
    }


    public String getName() {
        return name;
    }

    public Character getDirection() {
        return direction;
    }

    public Character getCoordinateCols() {
        return coordinateCols;
    }

    public int getCoordinateRows() {
        return coordinateRows;
    }

    public Integer getNumberOfArrows() {
        return numberOfArrows;
    }

    public void setNumberOfArrows(Integer numberOfArrows) {
        this.numberOfArrows = numberOfArrows;
    }

    public Boolean getHaveGold() {
        return haveGold;
    }

    public void setHaveGold(Boolean haveGold) {
        this.haveGold = haveGold;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getNumberOfSteps() {
        return numberOfSteps;
    }

    public void setNumberOfSteps(Integer numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
    }

}
