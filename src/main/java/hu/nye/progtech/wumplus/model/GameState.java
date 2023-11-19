package hu.nye.progtech.wumplus.model;

import java.util.Objects;

public class GameState {
    private MapVO mapVO;
    private PlayerVO playerVO;
    private boolean shouldExit;
    private boolean mapCompleted;

    public GameState(MapVO mapVO, PlayerVO playerVO, boolean shouldExit, boolean mapCompleted) {
        this.mapVO = mapVO;
        this.playerVO = playerVO;
        this.shouldExit = shouldExit;
        this.mapCompleted = mapCompleted;
    }

    public MapVO getMapVO() {
        return mapVO;
    }

    public void setMapVO(MapVO mapVO) {
        this.mapVO = mapVO;
    }

    public PlayerVO getPlayerVO() {
        return playerVO;
    }

    public void setPlayerVO(PlayerVO playerVO) {
        this.playerVO = playerVO;
    }

    public boolean isShouldExit() {
        return shouldExit;
    }

    public void setShouldExit(boolean shouldExit) {
        this.shouldExit = shouldExit;
    }

    public boolean isMapCompleted() {
        return mapCompleted;
    }

    public void setMapCompleted(boolean mapCompleted) {
        this.mapCompleted = mapCompleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameState gameState = (GameState) o;
        return shouldExit == gameState.shouldExit && mapCompleted == gameState.mapCompleted && Objects.equals(mapVO, gameState.mapVO) && Objects.equals(playerVO, gameState.playerVO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapVO, playerVO, shouldExit, mapCompleted);
    }

    @Override
    public String toString() {
        return "GameState{" +
                "mapVO=" + mapVO +
                ", playerVO=" + playerVO +
                ", shouldExit=" + shouldExit +
                ", mapCompleted=" + mapCompleted +
                '}';
    }
}
