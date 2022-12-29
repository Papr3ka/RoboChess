import java.util.Vector;

import java.awt.Color;
import java.awt.Point;

import becker.robots.*;

public class Knight extends BasePiece{

    private Side side;

    public Knight(Board chessBoard, int x, int y, Side side_){
        
        super(chessBoard, x, y, side_ == Side.White ? Direction.NORTH : Direction.SOUTH);
        side = side_;
        if(side == Side.White){
            setColor(Color.WHITE);
        }else{
            setColor(Color.BLACK);
        }
    }

    // Returns the positions in which the knight can move to
    // requires separate entity check
    public Vector<Point> getNextPositions(Point king){
        Vector<Point> checks = new Vector<Point>();
        Point testPoint;
        testPoint = getPos();
        testPoint.translate(1, 2);
        if(checkLimits(testPoint, king)){
            checks.add(testPoint);
            testPoint = getPos();
        }
        testPoint.translate(-1, 2);
        if(checkLimits(testPoint, king)){
            checks.add(testPoint);
            testPoint = getPos();
        }
        testPoint.translate(1, -2);
        if(checkLimits(testPoint, king)){
            checks.add(testPoint);
            testPoint = getPos();
        }
        testPoint.translate(-1, -2);
        if(checkLimits(testPoint, king)){
            checks.add(testPoint);
            testPoint = getPos();
        }
        //////
        testPoint.translate(2, 1);
        if(checkLimits(testPoint, king)){
            checks.add(testPoint);
            testPoint = getPos();
        }
        testPoint.translate(2, -1);
        if(checkLimits(testPoint, king)){
            checks.add(testPoint);
            testPoint = getPos();
        }
        testPoint.translate(-2, 1);
        if(checkLimits(testPoint, king)){
            checks.add(testPoint);
            testPoint = getPos();
        }
        testPoint.translate(-2, -1);
        if(checkLimits(testPoint, king)){
            checks.add(testPoint);
            testPoint = getPos();
        }

        return checks;
    }

    // Returns points in which the knight is covering
    public Vector<Point> getCovers(){
        Vector<Point> checks = new Vector<Point>();
        Point testPoint;
        testPoint = getPos();
        testPoint.translate(1, 2);
        if(checkLimits(testPoint)){
            checks.add(testPoint);
            testPoint = getPos();
        }
        testPoint.translate(-1, 2);
        if(checkLimits(testPoint)){
            checks.add(testPoint);
            testPoint = getPos();
        }
        testPoint.translate(1, -2);
        if(checkLimits(testPoint)){
            checks.add(testPoint);
            testPoint = getPos();
        }
        testPoint.translate(-1, -2);
        if(checkLimits(testPoint)){
            checks.add(testPoint);
            testPoint = getPos();
        }
        //////
        testPoint.translate(2, 1);
        if(checkLimits(testPoint)){
            checks.add(testPoint);
            testPoint = getPos();
        }
        testPoint.translate(2, -1);
        if(checkLimits(testPoint)){
            checks.add(testPoint);
            testPoint = getPos();
        }
        testPoint.translate(-2, 1);
        if(checkLimits(testPoint)){
            checks.add(testPoint);
            testPoint = getPos();
        }
        testPoint.translate(-2, -1);
        if(checkLimits(testPoint)){
            checks.add(testPoint);
            testPoint = getPos();
        }

        return checks;
    }
}
