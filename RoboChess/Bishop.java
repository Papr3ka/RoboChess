import becker.robots.Direction;

public class Bishop extends BasePiece{
    public Bishop(Board chessBoard, int x, int y, Side side){
        super(chessBoard, x, y, side == Side.White ? Direction.NORTH : Direction.SOUTH);
    }
}
