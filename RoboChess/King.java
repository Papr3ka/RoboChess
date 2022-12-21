import becker.robots.Direction;

public class King extends BasePiece {
    public King(Board chessBoard, int x, int y, Side side){
        super(chessBoard, x, y, side == Side.White ? Direction.NORTH : Direction.SOUTH);
    }
}
