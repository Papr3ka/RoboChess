import java.util.ArrayList;
import java.awt.Color;
import java.awt.Point;

import becker.robots.Direction;
import becker.robots.Robot;

public class BasePiece extends Robot{

    public enum Side{
        White, Black
    }

    protected int moves;
    protected Side side;
    protected Color color;

    public BasePiece(Board chessBoard, int x, int y, Direction direction){
        super(chessBoard.getState()[0], y, x, direction);
        moves = 0;
    }

    // Sets up a null BasePiece (this constructor should not be used)
    public BasePiece(){
        super(null, 0, 0, null);
        moves = -1;
    }

    public int getMoves(){
        return moves;
    }

    public void incrementMoves(){
        if(moves >= 0){
            moves++;
        }
    }

    public Side getSide(){
        return side;
    }

    // Returns a 2 element array in the form [x, y]
    public Point getPos(){
        return new Point(getAvenue(), getStreet());
    }

    public ArrayList<Point> getNextPositions(ArrayList<Point> currentSide, ArrayList<Point> oppositeSide){
        ArrayList<Point> current = new ArrayList<Point>();
        current.add(getPos());
        return current;
    }

    public ArrayList<Point> getNextCovers(ArrayList<Point> currentSide, ArrayList<Point> oppositeSide){
        ArrayList<Point> current = new ArrayList<Point>();
        current.add(getPos());
        return current;
    }


    // Checks if a position is within bounds of the chess board
    public boolean checkLimits(Point p){
        if((p.x >= 0) && (p.x < 8) && (p.y >= 0) && (p.y < 8)){
            return true;
        }
        return false;
    }

    // Overload for checking with king
    public boolean checkLimits(Point p, Point king){
        if((p.x >= 0) && (p.x < 8) && (p.y >= 0) && (p.y < 8) && !p.equals(king)){
            return true;
        }
        return false;
    }

}