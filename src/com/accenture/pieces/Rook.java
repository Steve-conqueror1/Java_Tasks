package com.accenture.pieces;

import com.accenture.board.Board;
import com.accenture.game.PieceType;
import com.accenture.game.PlayerColor;

import static com.accenture.board.Board.currentPlayer;
import static com.accenture.board.Board.pieces;
import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

/**
 * Это класс для ладьи
 * */
public class Rook extends Piece {

    public Rook(int x, int y) {
        super(x, y);
    }

    /**
     * Ладья движется вертикально или горизонтально
     * @param endX - конечное местоположение X
     * @param endY - конечное местоположение Y
     * возвращает true, если перемещение было правильным
    * */
    @Override
    public boolean isCorrectMove(int endX, int endY) {

        if (rookCanCapture(endX, endY)){
            return true;
        }

        if(((endX == this.x)|| (endY == this.y)) && (pieces[endX][endY] == (int)'\u0000') && (rookJumped(endX, endY)==false)){
            return true;
        }

        return false;
    }


    /**
     * этот метод возвращает true, если ладья пытается захватить фигуру в новой координате
     * @param endX - конец х координат
     * @param endY - конец y координат
     * */
    public boolean rookCanCapture(int endX, int endY){

        if ((currentPlayer.equals(PlayerColor.WHITE.getPlayer()))
                && (pieces[endX][endY]==toUpperCase(PieceType.getPiece(toLowerCase(pieces[endX][endY]))))
                && (PieceType.getPiece(toLowerCase(pieces[endX][endY]))!='\u0000') && (rookJumped(endX, endY) == false)){

                 return true;
        }

        if ((currentPlayer.equals(PlayerColor.BLACK.getPlayer())) && (pieces[endX][endY]==PieceType.getPiece(pieces[endX][endY])
                && (PieceType.getPiece(pieces[endX][endY])!='\u0000') && rookJumped(endX, endY)==false)){

                   return true;
        }

        return false;
    }


    /**
     * Ладья не может прыгать другими фигурами
     * этот метод возвращает true, если ладья прыгнула
     * @param endX - конец х координат
     * @param endY - конец y координат
     * */
    public boolean rookJumped( int endX, int endY){

        if (Math.abs(endY-this.y)==1 && (endX == this.x)){
            return false;
        }

        if (Math.abs(endX-this.x)==1 && (endY == this.y)){
            return false;
        }

        //ладья движется вперед
      if ((endX == this.x) && (endY -this.y > 1) ){

          for (int t = this.y+1; t < endY; t++){
              if ((toLowerCase(pieces[this.x][this.y])==PieceType.getPiece(pieces[this.x][t]))
                      && PieceType.getPiece(pieces[this.x][t])!='\u0000'){

                  return true;
              }
          }
      }

      //ладья движется назад
      if ((endX == this.x) && (Math.abs(endY - this.y) > 1) ){

          for (int t = endY+1; t < this.y; t++){
              if ((toLowerCase(pieces[this.x][t])==PieceType.getPiece(pieces[this.x][t]))
                      && PieceType.getPiece(pieces[this.x][t])!='\u0000' ){

                  return true;
              }
          }
      }

        //ладья движется вправо
        if ((endY == this.y) && (endX - this.x > 1) ){

            for (int t = this.x+1; t < endX; t++){
                if ((toLowerCase(pieces[this.x][this.y])==PieceType.getPiece(toLowerCase(pieces[t][endY]))
                        && PieceType.getPiece(toLowerCase(pieces[t][endY]))!='\u0000')){

                    return true;
                }
            }
        }

        //лладья движется влево
        if ((endY == this.y) && (Math.abs(endX - this.x) > 1) ){

            for (int t = endX+1; t < this.x; t++){
                if ((toLowerCase(pieces[this.x][endY])==PieceType.getPiece(pieces[t][endY]))
                        && PieceType.getPiece(pieces[t][endY])!='\u0000'){

                    return true;
                }
            }
        }
        return false;
    }


    /**
    *перемещение ладьи в новые координаты
   */
    @Override
    public void makePath(int startX, int startY, int endX, int endY) {
            pieces[endX][endY] = pieces[x][y];
            pieces[this.x][this.y] = (int) '\u0000';
    }
}
