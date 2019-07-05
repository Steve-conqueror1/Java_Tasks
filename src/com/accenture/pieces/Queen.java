package com.accenture.pieces;

import com.accenture.board.Board;
import com.accenture.game.PieceType;

import static com.accenture.board.Board.pieces;
import static java.lang.Character.toLowerCase;

public class Queen extends Piece {

    public Queen(int x, int y) {
        super(x, y);
    }

    /**
     * ферзь может двигаться вертикально, горизонтально и по диагонали
     * возвращает true, если ферзь движется вертикально, горизонтально или вертикально
     * */
    @Override
    public boolean isCorrectMove(int endX, int endY) {

        Bishop bishop = new Bishop(endX, endY);
        Pawn pawn = new Pawn(endX, endY);

        if ((bishop.isCorrectMove(endX, endY) || pawn.isCorrectMove(endX, endY)) && bishop.bishopJumped(endX, endY)==false) {
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
