package com.ykmimi.gobang.entity;

import com.ykmimi.gobang.game.Game;

import java.util.*;

public class Referee {

    private static ChessBoard cb = new ChessBoard();

    /////* 对棋盘遍历,直到获取到有5连的情况.
    public void checkChessBoard(HashMap<Integer, Player> stepMap) {
//        Set<String> coordinateSet = gobangMap.keySet();
        Set<Integer> stepNumSet = null;
        TreeSet<Integer> stepNumTreeSet = null;
    }


    /////* 对玩家的棋进行单独计算其数量
    int white = 0;
    int black = 0;

    public void setPlayerInstance(Player p1, Player p2) {
        String p1Name = p1.getName();
        String p1PieceName = p1.getCp().getPieceName();
        String p2Name = p2.getName();
        String p2PieceName = p2.getCp().getPieceName();
//        System.out.println("玩家1"+p1.getName()+p1.getCp().getPieceName()+"的棋子数量:"+p1.getPlayerCoordinateList().size());
//        System.out.println("玩家2"+p2.getName()+p2.getCp().getPieceName()+"的棋子数量:"+p2.getPlayerCoordinateList().size());
        white = p1.getPlayerCoordinateList().size();
        black = p2.getPlayerCoordinateList().size();
        System.out.println("玩家1" + p1Name + p1PieceName + "有:" + white + "个.\n" + "玩家2" + p2Name + p2PieceName + "有:" + white + "个.");
        System.out.println("本次两玩家的交手有:");
        System.out.println(p1.getCp().getPieceName() + p1.getCp().getPieceCoordinate());
        System.out.println(p2.getCp().getPieceName() + p2.getCp().getPieceCoordinate());
        System.out.println("玩家1" + p1.getCp().getPieceName() + "的所有棋子坐标:");
        System.out.println(p1.getPlayerCoordinateList());
        System.out.println("玩家2" + p2.getCp().getPieceName() + "的所有棋子坐标:");
        System.out.println(p2.getPlayerCoordinateList());
    }

    public void getChessBoard() {
        System.out.println(cb);


    }

    ArrayList<String> playerCoordinateList;

    /////* 最终审判,对每次棋手落子时该棋手的棋子是否组成5连进行判定.
    public ArrayList<String> goBangjudger(Player player) {
        /////* 传入player对象,对其含有的playCoordinate坐标集合进行遍历,看看是否有5连
        playerCoordinateList = player.getPlayerCoordinateList();
        System.out.println("playerCoordinateList.size()" + playerCoordinateList.size());
        System.out.println("此时玩家" + player.getName() + "的棋子位置有:");
        for (int i = 0; i < playerCoordinateList.size(); i++) {
            String coords = playerCoordinateList.get(i);
            System.out.print(coords + " ");
            if (coords.length() == 2) {
                String row = coords.substring(0, 1);
                String column = coords.substring(1, 2);
                System.out.println(row + "行" + column + "列");

            }
            if (coords.length() == 3) {
                String row = coords.substring(0, 2);
                String column = coords.substring(2, 3);
                System.out.println(row + "行" + column + "列");
            }
        }
        System.out.println();
        /////*获取最后一个传入的棋子,判定该棋子所处的位置与之对应的交叉和十字关系
        String lastCoord = playerCoordinateList.get(playerCoordinateList.size() - 1);
        return playerCoordinateList;


    }

    ArrayList<String> coordList;

