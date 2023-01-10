import java.util.Vector;
import java.awt.Color;
import java.awt.Point;

import becker.robots.*;

public class Knight extends BasePiece{

    public Knight(Board chessBoard, int x, int y, Side side_){
        
        super(chessBoard, x, y, side_ == Side.White ? Direction.NORTH : Direction.SOUTH);
        side = side_;
        if(side == Side.White){
            color = Color.WHITE;
        }else{
            color = Color.BLACK;
        }

        setColor(color);
    }

    public Vector<Point> getNextPositions(Vector<Point> currentSide, Vector<Point> oppositeSide){
        Vector<Point> nextPositions = new Vector<Point>();
        Vector<Point> nextPotentialPositions = getNextCovers(currentSide, oppositeSide);
        
        for(Point p: nextPotentialPositions){
            if(!currentSide.contains(p)){
                nextPositions.add(p);
            }
        }

        return nextPositions;
    }

    // Returns all the points in which the knight is covering
    public Vector<Point> getNextCovers(Vector<Point> currentSide, Vector<Point> oppositeSide){
        Vector<Point> nextPotentialCovers = new Vector<Point>();
        Vector<Point> nextCovers = new Vector<Point>();

        Point testPoint;
        testPoint = getPos();
        testPoint.translate(1, 2);
        nextPotentialCovers.add(testPoint);
        testPoint = getPos();
        testPoint.translate(-1, 2);
        nextPotentialCovers.add(testPoint);
        testPoint = getPos();
        testPoint.translate(1, -2);
        nextPotentialCovers.add(testPoint);
        testPoint = getPos();
        testPoint.translate(-1, -2);
        nextPotentialCovers.add(testPoint);
        testPoint = getPos();
        //////
        testPoint.translate(2, 1);
        nextPotentialCovers.add(testPoint);
        testPoint = getPos();
        testPoint.translate(2, -1);
        nextPotentialCovers.add(testPoint);
        testPoint = getPos();
        testPoint.translate(-2, 1);
        nextPotentialCovers.add(testPoint);
        testPoint = getPos();
        testPoint.translate(-2, -1);
        nextPotentialCovers.add(testPoint);
        
        for(Point p: nextPotentialCovers){
            if(checkLimits(p)){
                nextCovers.add(p);
            }
        }

        return nextCovers;
    }

}
