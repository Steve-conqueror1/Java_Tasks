package com.accenture.board;

import com.accenture.exceptions.EmptyMoveException;
import com.accenture.exceptions.IllegalPieceException;
import com.accenture.game.PieceType;
import com.accenture.game.PlayerColor;
import com.accenture.pieces.*;
import com.accenture.repository.ChessGameRepository;

import java.util.InputMismatchException;
import java.util.Scanner;

import static com.accenture.pieces.King.kingIsAlive;
import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

public class Board {

    public static char[][] pieces = new char[8][8];
     public static String capturedPiece;

    public static void fillBoardWithWhitePieces(){
        pieces[0][0]=PieceType.ROOK.getPieceCharToRead();
        pieces[1][0]=PieceType.KNIGHT.getPieceCharToRead();
        pieces[2][0]=PieceType.BISHOP.getPieceCharToRead();
        pieces[3][0]=PieceType.QUEEN.getPieceCharToRead();
        pieces[4][0]=PieceType.KING.getPieceCharToRead();
        pieces[5][0]=PieceType.BISHOP.getPieceCharToRead();
        pieces[6][0]=PieceType.KNIGHT.getPieceCharToRead();
        pieces[7][0]=PieceType.ROOK.getPieceCharToRead();

        for (int i=0; i<8; i++) {
            for (int j=0; j<2; j++){
                if (pieces[i][j]=='\u0000')
                    pieces[i][j]=PieceType.PAWN.getPieceCharToRead();
            }
        }
    }

    public static void fillBoardWithBlackPieces(){
        pieces[0][7]= toUpperCase(PieceType.ROOK.getPieceCharToRead());
        pieces[1][7]=toUpperCase(PieceType.KNIGHT.getPieceCharToRead());
        pieces[2][7]=toUpperCase(PieceType.BISHOP.getPieceCharToRead());
        pieces[3][7]=toUpperCase(PieceType.QUEEN.getPieceCharToRead());
        pieces[4][7]=toUpperCase(PieceType.KING.getPieceCharToRead());
        pieces[5][7]=toUpperCase(PieceType.BISHOP.getPieceCharToRead());
        pieces[6][7]=toUpperCase(PieceType.KNIGHT.getPieceCharToRead());
        pieces[7][7]=toUpperCase(PieceType.ROOK.getPieceCharToRead());

        for (int i=0; i<8; i++) {
            for (int j=7; j>5; j--){
                if (pieces[i][j]=='\u0000')
                    pieces[i][j]=toUpperCase(PieceType.PAWN.getPieceCharToRead());
            }
        }
    }

    public static void showBoard(){
        System.out.println("\n\t\t\t\tИГРОК--A....................................................ИГРОК--B");
        for (int i=0; i<pieces.length; i++){
            for (int j = 0; j<pieces.length; j++){
                System.out.print(+pieces[i][j]!='\u0000'? "\t\t"
                        +pieces[i][j]+" {"+i+","+j+"}":"\t\t" + (int)(pieces[i][j])+" {"+i+","+j+"}");
            }
            System.out.println("\n");
        }
    }

    public static String currentPlayer;