    /////* 一个迭代方法,传入最后的棋子位置,对其进行交叉和十字的判定,如果有玩家胜出noWinner则返回false
    public boolean checkChessPieceCoordinate(String coord, Player p) {
        String x = "";
        String y = "";
        /////* 对不同的坐标长度的表示分别判断
        if (coord.length() == 2) {
            x = coord.substring(0, 1);
            y = coord.substring(1, 2);
        }
        if (coord.length() == 3) {
            x = coord.substring(0, 2);
            y = coord.substring(2, 3);
        }
        boolean angle = (x.equals("15") || (x.equals("1"))) && ((y.equals("A")) || ((y.equals("O"))));
        boolean normal = x.hashCode() > "1".hashCode() && y.hashCode() > "A".hashCode() && x.hashCode() < "15".hashCode() && y.hashCode() < "O".hashCode();
        boolean edge = !(angle) && !(normal);
        coordList = goBangjudger(p);
        String lastCoord = coordList.get(coordList.size() - 1);
        /////* 如果棋子在顶角角落,则对横线,竖线,右下斜线判定是否有同色棋子
        /////* 如果不这样判定,因为不同于细胞自动机,这个是没有下标越界异常的.
        /////* 所以每次都可以对交叉十字去判定.不需要针对角,或者边,以及普通位置
//
        /////* 在当前棋手的所有棋子坐标中进行for循环遍历
//        for (int i = 0; i < coordList.size(); i++) {

        boolean noWinner = true;
        /////* 如果有与这次坐标相同的那么进入if (肯定是有的啊...因为棋子刚放入啊)
        if (coordList.contains(x + y)) {
//            System.out.println("原棋子坐标检测到了💗");
//
            /////* 交叉父线
            int fa = coordMultiplyFLineAJudger(x, y, coordList, p);
            int fb = coordMultiplyFLineBJudger(x, y, coordList, p);
            if (fa + fb + 1 == 5) {
//                System.err.println(p.getCp().getPieceName()+"胜出");
                noWinner = false;
                return noWinner;
            }else {
                setMultiplyFLineA(0);
                setMultiplyFLineB(0);
            }
            /////* 交叉母线
            int ma = coordMultiplyMLineAJudger(x, y, coordList, p);
            int mb = coordMultiplyMLineBJudger(x, y, coordList, p);
            if (ma + mb + 1 == 5) {

//                System.err.println(p.getCp().getPieceName()+"胜出");
                noWinner = false;
                return noWinner;
            }else {
                setMultiplyMLineA(0);
                setMultiplyMLineB(0);
            }

            /////* 十字父线
            int pfa = plusFLineAJudger(x, y, coordList, p);
            int pfb = plusFLineBJudger(x, y, coordList, p);
            if (pfa + pfb + 1 == 5) {
//                System.err.println(p.getCp().getPieceName()+"胜出");
                noWinner = false;
                return noWinner;
            }else{
                setPlusFLineA(0);
                setPlusFLineB(0);
            }
            /////* 十字母线
            int pma = plusMLineAJudger(x, y, coordList, p);
            int pmb = plusMLineBJudger(x, y, coordList, p);
            if (pma + pmb + 1 == 5) {
//                System.err.println(p.getCp().getPieceName()+"胜出");
                noWinner = false;
                return noWinner;
            }else{
                setPlusMLineA(0);
                setPlusMLineB(0);
            }


        }
        return noWinner;

    }

    /////* count
    int count = 0;//count可用来监测判定棋子的次数,比如只判定5次.
    private int multiplyFLineA = 0;//交叉父线前段
    private int multiplyFLineB = 0;//交叉父线后段
    private int multiplyMLineA = 0;//交叉母线前段
    private int multiplyMLineB = 0;//交叉母线后段

    public int getMultiplyFLineA() {
        return multiplyFLineA;
    }

    public void setMultiplyFLineA(int multiplyFLineA) {
        this.multiplyFLineA = multiplyFLineA;
    }

    public int getMultiplyFLineB() {
        return multiplyFLineB;
    }

    public void setMultiplyFLineB(int multiplyFLineB) {
        this.multiplyFLineB = multiplyFLineB;
    }

    public int getMultiplyMLineA() {
        return multiplyMLineA;
    }

    public void setMultiplyMLineA(int multiplyMLineA) {
        this.multiplyMLineA = multiplyMLineA;
    }

    public int getMultiplyMLineB() {
        return multiplyMLineB;
    }

