package com.accenture.pieces;


public abstract class Piece {

    public int x, y;

    public Piece(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
      * определите, правильно ли перемещение в соответствии с типом фигуры
      *@param endX - конечное местоположение X
      * @param endY - конечное местоположение Y
      * возвращает true, если ход правильный, false в ход не правильный
   * */
    public abstract boolean isCorrectMove(int endX, int endY);

    /**
    *определить начальной и конечной координат
    * @param startX - начальное местоположение X
    * @param startY - начальное местоположение Y
    * @param endX - конечное местоположение X
    * @param endY - конечное местоположение Y
    * */
    public abstract void makePath(int startX, int startY, int endX, int endY );

}
