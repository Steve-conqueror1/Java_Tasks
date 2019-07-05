package com.accenture.game;

import com.accenture.board.Board;

/**
 * Chess - это основной класс программы
 * */
public class Chess {

    /**
     * основной метод программы
     * */
    public static void main(String[] args) {

        System.out.println("\n\t\t\t\t--------------------ДОБРО ПОЖАЛОВАТЬ В НАШУ ИГРУ - ШАХМАТ-----------------");
        System.out.println("\t\t\t\t\t\t\t{x, y} x -> горизонтально y ->  вертикально\n");

        Board.fillBoardWithWhitePieces();
        Board.fillBoardWithBlackPieces();

        Board.showBoard();

        Board.play();
    }
}
