package hu.nye.progtech.wumplus.model;

import java.util.Objects;

/**
 * Játék állapota.
 */
public class GameState {
    private MapVO mapVO;
    private PlayerVO playerVO;
//    private boolean shouldExit;
    private boolean mapCompleted;

    private boolean playerDead;
    private boolean playerWon;
    private boolean givUp;

    private boolean pause;

    public GameState(MapVO mapVO, PlayerVO playerVO, boolean shouldExit, boolean mapCompleted) {
        this.mapVO = mapVO;
        this.playerVO = playerVO;
        this.mapCompleted = mapCompleted;
        this.playerDead = false;
        this.playerWon = false;
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
        return givUp || pause || playerDead || playerWon;
    }
    public boolean isMapCompleted() {
        return mapCompleted;
    }

    public void setMapCompleted(boolean mapCompleted) {
        this.mapCompleted = mapCompleted;
    }

    public boolean isPlayerDead() {
        return playerDead;
    }

    public void setPlayerDead(boolean playerDead) {
        this.playerDead = playerDead;
    }

    public GameState deepcCopy() {
        GameState result = new GameState(mapVO.deepCopy(), playerVO.deepCopy(), false, mapCompleted);
        result.setPlayerDead(playerDead);
        result.setPlayerWon(playerWon);
        result.setGivUp(givUp);
        result.setPause(pause);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameState gameState = (GameState) o;
        return mapCompleted == gameState.mapCompleted && playerDead == gameState.playerDead && playerWon == gameState.playerWon && givUp == gameState.givUp && pause == gameState.pause && Objects.equals(mapVO, gameState.mapVO) && Objects.equals(playerVO, gameState.playerVO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapVO, playerVO, mapCompleted, playerDead, playerWon, givUp, pause);
    }


    @Override
    public String toString() {
        return "GameState{" +
                "mapVO=" + mapVO +
                ", playerVO=" + playerVO +
                ", mapCompleted=" + mapCompleted +
                ", playerDead=" + playerDead +
                ", playerWon=" + playerWon +
                ", givUp=" + givUp +
                ", pause=" + pause +
                '}';
    }

    public String getPlayerName() {
        return playerVO.getName();
    }

    public boolean isPlayerWon() {
        return playerWon;
    }

    public void setPlayerWon(boolean playerWon) {
        this.playerWon = playerWon;
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

    public boolean isGivUp() {
        return givUp;
    }

    public void setGivUp(boolean givUp) {
        this.givUp = givUp;
    }

    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }
}
