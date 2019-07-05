package com.accenture.pieces;

import static com.accenture.board.Board.pieces;

public class King extends Piece {

    public King(int x, int y) {
        super(x, y);
    }

    /**
     * Король может двигаться на один шаг вертикально, горизонтально или по диагонали
     * */
    @Override
    public boolean isCorrectMove(int endX, int endY) {
        if((Math.abs(endY-this.y)==1) || Math.abs(endX-this.x)==1){
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
