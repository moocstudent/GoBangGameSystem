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
    private HashMap<Integer, Player> step = new HashMap<>();
    private int count = 0;

    public HashMap<String, HashMap<Integer, Player>> stepGame() {
        //while


        play1Step();
        boardRecord();
        play2Step();
        boardRecord();
        cb.setChessboard(gobangMap);
        cb.setStepMap(step);
        System.out.println(getCb());
        /////*简单起见,直接把棋局与棋盘传给裁判类
//        getReferee().checkStepMap(step);
        getReferee().checkChessBoard(gobangMap);


        return gobangMap;

    }

    /////* 玩家1操作 需要一集合,去存放玩家1的所有棋子位置
    public void play1Step() {
        System.out.println("输入玩家1要走的棋盘位置:");
        String coord1 = in.next();
        player1.setCp(new ChessPiece(player1.getCp().getPieceName(), coord1));
        step = gobangMap.get(coord1);
        if (step != null) {
            System.out.println("棋盘坐标重复!");
            play1Step();
            return;
        }else{
            step = new HashMap<>();
        }
        count++;
        step.put(count, player1);
        player1.setPlayerCoordinateList(coord1);
        gobangMap.put(coord1, step);
    }
    /////* 玩家2操作
    public void play2Step() {
        System.out.println("输入玩家2要走的棋盘位置:");
        String coord2 = in.next();
        player2.setCp(new ChessPiece(player2.getCp().getPieceName(), coord2));//有点重复的意味,又一个坐标
        step = gobangMap.get(coord2);
        if (step != null) {
            System.out.println("棋盘坐标重复!");
            play2Step();
            return;
        }else{
            step = new HashMap<>();
        }
        count++;
        step.put(count, player2);
        player2.setPlayerCoordinateList(coord2);
        gobangMap.put(coord2, step);
    }

    /////* 棋盘内容统计
    public void boardRecord(){

        Set<String> coord = gobangMap.keySet();
        Set<Integer> stepNO =step.keySet();
        for (Integer stepno : stepNO){
            System.out.println("第 "+stepno+" 步,"+ step.get(stepno));

//            step.get(stepno).getCp().getPieceName();
//            step.get(stepno).getCp().getPieceCoordinate();
        }
    }


}
