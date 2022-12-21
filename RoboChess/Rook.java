import becker.robots.Direction;

public class Rook extends BasePiece{
    public Rook(Board chessBoard, int x, int y, Side side){
        super(chessBoard, x, y, side == Side.White ? Direction.NORTH : Direction.SOUTH);
    }
}
