import becker.robots.icons.*;
import java.awt.geom.GeneralPath;
import java.awt.Color;

public class SelectorIcon extends ShapeIcon
  {
     public SelectorIcon(Color color)
     {  this(color, 0.8);
     }

     public SelectorIcon(Color color, double relativeSize)
     {  super(SelectorIcon.shape, color, relativeSize);
     }

     private static GeneralPath shape;

     static
     {  shape = new GeneralPath();
        
        shape.moveTo(0.87F, 0.07F);
        
        
        shape.lineTo(0.7F, 0.07F);
        shape.lineTo(0.7F, 0.12F);
        shape.lineTo(0.6F, 0.12F);
        shape.lineTo(0.6F, 0.07F);
        
        shape.lineTo(0.4F, 0.07F);
        shape.lineTo(0.4F, 0.12F);
        shape.lineTo(0.3F, 0.12F);
        shape.lineTo(0.3F, 0.07F);
        
        shape.lineTo(0.13F, 0.07F);
        shape.lineTo(0.13F, 0.25F);
        shape.lineTo(0.22F, 0.3F);
        shape.lineTo(0.17F, 0.8F);
        
        shape.lineTo(0.1F, 0.8F);
        shape.lineTo(0.1F, 0.9F);
        shape.lineTo(0.05F, 1.0F);
        shape.lineTo(0.95F, 1.0F);
        
       
        shape.lineTo(0.9F, 0.9F);
        shape.lineTo(0.9F, 0.8F);
        shape.lineTo(0.83F, 0.8F);
        
        shape.lineTo(0.78F, 0.3F);
        shape.lineTo(0.87F, 0.25F);
        shape.lineTo(0.87F, 0.07F);
        shape.closePath();

        
        shape.closePath();

     }
  }
