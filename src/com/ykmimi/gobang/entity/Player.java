package com.ykmimi.gobang.entity;

import java.security.PrivateKey;
import java.util.ArrayList;

public class Player {
    private String name;
    private ChessPiece cp;
    private ArrayList<String> playerCoordinateList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChessPiece getCp() {
        return cp;
    }

    public void setCp(ChessPiece cp) {
        this.cp = cp;
    }

    public ArrayList<String> getPlayerCoordinateList() {
        return playerCoordinateList;
    }

    /////* 设置该玩家的坐标集合,也就是该棋子的所有位置
    public void setPlayerCoordinateList(String playerCoordinate) {
        if(playerCoordinateList==null){
            playerCoordinateList = new ArrayList<>();
        }
        this.playerCoordinateList.add(playerCoordinate);
    }

    @Override
    public String toString() {
        return name + "操作->" + cp;
    }
}
