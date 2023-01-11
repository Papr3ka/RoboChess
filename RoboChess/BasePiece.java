import java.awt.Color;
import java.awt.Point;
import java.lang.Math;
import java.util.ArrayList;

import becker.robots.Direction;
import becker.robots.RobotSE;

public class BasePiece extends RobotSE{

    public enum Side{
        White, Black
    }

    protected boolean eliminated;
    protected int moves;
    protected Side side;
    protected Color color;

    private Direction originalDir;

    public BasePiece(Board chessBoard, int x, int y, Direction direction){
        super(chessBoard.getState()[0], y, x, direction);
        setSpeed(1024.0);
        originalDir = direction;
        moves = 0;
        eliminated = false;
    }

    // Sets up a null BasePiece (this constructor should not be used)
    public BasePiece(){
        super(null, 0, 0, null);
        moves = -1;
        eliminated = true;
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

    // Assigns a value to direction 0 1 2 3 for N E S W
    public int resolveDirection(Direction dir){
        if(dir == Direction.NORTH){
            return 0;
        }else if(dir == Direction.EAST){
            return 1;
        }else if(dir == Direction.SOUTH){
            return 2;
        }else{
            return 3;
        }
    }

    public void faceTo(Direction dir){
        int turns = resolveDirection(getDirection()) - resolveDirection(dir);
        if(turns > 0){
            turnLeft(turns);
        }else{
            turnRight(-turns);
        }
    }

    public void moveTo(Point p){
        if(p.x > getPos().x){
            faceTo(Direction.EAST);
        }else if(p.x < getPos().x){
            faceTo(Direction.WEST);
        }
        // p.x == getPos().x
        move(Math.abs(getPos().x - p.x));

        if(p.y > getPos().y){
            faceTo(Direction.SOUTH);
        }else if(p.y < getPos().y){
            faceTo(Direction.NORTH);
        }
        move(Math.abs(getPos().y - p.y));
        faceTo(originalDir);

        incrementMoves();
        
    }

    public void eliminate(){
        eliminated = true;
        moveTo(new Point(64, 64));
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