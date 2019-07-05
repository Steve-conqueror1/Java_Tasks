package com.accenture.exceptions;

/**
* это исключение вызывается, когда игрок вводит пустые координаты
* */
public class EmptyMoveException extends Exception {

    public EmptyMoveException(String message, String statement) {
        super(message + ":" +statement);
    }
}

