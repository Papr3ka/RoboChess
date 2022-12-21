import java.awt.Color;

import becker.robots.*;

public class Bishop extends BasePiece{

    private Side side;

    public Bishop(Board chessBoard, int x, int y, Side side_){
        
        super(chessBoard, x, y, side_ == Side.White ? Direction.NORTH : Direction.SOUTH);
        side = side_;
        if(side == Side.White){
            setColor(Color.WHITE);
        }else{
            setColor(Color.BLACK);
        }
    }
}
