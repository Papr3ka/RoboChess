import java.util.ArrayList;
import java.awt.Color;
import java.awt.Point;

import becker.robots.*;

public class Bishop extends BasePiece{

    public Bishop(Board chessBoard, int x, int y, Side side_){
        
        super(chessBoard, x, y, Direction.NORTH);
        side = side_;
        if(side == Side.White){
            color = Color.WHITE;
        }else{
            color = Color.BLACK;
        }
        setIcon(new BishopIcon(color));
        setColor(color);
    }

    public Bishop(Board chessBoard, BasePiece piece){
        
        super(chessBoard, piece.getPos().x, piece.getPos().y, Direction.NORTH);
        side = piece.getSide();
        if(side == Side.White){
            color = Color.WHITE;
        }else{
            color = Color.BLACK;
        }

        moves = piece.getMoves();
        
        setIcon(new BishopIcon(color));
        setColor(color);

        piece.eliminate(new Point(64, 24));
        piece.setTransparency(1.0d);
    }

    public ArrayList<Point> getNextPositions(ArrayList<Point> currentSide, ArrayList<Point> oppositeSide){
        ArrayList<Point> nextPotentialPositions = getNextCovers(currentSide, oppositeSide);
        ArrayList<Point> nextPositions = new ArrayList<Point>();

        if(eliminated){
            return nextPositions;
        }

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

        if(eliminated){
            return nextCovers;
        }

        // 
        Point testPoint = getPos();
        for(int i = 1; i < 8; i++){
            testPoint = getPos();
            testPoint.translate(i, i);
            nextPotentialCovers.add(testPoint);
            if(currentSide.contains(testPoint) || oppositeSide.contains(testPoint)){
                break;
            }
        }
        for(int i = 1; i < 8; i++){
            testPoint = getPos();
            testPoint.translate(-i, i);
            nextPotentialCovers.add(testPoint);
            if(currentSide.contains(testPoint) || oppositeSide.contains(testPoint)){
                break;
            }
        }
        for(int i = 1; i < 8; i++){
            testPoint = getPos();
            testPoint.translate(i, -i);
            nextPotentialCovers.add(testPoint);
            if(currentSide.contains(testPoint) || oppositeSide.contains(testPoint)){
                break;
            }
        }
        for(int i = 1; i < 8; i++){
            testPoint = getPos();
            testPoint.translate(-i, -i);
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
