package com.ykmimi.gobang.game;

import com.ykmimi.gobang.entity.ChessBoard;
import com.ykmimi.gobang.entity.ChessPiece;
import com.ykmimi.gobang.entity.Player;
import com.ykmimi.gobang.entity.Referee;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Game {

    static Scanner in = new Scanner(System.in);
    private static ChessBoard cb = new ChessBoard();
    private static ChessPiece cp = new ChessPiece();
    private static Player player1 = new Player();
    private static Player player2 = new Player();
    private static Referee referee = new Referee();

    public static ChessBoard getCb() {
        return cb;
    }

    public static void setCb(ChessBoard cb) {
        Game.cb = cb;
    }

    public static ChessPiece getCp() {
        return cp;
    }

    public static void setCp(ChessPiece cp) {
        Game.cp = cp;
    }

    public static Player getPlayer1() {
        return player1;
    }

    public static void setPlayer1(Player player1) {
        Game.player1 = player1;
    }

    public static Player getPlayer2() {
        return player2;
    }

    public static void setPlayer2(Player player2) {
        Game.player2 = player2;
    }

    public static Referee getReferee() {
        return referee;
    }


    public Game() {
        System.out.println("棋盘大小为: " + cb.getSize());
        System.out.println("输入玩家1姓名(玩家1先手):");
        player1.setName(in.next());
        System.out.println("玩家1姓名为:" + player1.getName());
        System.out.println("请输入玩家1所执的棋子(黑棋/白棋):");
        player1.setCp(new ChessPiece(in.next()));
        System.out.println("玩家1执 " + player1.getCp().getPieceName() + " 战斗!");
        System.out.println("请输入玩家2姓名:");
        player2.setName(in.next());
        System.out.println("玩家2姓名为:" + player2.getName());
        if (player1.getCp().getPieceName().equals("黑棋")) {
            player2.setCp(new ChessPiece("白棋"));
            System.out.println("玩家2执 " + player2.getCp().getPieceName() + " 战斗!");
        } else {
            player2.setCp(new ChessPiece("黑棋"));
            System.out.println("玩家2执 " + player2.getCp().getPieceName() + " 战斗!");
        }
        referee.getChessBoard();


    }

    private HashMap<String, HashMap<Integer, Player>> gobangMap = new HashMap<String, HashMap<Integer, Player>>();
    private HashMap<Integer, Player> step;
    private HashMap<Integer, Player> stepMap;
    private int count = 0;


    /////////////////////////////////////////////////////////////
    /////* 游戏进行,循环执行stepGame.[应在游戏某方胜出或结束时循环结束]
//    public HashMap<String, HashMap<Integer, Player>> stepGame() { 原属性

    /////*应该使play1Step返回Set集合,返回胜出时的那个5连子位置
       public boolean stepGame() {

        boolean p1GoOn =  play1Step();
        if(!p1GoOn==true){
            System.err.println(player1.getName()+"的"+player1.getCp().getPieceName()+"胜出!");
            return p1GoOn;
        }
        boardRecord();
        boolean p2GoOn = play2Step();
        if(!p2GoOn==true){
            System.err.println(player2.getName()+"的"+player2.getCp().getPieceName()+"胜出!");
            return p2GoOn;
        }
        boardRecord();
        cb.setChessboard(gobangMap);
        cb.setStepMap(stepMap);
//        System.out.println(getCb());
        /////*简单起见,直接把棋局与棋盘传给裁判类
//        getReferee().checkStepMap(step);

        referee.checkChessBoard(stepMap);
        referee.setPlayerInstance(player1, player2);

        //else 输入玩家名及 新坐标 表示悔棋 可两个玩家同时输入.

        return p1GoOn&&p2GoOn;

    }

    /////* 玩家1操作 需要一集合,去存放玩家1的所有棋子位置
    public boolean play1Step() {
        System.out.println("输入玩家1要走的棋盘位置:");
        String coord1 = in.next();
        player1.setCp(new ChessPiece(player1.getCp().getPieceName(), coord1));
        step = gobangMap.get(coord1);
        if (step != null) {
            System.out.println("棋盘坐标重复!");
            play1Step();
            return true;
        } else {
            stepMap = cb.getStepMap();
        }
        count++;
        stepMap.put(count, player1);/////* 小地图放入计数步次和该玩家实例.其中该玩家实例已经传入了棋子和位置.
        player1.setPlayerCoordinateList(coord1);
//        referee.goBangjudger(player1);
        gobangMap.put(coord1, stepMap);
        boolean p1NoWin = referee.checkChessPieceCoordinate(coord1,player1);
        return p1NoWin;
    }

    /////* 玩家2操作  [与play1Step相似,应转换为同一方法]
    public boolean play2Step() {
        System.out.println("输入玩家2要走的棋盘位置:");
        String coord2 = in.next();
        player2.setCp(new ChessPiece(player2.getCp().getPieceName(), coord2));//有点重复的意味,又一个坐标
        step = gobangMap.get(coord2);
        if (step != null) {
            System.out.println("棋盘坐标重复!");
            play2Step();
            return true;
        } else {
            stepMap = cb.getStepMap();
        }
        count++;
        stepMap.put(count, player2);
        player2.setPlayerCoordinateList(coord2);
//        referee.goBangjudger(player2);
        gobangMap.put(coord2, stepMap);
        boolean p2NoWin = referee.checkChessPieceCoordinate(coord2,player2);
        return p2NoWin;
    }

    /////* 棋盘内容统计
    public void boardRecord() {

    }


}
