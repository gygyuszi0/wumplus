//package hu.nye.progtech.wumplus.service.player;
//
//import hu.nye.progtech.wumplus.model.Player;
//
//import java.util.List;
//import java.util.StringTokenizer;
//
//public class PlayerParser {
//
//    private final String playerName;
//
//    public PlayerParser(List<String> rawMap, String player) {
//        String initialRow = rawMap.get(0);
//        playerName = player;
//    }
//
//    public Player parsePlayer(String row){
//
//        StringTokenizer tokenizer = new StringTokenizer(row, " ");
//
//        String dimensionBlock = tokenizer.nextToken();
//        String playerColumnBlock = tokenizer.nextToken();
//        String playerRowBlock = tokenizer.nextToken();
//        String playerDirectionBlock = tokenizer.nextToken();
//
//        Integer dimensionValue = Integer.parseInt(dimensionBlock);
//        Character playerColumnValue = playerColumnBlock.charAt(0);
//        Integer playerRowValue = Integer.parseInt(playerRowBlock);
//        Character playerDirectionValue = playerDirectionBlock.charAt(0);
//
//        // Player result = new Player(playerName, playerDirectionValue, )
//
//
//        return null;
//
//    }
//
//    private int readDimensions(String row){
//        StringTokenizer tokenizer = new StringTokenizer(row, " ");
//        String dimensionBlock = tokenizer.nextToken();
//        Integer dimensionValue = Integer.parseInt(dimensionBlock);
//
//        return dimensionValue;
//    }
//}
