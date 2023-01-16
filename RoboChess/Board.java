import becker.robots.*;
import becker.robots.icons.*;
import java.awt.geom.Rectangle2D;
import java.awt.Color;

public class Board extends City{
//tt
    // White: Color(234, 233, 210, 255)
    // Black (actually blue): Color(75, 115, 153, 255)
    // Selected: 

    private City[] chessBoard; // Create singular element array to allow passing by reference

    private Color colorWhite;
    private Color colorBlack;

    private Rectangle2D shapeConst;

    public Board(Color colorWhite_, Color colorBlack_){
        super(0, 0, 8, 12, 96); // 8x8 chess board, indexes from 0-7, 8x4 for eliminated pieces
        setFrameTitle("RoboChess");

        // Code here seems a bit sus (unsafe)
        chessBoard = new City[]{this}; 

        colorWhite = colorWhite_;
        colorBlack = colorBlack_;

        shapeConst = new Rectangle2D.Double(0.0, 0.0, 1.0, 1.0);

        for(int x = -32; x < 96; x++){
            for(int y = -32; y < 96; y++){
                this.getIntersection(y, x).setIcon(new ShapeIcon(shapeConst, colorWhite));
            }
        }


        // Create the pattern
        refreshBoard();

    }

    // returns reference of City
    public City[] getState(){
        chessBoard[0] = this;
        return chessBoard;
    }

    // Creates the chessboard pattern
    public void refreshBoard(){
        int offset = 0;
        for(int y = 0; y < 8; y++){
            for(int x = 0; x < 8; x++){
                if((x + y*8 + offset) % 2 == 0){
                    selectBoard(x, y, colorWhite);
                }else{
                    selectBoard(x, y, colorBlack);
                }
            }
            offset ^= 1;
        }
    }

    // Sets a specific intersection to a specific color
    public void selectBoard(int x, int y, Color color){
        this.getIntersection(y, x).setIcon(new CompositeIcon(new ShapeIcon(shapeConst, color.darker()), new ShapeIcon(shapeConst, color, 0.9)));
    }

}