    public void setMultiplyMLineB(int multiplyMLineB) {
        this.multiplyMLineB = multiplyMLineB;
    }

    private int plusFLineA = 0;//十字父线前段
    private int plusFLineB = 0;//十字父线后段
    private int plusMLineA = 0;//十字母线前段
    private int plusMLineB = 0;//十字母线后段

    public int getPlusFLineA() {
        return plusFLineA;
    }

    public void setPlusFLineA(int plusFLineA) {
        this.plusFLineA = plusFLineA;
    }

    public int getPlusFLineB() {
        return plusFLineB;
    }

    public void setPlusFLineB(int plusFLineB) {
        this.plusFLineB = plusFLineB;
    }

    public int getPlusMLineA() {
        return plusMLineA;
    }

    public void setPlusMLineA(int plusMLineA) {
        this.plusMLineA = plusMLineA;
    }

    public int getPlusMLineB() {
        return plusMLineB;
    }

    public void setPlusMLineB(int plusMLineB) {
        this.plusMLineB = plusMLineB;
    }

    /////* TODO 交叉父线A段判断  第1条线A段
    public int coordMultiplyFLineAJudger(String x, String y, ArrayList<String> coordList, Player p) {

        ////////////////*交叉父线(左上↖ 行+ ,列-, 右下↘是行-,列+, 这是交叉父路径.)
        /////* 交叉父线前段
        if (coordList.contains(((Integer.parseInt(x)+1) + (String.valueOf((char) (y.hashCode() - 1)))))) {

            multiplyFLineA++;
            return coordMultiplyFLineAJudger((Integer.parseInt(x)+1)+"", String.valueOf((char) (y.hashCode() - 1)), coordList, p);

        }else {
            return multiplyFLineA;
        }
    }/////*交叉父线A判断结束

    //TODO 交叉父线B段判断  第1条线B段
    public int coordMultiplyFLineBJudger(String x, String y, ArrayList<String> coordList, Player p) {

        /////* 交叉父线后段
        if (coordList.contains(((Integer.parseInt(x)-1) + (String.valueOf((char) (y.hashCode() + 1)))))) {

            multiplyFLineB++;
            return coordMultiplyFLineBJudger((Integer.parseInt(x)-1)+"", String.valueOf((char) (y.hashCode() + 1)), coordList, p);
        }else{
            return multiplyFLineB;
        }

    }/////交叉父线B判断结束

    /////* TODO 交叉母线A段判定 第2条线A段
    public int coordMultiplyMLineAJudger(String x, String y, ArrayList<String> coordList, Player p) {

        /////////*交叉母线(右上↗ 行+ ,列+, 左下↙是行-,列-, 这是交叉母路径.)
        /////* 交叉母线前段
        if (coordList.contains(((Integer.parseInt(x)+1) + (String.valueOf((char) (y.hashCode() + 1)))))) {

            multiplyMLineA++;
            return  coordMultiplyMLineAJudger((Integer.parseInt(x)+1)+"", String.valueOf((char) (y.hashCode() + 1)), coordList, p);

        }else{
            return multiplyMLineA;
        }
    }



    /////* TODO 交叉母线B段判定 第2条线B段
    public int coordMultiplyMLineBJudger(String x, String y, ArrayList<String> coordList, Player p) {
        //棋子进入判定 number=0
        if (coordList.contains(((Integer.parseInt(x)-1) + (String.valueOf((char) (y.hashCode() - 1)))))) {
            //棋子+1位置如果有同色,进入
            multiplyMLineB++;//1.再次进入下面方法number又回到0了
            return coordMultiplyMLineBJudger((Integer.parseInt(x)-1)+"", String.valueOf((char) (y.hashCode() - 1)), coordList, p);
        }else{
            //判断+1位置到尽头后,表示这一个循环结束,返回numbers
            //并且numbers还要归0
            return multiplyMLineB;
        }

    }/////* 交叉母线B段判断结束


