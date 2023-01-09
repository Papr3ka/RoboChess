import java.awt.Color;

import becker.robots.*;

public class King extends BasePiece{

    private Side side;
    private Color color;

    public King(Board chessBoard, int x, int y, Side side_){
        
        super(chessBoard, x, y, side_ == Side.White ? Direction.NORTH : Direction.SOUTH);
        side = side_;
        if(side == Side.White){
            color = Color.WHITE;
        }else{
            color = Color.BLACK;
        }

        setColor(color);
    }
}
