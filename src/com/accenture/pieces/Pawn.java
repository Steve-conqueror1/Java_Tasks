package com.accenture.pieces;

import com.accenture.board.Board;
import com.accenture.game.PieceType;
import com.accenture.game.PlayerColor;

import static com.accenture.board.Board.currentPlayer;
import static com.accenture.board.Board.pieces;
import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

public class Pawn extends Piece {
    static int countA=0;
    static int countB=0;

    public Pawn(int x, int y) {
        super(x, y);
    }

    /**
     * Пешка может двигаться на один шаг вертикально
     * При первом движении он может двигаться на два шага
     * */
    @Override
    public boolean isCorrectMove(int endX, int endY) {
        if (pawnCanCapture(x, y, endX, endY)){
            System.out.println(" its a capture ");
            return true;
        }

        if ((countA==0 && Board.currentPlayer.equals(PlayerColor.WHITE.getPlayer())
                && (pieces[x][y]==PieceType.getPiece(pieces[x][y]))) && ((Math.abs(endY-this.y)==1 && (endX==this.x))|| (Math.abs(endY-this.y)==2 && (endX==this.x)))){
            countA =countA+1;
            System.out.println(" countA == 0");

            return true;
        }

        if ((countA > 0 && Board.currentPlayer.equals(PlayerColor.WHITE.getPlayer()))
                && (pieces[x][y]==PieceType.getPiece(pieces[x][y])) && (Math.abs(endY-this.y)==1 && endX == this.x)){
            System.out.println(" countA > 0  ");

            return true;
        }

        if ((countB==0 && Board.currentPlayer.equals(PlayerColor.BLACK.getPlayer())
                && (pieces[x][y]==toUpperCase(PieceType.getPiece(toLowerCase(pieces[x][y])))) && ((Math.abs(endY-this.y)==1 && endX == this.x)|| (Math.abs(endY-this.y)==2 && endX==this.x)))){
            countB =countB+1;
            System.out.println(" countB==0  ");
            return true;
        }

        if ( countB > 0 && Board.currentPlayer.equals(PlayerColor.BLACK.getPlayer())
                && (pieces[x][y]==toUpperCase(PieceType.getPiece(toLowerCase(pieces[x][y])))) && (Math.abs(endY-this.y)==1 && endX==this.x)){
            System.out.println(" countB > 0  ");
            return true;
        }
        return false;
    }

    private boolean pawnCanCapture(int startX, int startY, int endX, int endY) {
        if (pieces[endX][endY]!= (int)'\u0000' && ((Math.abs(endY-startY)==1) && (Math.abs(endX-startX)==1))
                && (pieces[x][y]!= PieceType.getPiece(pieces[endX][endY])
                 && currentPlayer.equals(PlayerColor.BLACK.getPlayer()) )){
            return true;
        }

        if (pieces[endX][endY]!= (int)'\u0000' && (Math.abs(endY-startY)==1) && (Math.abs(endX-startX)==1)
                && (pieces[x][y]!= toUpperCase(PieceType.getPiece(toLowerCase(pieces[endX][endY])))
                 && currentPlayer.equals(PlayerColor.WHITE.getPlayer()) )){
            return true;
        }

        if ((Math.abs(endY-startY) ==1) && (endX == startX) && pieces[endX][endY]!=(int)'\u0000'){
            return false;
        }
        return false;
    }



    /**
     * переместить конь в новые координаты
     * */
    @Override
    public void makePath(int startX, int startY, int endX, int endY) {
        pieces[endX][endY] = pieces[x][y];
        pieces[this.x][this.y] = (int) '\u0000';
    }
}
