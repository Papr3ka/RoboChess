import java.util.Vector;
import java.awt.Color;
import java.awt.Point;

import becker.robots.*;

public class Pawn extends BasePiece{

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

    public Vector<Point> getNextPositions(Vector<Point> currentSide, Vector<Point> oppositeSide){
        Vector<Point> nextPositions = new Vector<Point>();

        // 

        return nextPositions;
    }

    public Vector<Point> getNextCovers(Vector<Point> currentSide, Vector<Point> oppositeSide){
        Vector<Point> nextCovers = new Vector<Point>();

        // 

        return nextCovers;
    }
}
