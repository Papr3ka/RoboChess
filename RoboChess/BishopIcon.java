/*
 * RoboChess - Chess using robots
 * Copyright (c) Benjamin Yao and Jack Hyun
 * 
 * This file is part of RoboChess
 * 
 * This file is a sub-class of 'ShapeIcon' and contains the
 * icon for the 'Bishop' piece
 * 
 */

import becker.robots.icons.*;
import java.awt.geom.GeneralPath;
import java.awt.Color;

public class BishopIcon extends ShapeIcon {
   public BishopIcon(Color color) {
      this(color, 0.8);
   }

   public BishopIcon(Color color, double relativeSize) {
      super(BishopIcon.shape, color, relativeSize);
   }

   private static GeneralPath shape;

   static {
      shape = new GeneralPath();

      shape.moveTo(0.52F, 0.15F);

      shape.lineTo(0.55F, 0.05F);
      shape.lineTo(0.45F, 0.05F);
      shape.lineTo(0.48F, 0.15F);
      shape.lineTo(0.3F, 0.3F);

      shape.lineTo(0.2F, 0.6F);
      shape.lineTo(0.25F, 0.73F);
      shape.lineTo(0.35F, 0.73F);
      shape.lineTo(0.25F, 0.9F);

      shape.lineTo(0.2F, 0.9F);
      shape.lineTo(0.15F, 1.0F);
      shape.lineTo(0.85F, 1.0F);
      shape.lineTo(0.8F, 0.9F);

      shape.lineTo(0.75F, 0.9F);
      shape.lineTo(0.65F, 0.73F);
      shape.lineTo(0.75F, 0.73F);
      shape.lineTo(0.8F, 0.6F);

      shape.lineTo(0.72F, 0.35F);
      shape.lineTo(0.58F, 0.48F);
      shape.lineTo(0.52F, 0.42F);
      shape.lineTo(0.675F, 0.28F);
      shape.closePath();
   }
}