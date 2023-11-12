package hu.nye.progtech.wumplus.service.player;

import hu.nye.progtech.wumplus.model.MapVO;
import hu.nye.progtech.wumplus.model.PlayerVO;

import java.util.List;
import java.util.StringTokenizer;

public class PlayerParser {

    private final String playerName;

    public PlayerParser(String player) {
        playerName = player;
    }

    public PlayerVO parsePlayer(List<String> rawMap) {
        String row = rawMap.get(0);
        StringTokenizer tokenizer = new StringTokenizer(row, " ");

        String dimensionBlock = tokenizer.nextToken();
        String playerColumnBlock = tokenizer.nextToken();
        String playerRowBlock = tokenizer.nextToken();
        String playerDirectionBlock = tokenizer.nextToken();

        Integer dimensionValue = Integer.parseInt(dimensionBlock);
        Character playerColumnValue = playerColumnBlock.charAt(0);
        Integer playerRowValue = Integer.parseInt(playerRowBlock);
        Character playerDirectionValue = playerDirectionBlock.charAt(0);

        PlayerVO result = new PlayerVO(playerName, playerDirectionValue, playerColumnValue, playerRowValue);

        return result;
    }

    public void setGameLogicInformation(PlayerVO playerVO, MapVO mapVO){}
}