    /////* TODO 十字父线A段判定 第3条线A段
    public int plusFLineAJudger(String x, String y, ArrayList<String> coordList, Player p) {

        /////////* 十字父线(上↑ 行+ ,列不动, 下↓是行-,列不动, 这是十字父路径.)
        /////* 十字父线前段
        if (coordList.contains((Integer.parseInt(x)+1) + y)) {
            plusFLineA++;
            return plusFLineAJudger((Integer.parseInt(x)+1)+"", y, coordList, p);
        }else {
            return plusFLineA;
        }
    }

    /////* TODO 十字父线B段判定 第3条线B段
    public int plusFLineBJudger(String x, String y, ArrayList<String> coordList, Player p) {

        /////* 十字父线后段
        if (coordList.contains((Integer.parseInt(x)-1) + y)) {
            plusFLineB++;
            return plusFLineBJudger((Integer.parseInt(x)-1)+"", y, coordList, p);

        }else {
            return plusFLineB;
        }

    }/////* 十字父线B段判定结束

    /////* TODO 十字母线A段判定 第4条线A段
    public int plusMLineAJudger(String x, String y, ArrayList<String> coordList, Player p) {

        /////////* 十字母线(右→ 行不动 ,列+, 左←是行不动,列-, 这是十字母路径.)
        /////* 十字母线前段
        if (coordList.contains((x + String.valueOf((char) (y.hashCode() + 1))))) {
            plusMLineA++;
            return plusMLineAJudger(x, String.valueOf((char) (y.hashCode() + 1)), coordList, p);
        }else {
            return plusMLineA;
        }
    }


    /////* TODO 十字母线B段判定 第4条线B段
    public int plusMLineBJudger(String x, String y, ArrayList<String> coordList, Player p) {

        if (coordList.contains((x + String.valueOf((char) (y.hashCode() - 1))))) {
            plusMLineB++;
            return plusMLineBJudger(x, String.valueOf((char) (y.hashCode() - 1)), coordList, p);
        }else {
            return plusMLineB;
        }
    }

