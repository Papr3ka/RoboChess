import becker.robots.Direction;
import becker.robots.Robot;

public class BasePiece extends Robot{

    public BasePiece(Board chessBoard, int x, int y, Direction direction){
        super(chessBoard.getState()[0], y, x, direction);
    }

    // Returns a 2 element array in the form [x, y]
    public int[] getPos(){
        return new int[]{getAvenue(), getStreet()};
    }

}