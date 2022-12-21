import java.util.Vector;

import becker.robots.Direction;

// Main class
class RoboChess {

    public static Vector<Object> initializeBoard(Board board){
        Vector<Object> pieces = new Vector<Object>();

        // Black Pieces
        pieces.add(new Rook(board, 0, 0, BasePiece.Side.Black));
        pieces.add(new Rook(board, 7, 0, BasePiece.Side.Black));

        pieces.add(new Knight(board, 1, 0, BasePiece.Side.Black));
        pieces.add(new Knight(board, 6, 0, BasePiece.Side.Black));

        pieces.add(new Bishop(board, 2, 0, BasePiece.Side.Black));
        pieces.add(new Bishop(board, 5, 0, BasePiece.Side.Black));

        pieces.add(new Queen(board, 3, 0, BasePiece.Side.Black));

        pieces.add(new King(board, 4, 0, BasePiece.Side.Black));

        // Black pawns
        for(int i = 0; i < 8; i++){
            pieces.add(new Pawn(board, i, 1, BasePiece.Side.Black));
        }

        // White

        pieces.add(new Rook(board, 0, 7, BasePiece.Side.White));
        pieces.add(new Rook(board, 7, 7, BasePiece.Side.White));

        pieces.add(new Knight(board, 1, 7, BasePiece.Side.White));
        pieces.add(new Knight(board, 6, 7, BasePiece.Side.White));

        pieces.add(new Bishop(board, 2, 7, BasePiece.Side.White));
        pieces.add(new Bishop(board, 5, 7, BasePiece.Side.White));

        pieces.add(new Queen(board, 3, 7, BasePiece.Side.White));

        pieces.add(new King(board, 4, 7, BasePiece.Side.White));

        // White pawns
        for(int i = 0; i < 8; i++){
            pieces.add(new Pawn(board, i, 6, BasePiece.Side.White));
        }

        return pieces;
    }

    public static void main(String[] args){
        Board chessBoard = new Board();
        Vector<Object> chessPieces = initializeBoard(chessBoard);
    }    
}
