import becker.robots.Direction;

public class Queen extends BasePiece{
    public Queen(Board chessBoard, int x, int y, Side side){
         super(chessBoard, x, y, side == Side.White ? Direction.NORTH : Direction.SOUTH);
    }   
}
