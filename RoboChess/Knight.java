import becker.robots.Direction;

public class Knight extends BasePiece{
    public Knight(Board chessBoard, int x, int y, Side side){
        super(chessBoard, x, y, side == Side.White ? Direction.NORTH : Direction.SOUTH);
    }
}
