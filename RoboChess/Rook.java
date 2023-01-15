import java.util.ArrayList;
import java.awt.Color;
import java.awt.Point;

import becker.robots.*;

public class Rook extends BasePiece{

    public Rook(Board chessBoard, int x, int y, Side side_){
        
        super(chessBoard, x, y, Direction.NORTH);
        side = side_;
        if(side == Side.White){
            color = Color.WHITE;
        }else{
            color = Color.BLACK;
        }
        setIcon(new RookIcon(color));
        setColor(color);
    }

    public ArrayList<Point> getNextPositions(ArrayList<Point> currentSide, ArrayList<Point> oppositeSide){
        ArrayList<Point> nextPotentialPositions = getNextCovers(currentSide, oppositeSide);
        ArrayList<Point> nextPositions = new ArrayList<Point>();

        // 

        for(Point p: nextPotentialPositions){
            if(!currentSide.contains(p)){
                nextPositions.add(p);
            }
        }

        return nextPositions;
    }

    public ArrayList<Point> getNextCovers(ArrayList<Point> currentSide, ArrayList<Point> oppositeSide){
        ArrayList<Point> nextPotentialCovers = new ArrayList<Point>();
        ArrayList<Point> nextCovers = new ArrayList<Point>();

        // 
        Point testPoint = getPos();
        for(int i = 1; i < 8; i++){
            testPoint = getPos();
            testPoint.translate(0, i);
            nextPotentialCovers.add(testPoint);
            if(currentSide.contains(testPoint) || oppositeSide.contains(testPoint)){
                break;
            }
        }
        for(int i = 1; i < 8; i++){
            testPoint = getPos();
            testPoint.translate(i, 0);
            nextPotentialCovers.add(testPoint);
            if(currentSide.contains(testPoint) || oppositeSide.contains(testPoint)){
                break;
            }
        }
        for(int i = 1; i < 8; i++){
            testPoint = getPos();
            testPoint.translate(0, -i);
            nextPotentialCovers.add(testPoint);
            if(currentSide.contains(testPoint) || oppositeSide.contains(testPoint)){
                break;
            }
        }
        for(int i = 1; i < 8; i++){
            testPoint = getPos();
            testPoint.translate(-i, 0);
            nextPotentialCovers.add(testPoint);
            if(currentSide.contains(testPoint) || oppositeSide.contains(testPoint)){
                break;
            }
        }

        for(Point p: nextPotentialCovers){
            if(checkLimits(p)){
                nextCovers.add(p);
            }
        }

        return nextCovers;
    }
}
