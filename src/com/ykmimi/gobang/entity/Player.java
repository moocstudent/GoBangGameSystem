package com.ykmimi.gobang.entity;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Player {
    private String name;
    private ChessPiece cp;
    /////* 玩家下过的坐标的List集合
    private ArrayList<String> playerCoordinateList  = new ArrayList<>();
    /////* 玩家的已下的棋子(用于裁判判定)
//    private Set multiplyFLineA = new HashSet();//交叉父线前段
//    private Set multiplyFLineB = new HashSet();//交叉父线后段
//    private Set multiplyMLineA = new HashSet();//交叉母线前段
//    private Set multiplyMLineB;//交叉母线后段
//
//    private Set plusFLineA = new HashSet();//十字父线前段
//    private Set plusFLineB = new HashSet();//十字父线后段
//    private Set plusMLineA = new HashSet();//十字母线前段
//    private Set plusMLineB = new HashSet();//十字母线后段
//
//    public Set getMultiplyFLineA() {
//        return multiplyFLineA;
//    }
//
//    public void setMultiplyFLineA(Set multiplyFLineA) {
//        this.multiplyFLineA = multiplyFLineA;
//    }
//
//    public Set getMultiplyFLineB() {
//        return multiplyFLineB;
//    }
//
//    public void setMultiplyFLineB(Set multiplyFLineB) {
//        this.multiplyFLineB = multiplyFLineB;
//    }
//
//    public Set getMultiplyMLineA() {
//        return multiplyMLineA;
//    }
//
//    public void setMultiplyMLineA(Set multiplyMLineA) {
//        this.multiplyMLineA = multiplyMLineA;
//    }
//
//    public Set getMultiplyMLineB() {
//        return multiplyMLineB;
//    }
//
//    public void setMultiplyMLineB(Set multiplyMLineB) {
//        this.multiplyMLineB = multiplyMLineB;
//    }
//
//    public Set getPlusFLineA() {
//        return plusFLineA;
//    }
//
//    public void setPlusFLineA(Set plusFLineA) {
//        this.plusFLineA = plusFLineA;
//    }
//
//    public Set getPlusFLineB() {
//        return plusFLineB;
//    }
//
//    public void setPlusFLineB(Set plusFLineB) {
//        this.plusFLineB = plusFLineB;
//    }
//
//    public Set getPlusMLineA() {
//        return plusMLineA;
//    }
//
//    public void setPlusMLineA(Set plusMLineA) {
//        this.plusMLineA = plusMLineA;
//    }
//
//    public Set getPlusMLineB() {
//        return plusMLineB;
//    }
//
//    public void setPlusMLineB(Set plusMLineB) {
//        this.plusMLineB = plusMLineB;
//    }

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

    /////* 设置该玩家棋子的坐标集合
    public void setPlayerCoordinateList(String playerCoordinate) {
        this.playerCoordinateList.add(playerCoordinate);
    }

    @Override
    public String toString() {
        return name + "操作->" + cp;
    }
}
