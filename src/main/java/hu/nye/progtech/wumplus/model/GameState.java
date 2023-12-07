package hu.nye.progtech.wumplus.model;

import java.util.Objects;

/**
 * Játék állapota.
 */
public class GameState {
    private MapVO mapVO;
    private PlayerVO playerVO;
    private boolean mapCompleted;
    private boolean playerDead;
    private boolean playerWon;
    private boolean givUp;
    private boolean pause;
    private boolean shouldExit;

    public GameState(MapVO mapVO, PlayerVO playerVO, boolean shouldExit, boolean mapCompleted) {
        this.mapVO = mapVO;
        this.playerVO = playerVO;
        this.mapCompleted = mapCompleted;
        this.playerDead = false;
        this.givUp = false;
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
    
    public boolean isPlayerDead() {
        return playerDead;
    }

    public void setPlayerDead(boolean playerDead) {
        this.playerDead = playerDead;
    }

    public boolean isPlayerWon() {
        return this.playerWon;
    }

    public void setPlayerWon(boolean playerWon) {
        this.playerWon = playerWon;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public boolean isGiveUp() {
        return givUp;
    }

    public void setGiveUp(boolean givUp) {
        this.givUp = givUp;
    }

    /**
     * Másolat létrehozása.
     *
     * @return új objektum
     */
    public GameState deepCopy() {
        GameState result = new GameState(mapVO.deepCopy(), playerVO.deepCopy(), false, mapCompleted);
        result.setPlayerDead(playerDead);
        result.setGiveUp(givUp);
        result.setPause(pause);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameState gameState = (GameState) o;
        return mapCompleted == gameState.mapCompleted && playerDead == gameState.playerDead &&
                givUp == gameState.givUp && pause == gameState.pause &&
                Objects.equals(mapVO, gameState.mapVO) && Objects.equals(playerVO, gameState.playerVO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapVO, playerVO, mapCompleted, playerDead, givUp, pause);
    }

    @Override
    public String toString() {
        return "GameState{" +
                "mapVO=" + mapVO +
                ", playerVO=" + playerVO +
                ", mapCompleted=" + mapCompleted +
                ", playerDead=" + playerDead +
                ", givUp=" + givUp +
                ", pause=" + pause +
                '}';
    }

    public String getPlayerName() {
        return playerVO.getName();
    }

    public char[][] getMapElement() {
        return mapVO.getMap();
    }

    public int getPlayerX() {
        return playerVO.getCoordX();
    }

    public int getPlayerY() {
        return playerVO.getCoordY();
    }
}