    public static void play(){
        Scanner in;
        int x, x1= 0;
        int y, y1 =0;
        int count=0;


        while (true) {
            in = new Scanner(System.in);

            if (count%2==0){
                currentPlayer = PlayerColor.WHITE.getPlayer();
                System.out.println("\t\t\tИграет "+ PlayerColor.WHITE.getPlayer()+ "\n");
            } else {
                currentPlayer = PlayerColor.BLACK.getPlayer();
                System.out.println("\t\t\tИграет "+PlayerColor.BLACK.getPlayer()+ "\n");
            }


            try {
                System.out.print("\t\t\tвведите координату x фигуры, которую вы хотите переместить  x = ");
                x = in.nextInt();
                System.out.print("\t\t\tвведите координату y фигуры, которую вы хотите переместить  y = ");
                y = in.nextInt();
                System.out.print("\t\t\tвведите координату x, куда вы хотите переместить фигуру  x1 = ");
                x1 = in.nextInt();
                System.out.print("\t\t\tвведите координату y, куда вы хотите переместить фигуру  y1 = ");
                y1 = in.nextInt();

                if (pieces[x][y]=='\u0000'){
                    throw new EmptyMoveException("\n\t\t\t Пустой ход", " координата {" +x+","+y+"}"+" пустое\n");
                }

                if ((currentPiece(pieces[x][y], x, y).isCorrectMove(x1, y1)) && (correctPiecesToPlay(pieces[x][y], currentPlayer))) {
                    ChessGameRepository.dbAccess(count+1, currentPlayer, PieceType.getPieceByName(pieces[x][y]), "{"+x+" , "+y+"}", "{"+x1+" , "+y1+"}", capturedPiece);
                    currentPiece(pieces[x][y], x, y).makePath(x, y, x1, y1);
                    count=count+1;

                    if (!kingIsAlive(currentPlayer)){
                        System.out.println("\t\t\t\t--------------------поздравления !!!!!!!!!!  " +currentPlayer+ " выиграл игру------------------------");
                        return;
                    }else{
                        showBoard();
                        continue;
                    }
                }

                if ((currentPiece(pieces[x][y], x, y).isCorrectMove(x1, y1)) && (!correctPiecesToPlay(pieces[x][y], currentPlayer))) {
                  throw new IllegalPieceException("\n\t\t\t"+ currentPlayer, " не может играть с этой фигурой \n");
                }

                showBoard();

                if (!currentPiece(toLowerCase(pieces[x][y]), x, y).isCorrectMove(x1, y1)) {
                    System.out.println("\t\t\t\t неверный ход для "+PieceType.getPieceByName(pieces[x][y]) +"!!!-- играть снова\n");
                    continue;
                }

            } catch (ArrayIndexOutOfBoundsException e){
                System.out.println("\n\t\t\tx, y, x1, y1 не может быть больше 7 или меньше 0!!!!!!!\n");
            }catch (InputMismatchException e){
                System.out.println("\n\t\t\t-------Вы можете ввести только цифру!! Введите правильное значение\n ");
                continue;

            }catch (EmptyMoveException e){
                System.out.println(e.getLocalizedMessage());
                continue;
            }catch (IllegalPieceException e){
                System.out.println(e.getLocalizedMessage());
            }catch (Exception e){
                System.out.println("Ошбка!!! " +e.toString());
            }
        }
    }


    /**
     * эта функция возвращает true:
     * если игрокA играет белые фигуры (представленные маленькими буквами)
     * если игрокB играет черные фигуры (представлены большими буквами)
     * @param c - фигура, которую игрок пытается переместить
     * @param currentPlayer - текущий игрок
     * */
    private static boolean correctPiecesToPlay(char c, String currentPlayer) {
        for (PieceType myPieces : PieceType.values()){
            if (currentPlayer.equals(PlayerColor.WHITE.getPlayer()) && (c==myPieces.getPieceCharToRead())){
                return true;
            }

            if (currentPlayer.equals(PlayerColor.BLACK.getPlayer()) && (c == toUpperCase(myPieces.getPieceCharToRead()))){
                return true;
            }
        }
            return false;
        }

    /**
    * Определите тип фигуры, которую игрок перемещает
     * Возвращает фигура
     * @param piece - фигура, которую использует игрок
    * */
    public static Piece currentPiece(char piece, int startX, int startY){
      Piece myPiece = null;
      switch (toLowerCase(piece)){
          case 'l':
              myPiece = new Rook(startX, startY);
              break;
          case 's':
              myPiece = new Pawn(startX, startY);
              break;
          case 'c':
              myPiece = new Bishop(startX, startY);
              break;
          case 'k':
              myPiece = new Knight(startX, startY);
             break;
          case 'f':
              myPiece = new Queen(startX, startY);
             break;
          case 'z':
              myPiece = new King(startX, startY);
              break;
      }
        return myPiece == null ? new Queen(startX, startY) : myPiece;
    }
}

