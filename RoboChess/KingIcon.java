/*
 * RoboChess - Chess using robots
 * Copyright (c) 2023 Benjamin Yao and Jack Hyun
 * 
 * This file is part of RoboChess
 * 
 * This file is a sub-class of 'ShapeIcon' and contains the
 * icon for the 'King' piece
 */

import becker.robots.icons.*;
import java.awt.geom.GeneralPath;
import java.awt.Color;

public class KingIcon extends ShapeIcon {
   public KingIcon(Color color) {
      this(color, 0.8);
   }

   public KingIcon(Color color, double relativeSize) {
      super(KingIcon.shape, color, relativeSize);
   }

   private static GeneralPath shape;

   static {
      shape = new GeneralPath();

      shape.moveTo(0.5F, 0.3F);

      shape.lineTo(0.6F, 0.2F);
      shape.lineTo(0.5F, 0.1F);
      shape.lineTo(0.4F, 0.2F);
      shape.lineTo(0.5F, 0.3F);

      shape.lineTo(0.3F, 0.25F);
      shape.lineTo(0.1F, 0.3F);
      shape.lineTo(0.0F, 0.5F);
      shape.lineTo(0.1F, 0.8F);

      shape.lineTo(0.2F, 0.9F);
      shape.lineTo(0.1F, 0.9F);
      shape.lineTo(0.0F, 1.0F);
      shape.lineTo(1.0F, 1.0F);

      shape.lineTo(0.9F, 0.9F);
      shape.lineTo(0.8F, 0.9F);
      shape.lineTo(0.9F, 0.8F);
      shape.lineTo(1.0F, 0.5F);

      shape.lineTo(0.9F, 0.3F);
      shape.lineTo(0.7F, 0.25F);
      shape.lineTo(0.5F, 0.3F);

      shape.closePath();

      shape.closePath();

   }
}
