/*
 * RoboChess - Chess using robots
 * Copyright (c) 2023 Benjamin Yao and Jack Hyun
 * 
 * This file is part of RoboChess
 * 
 * This file is a sub-class of 'ShapeIcon' and contains the
 * icon for the 'Pawn' piece
 * 
 */

import becker.robots.icons.*;
import java.awt.geom.GeneralPath;
import java.awt.Color;

public class PawnIcon extends ShapeIcon {
   public PawnIcon(Color color) {
      this(color, 0.8);
   }

   public PawnIcon(Color color, double relativeSize) {
      super(PawnIcon.shape, color, relativeSize);
   }

   private static GeneralPath shape;

   static {
      shape = new GeneralPath();

      shape.moveTo(0.5F, 0.05F);

      shape.lineTo(0.4F, 0.1F);
      shape.lineTo(0.45F, 0.15F);
      shape.lineTo(0.4F, 0.28F);
      shape.lineTo(0.3F, 0.33F);

      shape.lineTo(0.4F, 0.43F);
      shape.lineTo(0.34F, 0.8F);
      shape.lineTo(0.25F, 0.9F);
      shape.lineTo(0.3F, 1.0F);

      shape.lineTo(0.7F, 1.0F);
      shape.lineTo(0.75F, 0.9F);
      shape.lineTo(0.66F, 0.8F);
      shape.lineTo(0.6F, 0.43F);

      shape.lineTo(0.7F, 0.33F);

      shape.lineTo(0.6F, 0.28F);
      shape.lineTo(0.55F, 0.15F);
      shape.lineTo(0.6F, 0.1F);
      shape.lineTo(0.5F, 0.05F);
      shape.closePath();

      shape.closePath();

   }
}
