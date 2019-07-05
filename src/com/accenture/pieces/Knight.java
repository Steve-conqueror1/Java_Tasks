package com.accenture.pieces;

import com.accenture.game.PieceType;
import com.accenture.game.PlayerColor;

import static com.accenture.board.Board.currentPlayer;
import static com.accenture.board.Board.pieces;
import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

public class Knight  extends Piece{

    public Knight(int x, int y) {
        super(x, y);
    }

    /**
     * конь может двигаться на два шага по вертикали и один по горизонтали или два шага по горизонтали и один по вертикали
    * */
    @Override
    public boolean isCorrectMove(int endX, int endY) {

        if (knightCanCapture(endX, endY)){
            return true;
        }

       if((Math.abs(endY-this.y)==2 && Math.abs(endX-this.x)==1) || (Math.abs(endX-this.x) == 2
               && Math.abs(endY-this.y)==1) && pieces[endX][endY]==(int)'\u0000'){
           System.out.println("Knight moved correctly");
           return true;
       }
       return false;
    }

    /**
     * конь может захватить фигуру противника, расположенную в его конечных координатах
     * @param endX - конечное местоположение X
     * @param endY - конечное местоположение Y
     * */
    private boolean knightCanCapture(int endX, int endY) {

        if (currentPlayer.equals(PlayerColor.WHITE.getPlayer())
                && pieces[endX][endY]==toUpperCase(PieceType.getPiece(toLowerCase(pieces[endX][endY]))) &&  pieces[endX][endY] != (int) '\u0000'){
            return true;
        }

        if (currentPlayer.equals(PlayerColor.BLACK.getPlayer())
                && pieces[endX][endY] == PieceType.getPiece(pieces[endX][endY]) && pieces[endX][endY] != (int)'\u0000'){
            return true;
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
