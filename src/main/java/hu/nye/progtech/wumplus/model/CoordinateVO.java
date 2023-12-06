package hu.nye.progtech.wumplus.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.Objects;

/**
 * Koordinátát tárol egész számokkal.
 */
public class CoordinateVO {

    private Integer coordX;
    private Integer coordY;

    @JsonCreator
    public CoordinateVO(
            @JsonProperty("pos_x") Integer x,
            @JsonProperty("pos_y") Integer y) {
        this.coordX = x;
        this.coordY = y;
    }

    @JsonGetter("pos_x")
    public Integer getCoordX() {
        return coordX;
    }

    @JsonSetter("pos_x")
    public void setCoordX(Integer x) {
        this.coordX = x;
    }

    @JsonGetter("pos_y")
    public Integer getCoordY() {
        return coordY;
    }
    @JsonSetter("pos_y")
    public void setCoordY(Integer y) {
        this.coordY = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CoordinateVO that = (CoordinateVO) o;
        return Objects.equals(coordX, that.coordX) && Objects.equals(coordY, that.coordY);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordX, coordY);
    }

    @Override
    public String toString() {
        return "CoordinateVO{" +
                "x=" + coordX +
                ", y=" + coordY +
                '}';
    }

    public CoordinateVO deepCopy() {
        return new CoordinateVO(coordX, coordY);
    }
}
