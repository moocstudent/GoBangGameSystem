package com.ykmimi.gobang.action;

import com.ykmimi.gobang.game.Game;

public class GoBangGameStart {


    public static void main(String[] args) {
        System.out.println("欢迎试玩魔偶五子棋游戏.");
        Game gameInit = new Game();
        while(gameInit.stepGame());



    }
}
