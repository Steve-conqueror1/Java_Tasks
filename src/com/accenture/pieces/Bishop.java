package com.accenture.pieces;

import com.accenture.game.PieceType;
import com.accenture.game.PlayerColor;

import static com.accenture.board.Board.currentPlayer;
import static com.accenture.board.Board.pieces;
import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

public class Bishop  extends Piece{

    public Bishop(int x, int y) {
        super(x, y);
    }

    /**
     * слон может двигаться по диагонали
     * */
    @Override
    public  boolean isCorrectMove(int endX, int endY) {

        if (bishopCanCapture(endX, endY)){
            return true;

        }


        if((Math.abs(endX-this.x)==Math.abs(endY-this.y)) && bishopJumped(endX, endY) ==false && pieces[endX][endY] == (int)'\u0000'){
            return true;
        }

        return false;

    }

    /**
     *этот метод возвращает true, если слон перепрыгнул через другой кусок
     * @param endX - конечное местоположение X
     * @param endY - конечное местоположение Y
     * */
    public boolean bishopJumped(int endX, int endY) {

        if (endY - this.y > 1) {

            int j = this.y+1;
            for (int i = this.x + 1; i > endX; i--) {

                    if (toLowerCase(pieces[i][j]) == PieceType.getPiece(toLowerCase(pieces[i][j]))
                            && PieceType.getPiece(toLowerCase(pieces[i][j])) != (int)'\u0000') {
                        return true;
                }
                j=j+1;
            }

            for (int i = this.x + 1; i < endX; i++) {

                    if (toLowerCase(pieces[i][j]) == PieceType.getPiece(toLowerCase(pieces[i][j]))
                            && PieceType.getPiece(toLowerCase(pieces[i][j])) != (int)'\u0000') {
                        return true;
                    }

                j=j+1;
            }
        }

        if (this.y - endY > 1){

            int j = this.y-1;
            for ( int i = this.x-1; i > endX; i-- ){

                   if (toLowerCase(pieces[i][j]) == PieceType.getPiece(toLowerCase(pieces[i][j]))
                           && PieceType.getPiece(toLowerCase(pieces[i][j])) != (int)'\u0000') {
                    return true;
                }
                j=j-1;
            }

            for ( int i = this.x+1; i < endX; i++ ){

                    if (toLowerCase(pieces[i][j]) == PieceType.getPiece(toLowerCase(pieces[i][j])) && pieces[i][j] != (int)'\u0000') {
                        return true;
                    }
                j=j-1;
            }
        }
        return false;
    }

    /**
     * слон может захватить другую фигуру, перемещаясь в эту позицию по диагонали и не перепрыгивая через другие фигуры
     * @param endX - конечное местоположение X
     * @param endY - конечное местоположение Y
     * */
    private boolean bishopCanCapture(int endX, int endY) {

       if (currentPlayer.equals(PlayerColor.WHITE.getPlayer()) && pieces[endX][endY] == toUpperCase(PieceType.getPiece(pieces[endX][endY]))
               && pieces[endX][endY] != (int)'\u0000' && bishopJumped(endX, endY)==false){
             return true;
       }

       if (currentPlayer.equals(PlayerColor.BLACK.getPlayer()) && pieces[endX][endY] == PieceType.getPiece(pieces[endX][endY])
               && pieces[endX][endY] != (int)'\u0000' && bishopJumped(endX, endY)==false){
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
