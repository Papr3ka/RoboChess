import java.util.Vector;

import java.awt.Color;
import java.awt.Point;

import becker.robots.*;

public class Pawn extends BasePiece{

    private Side side;
    private Color color;

    public Pawn(Board chessBoard, int x, int y, Side side_){
        
        super(chessBoard, x, y, side_ == Side.White ? Direction.NORTH : Direction.SOUTH);
        side = side_;
        if(side == Side.White){
            color = Color.WHITE;
        }else{
            color = Color.BLACK;
        }

        setColor(color);
    }

    // Returns the positions in which the pawn can move to
    // requires separate collision check
    public Vector<Point> getNextPositions(Point king){
        Vector<Point> checks = new Vector<Point>();
        Point testPoint;
        if(side == Side.White){
            testPoint = getPos();
            testPoint.translate(0, -1);
            if(checkLimits(testPoint, king)){
                checks.add(testPoint);
                testPoint = getPos();
            }
        }else{
            testPoint = getPos();
            testPoint.translate(0, 1);
            if(checkLimits(testPoint, king)){
                checks.add(testPoint);
                testPoint = getPos();
            }
        }

        return checks;
    }

    // Returns the positions in which the pawn is covering
    public Vector<Point> getCovers(){
        Vector<Point> checks = new Vector<Point>();
        Point testPoint;
        if(side == Side.White){
            testPoint = getPos();
            testPoint.translate(1, -1);
            if(checkLimits(testPoint)){
                checks.add(testPoint);
                testPoint = getPos();
            }
            testPoint.translate(-1, -1);
            if(checkLimits(testPoint)){
                checks.add(testPoint);
                testPoint = getPos();
            }
        }else{
            testPoint = getPos();
            testPoint.translate(1, 1);
            if(checkLimits(testPoint)){
                checks.add(testPoint);
                testPoint = getPos();
            }
            testPoint.translate(-1, 1);
            if(checkLimits(testPoint)){
                checks.add(testPoint);
                testPoint = getPos();
            }
        }

        return checks;
    }
}
