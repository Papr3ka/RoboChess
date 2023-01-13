import becker.robots.icons.*;
import java.awt.geom.GeneralPath;
import java.awt.Color;

public class QueenIcon extends ShapeIcon
  {
     public QueenIcon(Color color)
     {  this(color, 0.8);
     }

     public QueenIcon(Color color, double relativeSize)
     {  super(QueenIcon.shape, color, relativeSize);
     }

     private static GeneralPath shape;

     static
     {  shape = new GeneralPath();
        
        shape.moveTo(0.5F, 0.0F);
        
        
        shape.lineTo(0.2F, 1.0F);
        shape.lineTo(0.72F, 1.0F);
        
        
        shape.closePath();

        
        shape.closePath();

     }
  }

