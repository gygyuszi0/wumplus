package hu.nye.progtech.wumplus.model;

import java.util.Objects;

public class PlayerWithMap {

    private final PlayerVO playerVO;

    private final MapVO mapVO;

    public PlayerWithMap(PlayerVO playerVO, MapVO mapVO) {
        this.playerVO = playerVO;
        this.mapVO = mapVO;
    }

    public PlayerVO getPlayerVO() {
        return playerVO;
    }

    public MapVO getMapVO() {
        return mapVO;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerWithMap that = (PlayerWithMap) o;
        return Objects.equals(playerVO, that.playerVO) && Objects.equals(mapVO, that.mapVO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerVO, mapVO);
    }

    @Override
    public String toString() {
        return "PlayerWithMap{" +
                "playerVO=" + playerVO +
                ", mapVO=" + mapVO +
                '}';
    }
}
