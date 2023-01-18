/*
 * RoboChess - Chess using robots
 * Copyright (c) 2023 Benjamin Yao and Jack Hyun
 * 
 * This file is part of RoboChess
 * 
 * This file is a sub-class of 'ShapeIcon' and contains the
 * icon for the 'Knight' piece
 * 
 */

import becker.robots.icons.*;
import java.awt.geom.GeneralPath;
import java.awt.Color;

public class KnightIcon extends ShapeIcon {
   public KnightIcon(Color color) {
      this(color, 0.8);
   }

   public KnightIcon(Color color, double relativeSize) {
      super(KnightIcon.shape, color, relativeSize);
   }

   private static GeneralPath shape;

   static {
      shape = new GeneralPath();

      shape.moveTo(0.75F, 0.3F);

      shape.lineTo(0.65F, 0.15F);
      shape.lineTo(0.45F, 0.1F);
      shape.lineTo(0.45F, 0.05F);
      shape.lineTo(0.38F, 0.05F);
      shape.lineTo(0.35F, 0.17F);
      shape.lineTo(0.15F, 0.4F);

      shape.lineTo(0.2F, 0.5F);
      shape.lineTo(0.2F, 0.55F);
      shape.lineTo(0.3F, 0.55F);
      shape.lineTo(0.33F, 0.51F);
      shape.lineTo(0.4F, 0.51F);
      shape.lineTo(0.5F, 0.45F);
      shape.lineTo(0.5F, 0.45F);

      shape.lineTo(0.5F, 0.5F);

      shape.lineTo(0.25F, 0.9F);
      shape.lineTo(0.2F, 0.9F);
      shape.lineTo(0.15F, 1.0F);
      shape.lineTo(0.85F, 1.0F);
      shape.lineTo(0.8F, 0.9F);

      shape.lineTo(0.75F, 0.9F);
      shape.lineTo(0.8F, 0.5F);
      shape.lineTo(0.75F, 0.3F);

      shape.closePath();

   }
}