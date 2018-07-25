package com.ykmimi.gobang.entity;

import java.util.HashMap;
import java.util.Set;

public class ChessBoard {
    private final String size = "15*15";
    private HashMap<String, HashMap<Integer, Player>> chessboard;
    private HashMap<Integer,Player> stepMap;


    public String getSize() {
        return size;
    }

    public HashMap<Integer, Player> getStepMap() {
        return stepMap;
    }

    public void setStepMap(HashMap<Integer, Player> stepMap) {
        this.stepMap = stepMap;
    }

    public HashMap<String, HashMap<Integer, Player>> getChessboard() {
        return chessboard;
    }

    public void setChessboard(HashMap<String, HashMap<Integer, Player>> chessboard) {
        this.chessboard = chessboard;
    }

    @Override
    public String toString() {
        int black = 0;
        int white = 0;
        Set<String> coord;
        Set<Integer> stepNO;
        if (getChessboard() == null) {
            return "裁判正在布置棋盘.";
        } else {
            coord = getChessboard().keySet();
            stepNO = getStepMap().keySet();
        }
//        for(Integer step : stepNO){
//            if( getStepMap().get(step).getCp().getPieceName().equals("白棋"));white++;
//            if( getStepMap().get(step).getCp().getPieceName().equals("黑棋"));black++;
//        }




        return "棋盘上现有棋子数量: " + coord.size() + " \n";
//                "其中黑棋: " + black + " 枚, 白棋: " + white + " 枚.";
    }


}
