package com.ykmimi.gobang.entity;

import com.ykmimi.gobang.game.Game;

import java.util.*;

public class Referee {

    private static ChessBoard cb = new ChessBoard();

    /////* 对棋盘遍历,直到获取到有5连的情况.
    public void checkChessBoard(HashMap<String,HashMap<Integer,Player>> gobangMap) {
        Set<String> coordinateSet = gobangMap.keySet();
        Set<Integer> stepNumSet = null;
        TreeSet<Integer> stepNumTreeSet = null;
        HashMap<Integer,Player> stepMap = null;
        /////* 遍历棋子坐标系集合
        for(String coord : coordinateSet){
            /////* stepNum获取步数集合
            stepNumSet = gobangMap.get(coord).keySet();
            stepNumTreeSet = new TreeSet<>();
            stepMap = gobangMap.get(coord);
            stepNumTreeSet.addAll(stepNumSet);
            Iterator stepItr = stepNumTreeSet.iterator();
            while(stepItr.hasNext()){
                System.out.println("--------------------------------------");
                System.out.print("第"+stepNumSet+"步,坐标:"+coord);

                System.out.println(stepMap.get(stepItr.next()).getCp().getPieceName());
            }


//            for (Integer step : stepItr ){
//
//            System.out.println("--------------------------------------");
//            System.out.print("第"+stepNumSet+"步,坐标:"+coord);
//
//                System.out.println(stepMap.get(step).getCp().getPieceName());
//            }
           /////* 这个坐标是全部的坐标,包括黑棋和白棋
            /////* 其后需要单独的白棋的坐标, 黑棋的坐标 见方法 checkStepMap
        }
       // +stepMap.get(stepNumSet.toArray()).getCp().getPieceName())



//        for(Integer step: stepNumSet){
//            System.out.print(stepMap.get(step).getName()+"的:"+stepMap.get(step).getCp().getPieceName());
//            System.out.println("--------------------------------------");
//        }
    }
    /////* 对玩家的棋进行单独计算其数量
    int white = 0;
    int black = 0;
    public void setPlayerInstance(Player p1,Player p2) {
        String p1Name = p1.getName();
        String p1PieceName = p1.getCp().getPieceName();
        String p2Name = p2.getName();
        String p2PieceName = p2.getCp().getPieceName();
//        System.out.println("玩家1"+p1.getName()+p1.getCp().getPieceName()+"的棋子数量:"+p1.getPlayerCoordinateList().size());
//        System.out.println("玩家2"+p2.getName()+p2.getCp().getPieceName()+"的棋子数量:"+p2.getPlayerCoordinateList().size());
        white = p1.getPlayerCoordinateList().size();
        black = p2.getPlayerCoordinateList().size();
        System.out.println("玩家1"+p1Name+p1PieceName+"有:"+white+"个.\n"+"玩家2"+p2Name+p2PieceName+"有:"+white+"个.");

    }

    public void getChessBoard(){
        System.out.println(cb);


    }

    /////* 一个迭代方法,传入棋子位置
    public void checkChessPieceCoordinate(ChessPiece cp){

    }

}
