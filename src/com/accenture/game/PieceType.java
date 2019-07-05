package com.accenture.game;

import static java.lang.Character.toUpperCase;

/**
 * Это перечисление объявляет шесть фигуры как константы
 * ROOK - ладья
 * PAWN - пешка
 * BISHOP - слон
 * KNIGHT - конь
 * QUEEN - ферзь
 * KING - король
 * */
public enum PieceType {

    ROOK('l'),
    PAWN('s'),
    BISHOP('c'),
    KNIGHT('k'),
    QUEEN('f'),
    KING('z');

   private PieceType(char pieceCharToRead) {
        this.pieceCharToRead = pieceCharToRead;
    }

    private char pieceCharToRead;

    public char getPieceCharToRead() {
        return pieceCharToRead;
    }

    /**
     * этот метод возвращает имя фигура
     * @param piece - символ, который представляет фигура
     * */
    public static String getPieceByName(char piece){
        for (PieceType myPiece : PieceType.values()) {
            if ((myPiece.getPieceCharToRead() == piece)||(toUpperCase(myPiece.getPieceCharToRead()) == piece)){
                return myPiece.name();
            }
        }
        return null;
    }

    /**
     * этот метод возвращает символ, если он равен символу в аргументе
     * @param piece - символ в аргументе
     * */
    public static char  getPiece(char piece){
        for (PieceType myPiece : PieceType.values()){
            if (piece == myPiece.getPieceCharToRead()) {
                return myPiece.getPieceCharToRead();
            }
        }
        return '\u0000';
    }
}
