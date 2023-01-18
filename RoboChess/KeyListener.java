/*
 * RoboChess - Chess using robots
 * Copyright (c) 2023 Benjamin Yao and Jack Hyun
 * 
 * This file is part of RoboChess
 * 
 * This file is a sub-class of 'RobotRC' and contains the
 * components necessary for detecting keyboard input
 */

import becker.robots.*;
import becker.robots.icons.*;
import java.awt.geom.Rectangle2D;
import java.awt.Color;

public class KeyListener extends RobotRC {

    private char keyPress;
    private boolean stateChange;

    public KeyListener(Board chessBoard) {
        // The only functionality of this is to detect keyPresses
        // None of the robot functionality other than this is intended
        super(chessBoard.getState()[0],
                0,
                0,
                Direction.NORTH);

        // Make entity hidden
        setIcon(new ShapeIcon(new Rectangle2D.Double(0.0, 0.0, 0.0, 0.0), new Color(255, 255, 255, 0)));
        stateChange = false;
    }

    // new implementation of the keyTyped method
    @Override
    protected void keyTyped(char key) {
        stateChange = true;
        keyPress = key;
    }

    public boolean isNewKeyPressed() {
        return stateChange;
    }

    public char getKey() {
        if (!stateChange) {
            return 0;
        }

        stateChange = false;
        return keyPress;
    }

    // Unused but required
    public Intersection getIntersection() {
        return new Intersection(getCity(), 0, 0);
    }

}
