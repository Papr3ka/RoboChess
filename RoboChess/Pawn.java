import becker.robots.Direction;

public class Pawn extends BasePiece{
    public Pawn(Board chessBoard, int x, int y, Side side){
        super(chessBoard, x, y, side == Side.White ? Direction.NORTH : Direction.SOUTH);
    }
}
