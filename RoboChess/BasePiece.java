import java.awt.Point;

import becker.robots.Direction;
import becker.robots.Robot;

public class BasePiece extends Robot{

    public enum Side{
        White, Black
    }

    public BasePiece(Board chessBoard, int x, int y, Direction direction){
        super(chessBoard.getState()[0], y, x, direction);
    }

    // Returns a 2 element array in the form [x, y]
    public Point getPos(){
        return new Point(getAvenue(), getStreet());
    }

    // Checks if a position is within bounds of the chess board
    public boolean checkLimits(Point p){
        if((p.x > 0) && (p.x < 8) && (p.y > 0) && (p.y < 8)){
            return true;
        }
        return false;
    }

    // Overload for checking with king
    public boolean checkLimits(Point p, Point king){
        if((p.x > 0) && (p.x < 8) && (p.y > 0) && (p.y < 8) && !p.equals(king)){
            return true;
        }
        return false;
    }

}