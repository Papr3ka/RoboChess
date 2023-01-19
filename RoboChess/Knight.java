/*
 * RoboChess - Chess using robots
 * Copyright (c) 2023 Benjamin Yao and Jack Hyun
 * 
 * This file is part of RoboChess
 * 
 * This file is a sub-class of BasePiece and contains all the
 * necessary operations specific to the 'Knight' piece
 */

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Point;

import becker.robots.*;

public class Knight extends BasePiece{

    public Knight(Board chessBoard, int x, int y, Side side_){
        
        super(chessBoard, x, y, Direction.NORTH);
        side = side_;
        if(side == Side.White){
            color = Color.WHITE;
        }else{
            color = Color.BLACK;
        }
        setIcon(new KnightIcon(color));
        setColor(color);
    }

    public Knight(Board chessBoard, BasePiece piece){
        
        super(chessBoard, piece.getPos().x, piece.getPos().y, Direction.NORTH);
        side = piece.getSide();
        if(side == Side.White){
            color = Color.WHITE;
        }else{
            color = Color.BLACK;
        }

        moves = piece.getMoves();
        
        setIcon(new KnightIcon(color));
        setColor(color);

        piece.eliminate(new Point(64, 24));
        piece.setTransparency(1.0d);
    }

    // Returns an array in which the Knight can move to considering the points on the current and opposite side
    public ArrayList<Point> getNextPositions(ArrayList<Point> currentSide, ArrayList<Point> oppositeSide){
        ArrayList<Point> nextPositions = new ArrayList<Point>();
        ArrayList<Point> nextPotentialPositions = getNextCovers(currentSide, oppositeSide);
        
        if(eliminated){
            return nextPositions;
        }

        for(Point p: nextPotentialPositions){
            if(!currentSide.contains(p)){
                nextPositions.add(p);
            }
        }

        return nextPositions;
    }

    // Returns all the points in which the knight is covering
    public ArrayList<Point> getNextCovers(ArrayList<Point> currentSide, ArrayList<Point> oppositeSide){
        ArrayList<Point> nextPotentialCovers = new ArrayList<Point>();
        ArrayList<Point> nextCovers = new ArrayList<Point>();

        if(eliminated){
            return nextCovers;
        }

        // Create all the points in which the knight is able to move to
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
        
        // Check if the points are within the limits of the chess board
        for(Point p: nextPotentialCovers){
            if(checkLimits(p)){
                nextCovers.add(p);
            }
        }

        return nextCovers;
    }

}
