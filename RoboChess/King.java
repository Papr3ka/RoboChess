/*
 * RoboChess - Chess using robots
 * Copyright (c) 2023 Benjamin Yao and Jack Hyun
 * 
 * This file is part of RoboChess
 * 
 * This file is a sub-class of BasePiece and contains all the
 * necessary operations specific to the 'King' piece
 */

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Point;

import becker.robots.*;

public class King extends BasePiece {

    public King(Board chessBoard, int x, int y, Side side_) {

        super(chessBoard, x, y, Direction.NORTH);
        side = side_;
        if (side == Side.White) {
            color = Color.WHITE;
        } else {
            color = Color.BLACK;
        }
        setIcon(new KingIcon(color));
        setColor(color);
    }

    // Returns an array in which the King can move to considering the points on the current and opposite side
    public ArrayList<Point> getNextPositions(ArrayList<Point> currentSide, ArrayList<Point> oppositeSide) {
        ArrayList<Point> nextPotentialPositions = getNextCovers(currentSide, oppositeSide);
        ArrayList<Point> nextPositions = new ArrayList<Point>();

        if (eliminated) {
            return nextPositions;
        }
        //

        for (Point p : nextPotentialPositions) {
            if (!currentSide.contains(p)) {
                nextPositions.add(p);
            }
        }

        return nextPositions;
    }

    // Returns an array in which the King is covering to considering the points on the current and opposite side
    // Used to determine if the king is in check or not
    public ArrayList<Point> getNextCovers(ArrayList<Point> currentSide, ArrayList<Point> oppositeSide) {
        ArrayList<Point> nextPotentialCovers = new ArrayList<Point>();
        ArrayList<Point> nextCovers = new ArrayList<Point>();

        if (eliminated) {
            return nextCovers;
        }

        // Generate and test points
        // points are tested to see if they are specifically occupied or not
        Point testPoint;
        for (int i = -1; i <= 1; i++) {
            testPoint = getPos();
            testPoint.translate(i, 1);
            nextPotentialCovers.add(testPoint);
            testPoint = getPos();
            testPoint.translate(i, -1);
            nextPotentialCovers.add(testPoint);

        }
        testPoint = getPos();
        testPoint.translate(-1, 0);
        nextPotentialCovers.add(testPoint);
        testPoint = getPos();
        testPoint.translate(1, 0);
        nextPotentialCovers.add(testPoint);

        for (Point p : nextPotentialCovers) {
            if (checkLimits(p)) {
                nextCovers.add(p);
            }
        }

        return nextCovers;
    }

}
