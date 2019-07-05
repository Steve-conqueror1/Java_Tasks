package com.accenture.game;

/**
 * Это перечисление объявляет два цвета как константы
 * WHITE - для игрокА
 * BLACK - для игрокВ
 * */
public enum  PlayerColor {
    WHITE("игрокA"),
    BLACK("игрокB");

    private String player;
    private  PlayerColor(String myPlayer) {
        player =myPlayer;
    }

    /**
     * этот метод возвращает имя текущего игрока
     * */
    public  String getPlayer(){
        return player;
    }
}
