/*
 * RoboChess - Chess using robots
 * Copyright (c) 2023 Benjamin Yao and Jack Hyun
 * 
 * This file is part of RoboChess
 * 
 * This file is a sub-class of BasePiece and contains all the
 * necessary operations specific to the 'Queen' piece
 */

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Point;

import becker.robots.*;

public class Queen extends BasePiece {

    public Queen(Board chessBoard, int x, int y, Side side_) {

        super(chessBoard, x, y, Direction.NORTH);
        side = side_;
        if (side == Side.White) {
            color = Color.WHITE;
        } else {
            color = Color.BLACK;
        }

        setIcon(new QueenIcon(color));
        setColor(color);
    }

    public Queen(Board chessBoard, BasePiece piece) {

        super(chessBoard, piece.getPos().x, piece.getPos().y, Direction.NORTH);
        side = piece.getSide();
        if (side == Side.White) {
            color = Color.WHITE;
        } else {
            color = Color.BLACK;
        }

        moves = piece.getMoves();

        setIcon(new QueenIcon(color));
        setColor(color);

        piece.eliminate(new Point(64, 24));
        piece.setTransparency(1.0d);
    }

    // Returns an array in which the Queen can move to considering the points on the current and opposite side
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

    // Returns an array in which the Queen is covering to considering the points on the current and opposite side
    public ArrayList<Point> getNextCovers(ArrayList<Point> currentSide, ArrayList<Point> oppositeSide) {
        ArrayList<Point> nextPotentialCovers = new ArrayList<Point>();
        ArrayList<Point> nextCovers = new ArrayList<Point>();

        if (eliminated) {
            return nextCovers;
        }

        // Generate all the horizontal, vertical and diagnol points and validate them
        Point testPoint = getPos();
        // Diaganols
        for (int i = 1; i < 8; i++) {
            testPoint = getPos();
            testPoint.translate(i, i);
            nextPotentialCovers.add(testPoint);
            if (currentSide.contains(testPoint) || oppositeSide.contains(testPoint)) {
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            testPoint = getPos();
            testPoint.translate(-i, i);
            nextPotentialCovers.add(testPoint);
            if (currentSide.contains(testPoint) || oppositeSide.contains(testPoint)) {
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            testPoint = getPos();
            testPoint.translate(i, -i);
            nextPotentialCovers.add(testPoint);
            if (currentSide.contains(testPoint) || oppositeSide.contains(testPoint)) {
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            testPoint = getPos();
            testPoint.translate(-i, -i);
            nextPotentialCovers.add(testPoint);
            if (currentSide.contains(testPoint) || oppositeSide.contains(testPoint)) {
                break;
            }
        }

        // Horizontal and Vertical
        for (int i = 1; i < 8; i++) {
            testPoint = getPos();
            testPoint.translate(0, i);
            nextPotentialCovers.add(testPoint);
            if (currentSide.contains(testPoint) || oppositeSide.contains(testPoint)) {
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            testPoint = getPos();
            testPoint.translate(i, 0);
            nextPotentialCovers.add(testPoint);
            if (currentSide.contains(testPoint) || oppositeSide.contains(testPoint)) {
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            testPoint = getPos();
            testPoint.translate(0, -i);
            nextPotentialCovers.add(testPoint);
            if (currentSide.contains(testPoint) || oppositeSide.contains(testPoint)) {
                break;
            }
        }
        for (int i = 1; i < 8; i++) {
            testPoint = getPos();
            testPoint.translate(-i, 0);
            nextPotentialCovers.add(testPoint);
            if (currentSide.contains(testPoint) || oppositeSide.contains(testPoint)) {
                break;
            }
        }

        for (Point p : nextPotentialCovers) {
            if (checkLimits(p)) {
                nextCovers.add(p);
            }
        }

        return nextCovers;
    }
}
