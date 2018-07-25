package com.ykmimi.gobang.entity;

public class ChessPiece {
    private String pieceName;//棋子名称
    private String pieceCoordinate;//棋子坐标

    public ChessPiece() {

    }

    public ChessPiece(String pieceName){
        this.pieceName = pieceName;
    }

    public ChessPiece(String pieceName,String coordinate){
        this.pieceName=pieceName;
        this.pieceCoordinate=coordinate;
    }
    public String getPieceName() {
        return pieceName;
    }




    public void setPieceName(String pieceName) {
        this.pieceName = pieceName;
    }

    public String getPieceCoordinate() {
        return pieceCoordinate;
    }

    public void setPieceCoordinate(String pieceCoordinate) {
        this.pieceCoordinate = pieceCoordinate;
    }

    @Override
    public String toString() {
        return  pieceName +"放置棋盘位置为:"+ pieceCoordinate;
    }
}
