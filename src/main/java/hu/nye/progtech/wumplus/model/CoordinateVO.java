package hu.nye.progtech.wumplus.model;

import java.util.Objects;

public class CoordinateVO {
    private Integer x;

    private Integer y;

    public CoordinateVO(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoordinateVO that = (CoordinateVO) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "CoordinateVO{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
