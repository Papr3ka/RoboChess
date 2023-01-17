import java.util.ArrayList;
import java.awt.Color;
import java.awt.Point;

import becker.robots.*;

public class Pawn extends BasePiece{

    private boolean canEnPassant;


    public Pawn(Board chessBoard, int x, int y, Side side_){
        super(chessBoard, x, y, Direction.NORTH);
        side = side_;
        if(side == Side.White){
            color = Color.WHITE;
        }else{
            color = Color.BLACK;
        }
        setIcon(new PawnIcon(color));
        setColor(color);

        canEnPassant = true;
    }

    public Boolean canEnPassant(){
        return canEnPassant;
    }

    public void updateMoved(){
        canEnPassant = false;
    }

    @Override
    public void moveTo(Point p){
        if(Math.abs(p.y - getPos().y) == 2){
            canEnPassant = true;
        }else{
            canEnPassant = false;
        }
        
        moveToPoint(p);
        moves++;
    }


    // Positions in which the pawn can move to
    public ArrayList<Point> getNextPositions(ArrayList<Point> currentSide, ArrayList<Point> oppositeSide){
        ArrayList<Point> nextPositions = new ArrayList<Point>();
        
        if(eliminated){
            return nextPositions;
        }

        int yDirection = 1;
        if(side == Side.White){
            yDirection = -1;
        }

        Point pRight = getPos();
        Point pLeft = getPos();

        // pRight.translate(1, 0);
        // pLeft.translate(-1, 0);
        // // En Passant
        // if(oppositeSide.contains(pRight)){
        //     nextPositions.add(new Point(pRight.x, pRight.y + yDirection));
        // }

        // if(oppositeSide.contains(pLeft)){
        //     nextPositions.add(new Point(pLeft.x, pLeft.y + yDirection));
        // }

        pRight.translate(1, yDirection);
        pLeft.translate(-1, yDirection);
        if(oppositeSide.contains(pRight)){
            nextPositions.add(pRight);
        }

        if(oppositeSide.contains(pLeft)){
            nextPositions.add(pLeft);
        }

        Point pNext = getPos();
        Point pNext2 = getPos();
        pNext.translate(0, yDirection);
        pNext2.translate(0, yDirection*2);

        if(!(currentSide.contains(pNext) || oppositeSide.contains(pNext))){
            nextPositions.add(pNext);
            if(!(currentSide.contains(pNext2) || oppositeSide.contains(pNext2)) && getMoves() == 0){
                nextPositions.add(pNext2);
            }
        }

        for(int i = nextPositions.size() - 1; i >= 0; i--){
            if(!checkLimits(nextPositions.get(i))){
                nextPositions.remove(i);
            }
        }

        return nextPositions;
    }

    // Positions in which the pawn is covering
    public ArrayList<Point> getNextCovers(ArrayList<Point> currentSide, ArrayList<Point> oppositeSide){
        ArrayList<Point> nextPotentialCovers = new ArrayList<Point>();
        ArrayList<Point> nextCovers = new ArrayList<Point>();

        if(eliminated){
            return nextCovers;
        }

        int yDirection = 1;
        if(side == Side.White){
            yDirection = -1;
        }
        Point testPoint;
        testPoint = getPos();
        testPoint.translate(1, yDirection);
        nextPotentialCovers.add(testPoint);
        testPoint = getPos();
        testPoint.translate(-1, yDirection);
        nextPotentialCovers.add(testPoint);

        for(Point p: nextPotentialCovers){
            if(checkLimits(p)){
                nextCovers.add(p);
            }
        }

        return nextCovers;
    }
}
