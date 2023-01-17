import java.awt.Color;
import java.awt.Point;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Random;

import becker.robots.*;

public class BasePiece extends RobotSE{

    public enum Side{
        White, Black
    }

    protected boolean eliminated;
    protected boolean isPositionFake;
    protected final Point fakePosition;
    protected int moves;
    protected Side side;
    protected Color color;

    private Direction originalDir;
    private Point tempPoint;
    private boolean useTempPoint;
    private int id;


    public BasePiece(Board chessBoard, int x, int y, Direction direction){
        super(chessBoard.getState()[0], y, x, direction);
        setSpeed(1024.0);
        originalDir = direction;
        moves = 0;
        eliminated = false;
        isPositionFake = false;
        fakePosition = new Point(64, 24);
        tempPoint = new Point(x, y);
        id = (new Random()).nextInt() + x + y*8;
    }

    // Sets up a null BasePiece (this constructor should not be used)
    public BasePiece(City c){
        super(c, 64, 24, Direction.NORTH);
        moves = -1;
        eliminated = true;
        isPositionFake = false;
        fakePosition = new Point(64, 24);
        tempPoint = fakePosition;
        setTransparency(1.0d);
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

        if(useTempPoint){
            return tempPoint;
        }
        // Returns a fake position
        // Used to determine other piece's positions without moving the current
        // Basicall used to hide the piece's position without removing it
        if(isPositionFake){
            return fakePosition;
        }

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

    // Turns the piece to face a specific direction regardless of current direction
    public void faceTo(Direction dir){
        int turns = resolveDirection(getDirection()) - resolveDirection(dir);
        if(turns > 0){
            turnLeft(turns);
        }else{
            turnRight(-turns);
        }
    }

    public void moveTo(Point p){
        moveToPoint(p);
        incrementMoves();
    }

    public void moveToPoint(Point p){
        if(useTempPoint){
            tempPoint = p;
            return;
        }

        setTransparency(1.0d);
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
        setTransparency(0.0d);    
    }

    public void eliminate(Point elimPoint){
        eliminated = true;
        setTransparency(0.15d);
        moveTo(elimPoint);
    }

    public void hide(){
        isPositionFake = true;
    }

    public void show(){
        isPositionFake = false;
    }

    public boolean isShown(){
        return isPositionFake;
    }

    public void tempModeOn(){
        useTempPoint = true;
    }

    public void tempModeOff(){
        useTempPoint = false;
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

    public int getId(){
        return id;
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