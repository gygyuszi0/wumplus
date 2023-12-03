package hu.nye.progtech.wumplus.model;

import java.util.Objects;

/**
 * Koordinátát tárol egész számokkal.
 */
public class CoordinateVO {

    private Integer coordX;
    private Integer coordY;

    public CoordinateVO(Integer x, Integer y) {
        this.coordX = x;
        this.coordY = y;
    }

    public Integer getCoordX() {
        return coordX;
    }

    public void setCoordX(Integer x) {
        this.coordX = x;
    }

    public Integer getCoordY() {
        return coordY;
    }

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
