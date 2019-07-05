package com.accenture.exceptions;

/**
 * это исключение вызывается, когда игрок пытается играть, используя фигуры другого игрока
 * */
public class IllegalPieceException extends Exception {

    public IllegalPieceException(String message, String statement) {
        super(message+ ": "+ statement);
    }

}
