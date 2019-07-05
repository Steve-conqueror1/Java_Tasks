package com.accenture.repository;

import java.sql.*;

/**
 * этот класс подключается к базе данных *
 * */
public class ChessGameRepository {

    static final String dbUrl = "jdbc:oracle:thin:@localhost:1521/xe";
    static final String username = "chess_user";
    static final String password = "chess_password";

    /**
     * метод dbAccess() - вставляет запись в базу данных
     * @param playerName - имя текущего игрока
     * @param pieceType - тип фигура
     * @param moveFrom - координата, из которой движется игрок
     * @param moveTo - координата, к которой движется игрок
     * */
    public static void dbAccess(int playeId, String playerName, String pieceType, String moveFrom, String moveTo, String capturedPiece )
            throws SQLException {

        Connection conn = DriverManager.getConnection(dbUrl, username, password);
        String sql = "insert into chess values (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, playeId);
        statement.setString(2, playerName);
        statement.setString(3, pieceType);
        statement.setString(4, moveFrom);
        statement.setString(5, moveTo);
        statement.setString(6, capturedPiece);

        statement.executeUpdate();

        statement.close();
        conn.close();
    }
}