    /////* 十字母线判断结束


//    public void coordProlongJudgement(String x, String y,ArrayList<String> coordList) {
//        /////* 自身
//
//        /////* 左上,行++列--
//        /////* 交叉父线 (行+列-)(行-列+)
//        for (int i = 0; i < coordList.size(); i++) {
//
////            /////* 检测到该传入x,y坐标,才进行判定,否则不进入.
////            if (coordList.get(j).equals(x + y)) {
////                System.out.println("该坐标检测到了💗");
////                int m = 0;
////                int n = 0;
//                count=0;
////                for (int i = 0; i < coordList.size(); i++) {
//                    ////////////////*交叉父线(左上↖ 行+ ,列-, 右下↘是行-,列+, 这是交叉父路径.)
//                    /////* 交叉父线前段
//                    if (coordList.get(i).equals((String.valueOf((char) (x.hashCode() + 1))) + (String.valueOf((char) (y.hashCode() - 1))))) {
//
//                        multiplyFLineA++;
//                        System.out.println("交叉父线前段+" + count + "位置有同色棋子");
//
//                        coordProlongJudgement(String.valueOf((char) (x.hashCode() + 1)), String.valueOf((char) (y.hashCode() - 1)),coordList);
//
//                    }
//                    /////* 交叉父线后段
//                    if (coordList.get(i).equals((String.valueOf((char) (x.hashCode() - 1))) + (String.valueOf((char) (y.hashCode() + 1))))) {
//
//                        multiplyFLineB++;
//                        System.out.println("交叉父线后段+" + count + "位置有同色棋子");
//
//                        coordProlongJudgement(String.valueOf((char) (x.hashCode() - 1)), String.valueOf((char) (y.hashCode() + 1)),coordList);
//                    }
//
//                    /////* 查看交叉父线是否满足5连
//                    if (multiplyFLineA + multiplyFLineB == 5) {
//                        System.out.println("该棋子满足5连.在交叉父线上.");
//                    }
//
//                    /////////*交叉母线(右上↗ 行+ ,列+, 左下↙是行-,列-, 这是交叉母路径.)
//                    /////* 交叉母线前段
//                    if (coordList.get(i).equals((String.valueOf((char) (x.hashCode() + 1))) + (String.valueOf((char) (y.hashCode() + 1))))) {
//
//                        multiplyMLineA++;
//                        System.out.println("交叉母线前段+" + count + "位置有同色棋子");
//
//                        coordProlongJudgement(String.valueOf((char) (x.hashCode() + 1)), String.valueOf((char) (y.hashCode() + 1)),coordList);
//
//                    }
//                    /////* 交叉母线后段
//                    if (coordList.get(i).equals((String.valueOf((char) (x.hashCode() - 1))) + (String.valueOf((char) (y.hashCode() - 1))))) {
//
//                        multiplyMLineB++;
//                        System.out.println("交叉父线后段+" + count + "位置有同色棋子");
//
//                        coordProlongJudgement(String.valueOf((char) (x.hashCode() - 1)), String.valueOf((char) (y.hashCode() - 1)),coordList);
//                    }
//////////////////////////////////////////////////////////////////////
//                    /////* 查看交叉母线是否满足5连
//                    if (multiplyMLineA + multiplyMLineB == 5) {
//                        System.out.println("该棋子满足5连.在交叉母线上.");
//                    }
//////////////////////////////////////////////////////////////////////////
//                    /////////* 十字父线(上↑ 行+ ,列不动, 下↓是行-,列不动, 这是十字父路径.)
//                    /////* 十字父线前段
//                    if (coordList.get(i).equals((String.valueOf((char) (x.hashCode() + 1))) + y)) {
//
//                        plusFLineA++;
//                        System.out.println("十字父线前段" + count + "位置有同色棋子");
//                        coordProlongJudgement(String.valueOf((char) (x.hashCode() + 1)), y,coordList);
//                    }
//                    /////* 十字父线后段
//                    if (coordList.get(i).equals((String.valueOf((char) (x.hashCode() - 1))) + y)) {
//
//                        plusFLineB++;
//                        System.out.println("十字父线后段" + count + "位置有同色棋子");
//                        coordProlongJudgement(String.valueOf((char) (x.hashCode() - 1)), y,coordList);
//                    }
//                    ////////////////////////
//                    /////* 查看十字父线是否满足5连
//                    if (plusFLineA + plusFLineB == 5) {
//                        System.out.println("该棋子满足5连.在十字父线上.");
//                    }
//////////////////////////////////////////////////////////////////////
//                    /////////* 十字母线(右→ 行不动 ,列+, 左←是行不动,列-, 这是十字母路径.)
//                    /////* 十字母线前段
//                    if (coordList.get(i).equals((x + String.valueOf((char) (y.hashCode() + 1))))) {
//
//                        plusMLineA++;
//                        System.out.println("十字父线前段" + count + "位置有同色棋子");
//                        coordProlongJudgement(x, String.valueOf((char) (y.hashCode() + 1)),coordList);
//                    }
//                    /////* 十字母线后段
//                    if (coordList.get(i).equals((x + String.valueOf((char) (y.hashCode() - 1))))) {
//
//                        plusMLineB++;
//                        System.out.println("十字父线后段" + count + "位置有同色棋子");
//                        coordProlongJudgement(x, String.valueOf((char) (y.hashCode() - 1)),coordList);
//                    }
//                    ////////////////////////
//                    /////* 查看十字父线是否满足5连
//                    if (plusMLineA + plusMLineB == 5) {
//                        System.out.println("该棋子满足5连.在十字母线上.");
//                    }
//
//
//
//
//
//        }
//
////        coordProlongJudgement(x, y);
//    }

}
