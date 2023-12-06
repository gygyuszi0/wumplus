package hu.nye.progtech.wumplus.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * Játkos információk.
 */
public class PlayerVO {

    // Játék elején ismerjük
    private final String name;

    // Fileból derül ki
    private final Character direction;
    private final CoordinateVO coordinate; // 0-tól indexel
    private final CoordinateVO startCoordinate; // 0-tól indexel

    // Játéklogia elemei
    private Integer numberOfArrows;
    private Boolean haveGold;
    private Integer score;
    private Integer numberOfSteps;

    @JsonCreator
    public PlayerVO(
            @JsonProperty("name") String name,
            @JsonProperty("direction") Character direction,
            @JsonProperty("coordinate") CoordinateVO coordinate,
            @JsonProperty("startCoordinate") CoordinateVO base) {
        this.name = name;
        this.direction = direction;
        this.coordinate = coordinate;

        this.numberOfArrows = 0;
        this.haveGold = false;
        this.score = 0;
        this.numberOfSteps = 0;
        this.startCoordinate = base;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    @JsonGetter("direction")
    public Character getDirection() {
        return direction;
    }

    @JsonGetter("coordinate")
    public CoordinateVO getCoordinate() {
        return new CoordinateVO(coordinate.getCoordX(), coordinate.getCoordY());
    }

    @JsonGetter("startCoordinate")
    public CoordinateVO getStartCoordinate() {
        return startCoordinate.deepCopy();
    }

    @JsonGetter("numberOfArrows")
    public Integer getNumberOfArrows() {
        return numberOfArrows;
    }

    @JsonSetter("numberOfArrows")
    public void setNumberOfArrows(Integer numberOfArrows) {
        this.numberOfArrows = numberOfArrows;
    }

    @JsonGetter("haveGold")
    public Boolean getHaveGold() {
        return haveGold;
    }

    @JsonSetter("haveGold")
    public void setHaveGold(Boolean haveGold) {
        this.haveGold = haveGold;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @JsonGetter("numberOfSteps")
    public Integer getNumberOfSteps() {
        return numberOfSteps;
    }

    @JsonSetter("numberOfSteps")
    public void setNumberOfSteps(Integer numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
    }

    @Override
    public String toString() {
        return "PlayerVO{" +
                "name='" + name + '\'' +
                ", direction=" + direction +
                ", coordinate=" + coordinate +
                ", numberOfArrows=" + numberOfArrows +
                ", haveGold=" + haveGold +
                ", score=" + score +
                ", numberOfSteps=" + numberOfSteps +
                '}';
    }

    /**
     * Változtatható tagok értékadása.
     *
     * @param numberOfArrows nyilak száma
     *
     * @param haveGold       van-e arany
     *
     * @param score          pontszám
     *
     * @param numberOfSteps  lépések száma
     *
     */
    public void setNonStatic(Integer numberOfArrows, Boolean haveGold,
            Integer score, Integer numberOfSteps) {
        this.numberOfArrows = numberOfArrows;
        this.haveGold = haveGold;
        this.score = score;
        this.numberOfSteps = numberOfSteps;
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
        return Objects.equals(name, playerVO.name) && Objects.equals(direction, playerVO.direction) &&
                Objects.equals(coordinate, playerVO.coordinate)
                && Objects.equals(numberOfArrows, playerVO.numberOfArrows) &&
                Objects.equals(haveGold, playerVO.haveGold) && Objects.equals(score, playerVO.score) &&
                Objects.equals(numberOfSteps, playerVO.numberOfSteps);
    }


    @Override
    public int hashCode() {
        return Objects.hash(name, direction, coordinate, numberOfArrows, haveGold, score, numberOfSteps);
    }

    @JsonIgnore
    public Integer getCoordX() {
        return coordinate.getCoordX();
    }

    @JsonIgnore
    public Integer getCoordY() {
        return coordinate.getCoordY();
    }


    /**
     * Copy the object.
     *
     * @return new object
     */
    public PlayerVO deepCopy() {
        PlayerVO playerVO = new PlayerVO(name, direction, coordinate.deepCopy(), startCoordinate.deepCopy());
        playerVO.setNonStatic(numberOfArrows, haveGold, score, numberOfSteps);
        return playerVO;
    }

    @JsonIgnore
    public boolean isWon() {
        return coordinate.equals(startCoordinate) && haveGold;
    }


    @JsonIgnore
    public Integer getBaseX() {
        return startCoordinate.getCoordX();
    }

    @JsonIgnore
    public Integer getBaseY() {
        return startCoordinate.getCoordY();
    }
}
