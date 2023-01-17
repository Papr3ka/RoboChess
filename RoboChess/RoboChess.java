import java.awt.Color;
import java.awt.Point;
import java.lang.Math;
import java.lang.Thread;
import java.util.ArrayList;

import becker.robots.Direction;

// Main class
class RoboChess {

    // Sleep function
    //
    // int time - time to sleep specified in milliseconds
    public static void sleep(int time){
        try{
            Thread.sleep(time);
        }catch(InterruptedException ex){
            Thread.currentThread().interrupt();
        }
    }

    public static ArrayList<BasePiece> initializeBoard(Board board){
        ArrayList<BasePiece> pieces = new ArrayList<BasePiece>();

        // Black Pieces
        pieces.add(new Rook(board, 0, 0, BasePiece.Side.Black));
        pieces.add(new Rook(board, 7, 0, BasePiece.Side.Black));

        pieces.add(new Knight(board, 1, 0, BasePiece.Side.Black));
        pieces.add(new Knight(board, 6, 0, BasePiece.Side.Black));

        pieces.add(new Bishop(board, 2, 0, BasePiece.Side.Black));
        pieces.add(new Bishop(board, 5, 0, BasePiece.Side.Black));

        pieces.add(new Queen(board, 3, 0, BasePiece.Side.Black));

        pieces.add(new King(board, 4, 0, BasePiece.Side.Black));

        // Black pawns
        for(int i = 0; i < 8; i++){
            pieces.add(new Pawn(board, i, 1, BasePiece.Side.Black));
        }

        // White

        pieces.add(new Rook(board, 0, 7, BasePiece.Side.White));
        pieces.add(new Rook(board, 7, 7, BasePiece.Side.White));

        pieces.add(new Knight(board, 1, 7, BasePiece.Side.White));
        pieces.add(new Knight(board, 6, 7, BasePiece.Side.White));

        pieces.add(new Bishop(board, 2, 7, BasePiece.Side.White));
        pieces.add(new Bishop(board, 5, 7, BasePiece.Side.White));

        pieces.add(new Queen(board, 3, 7, BasePiece.Side.White));

        pieces.add(new King(board, 4, 7, BasePiece.Side.White));

        // White pawns
        for(int i = 0; i < 8; i++){
            pieces.add(new Pawn(board, i, 6, BasePiece.Side.White));
        }

        return pieces;
    }

    public static ArrayList<Point> getAllPoints(ArrayList<BasePiece> pieces){
        ArrayList<Point> allPoints = new ArrayList<Point>();
        for(BasePiece piece: pieces){
            allPoints.add(piece.getPos());
        }
        return allPoints;
    }

    public static ArrayList<Point> getSidePoints(ArrayList<BasePiece> pieces, BasePiece.Side side){
        ArrayList<Point> allPoints = new ArrayList<Point>();
        for(BasePiece piece: pieces){
            if(piece.getSide() == side){
                allPoints.add(piece.getPos());
            }
        }
        return allPoints;
    }

    // Returns the index of a piece at a point, returns -1
    public static int getPieceAt(ArrayList<BasePiece> pieces, Point p){
        for(int i = 0; i < pieces.size(); i++){
            if(pieces.get(i).getPos().equals(p)){
                return i;
            }
        }
        return -1;
    }

    // Blocks until a key has been pressed and returns its respective ASCII value
    public static int waitKeyInterrupt(KeyListener keyListener){
            // Wait for user input to move selector
            while(!keyListener.isNewKeyPressed()){
                sleep(1); // poll 1000x per second
            }

            // Detect selection
            return keyListener.getKey();
    }


    public static int smallestVarVec(ArrayList<Double> vec){
        // Error
        if(vec.size() == 0){
            return -1;
        }

        int idx = 0;
        double min = vec.get(0);

        for(int i = 1; i < vec.size(); i++){
            if(vec.get(i) < min){
                min = vec.get(i);
                idx = i;
            }
        }

        return idx;
    }

    public static Point getPiecePos(ArrayList<BasePiece> chessPieces, Class<?> piece, BasePiece.Side side){
        Point pPoint = new Point(64, 24);
        for(BasePiece p: chessPieces){
            if(p.getClass() == piece && p.getSide() == side){
                pPoint = p.getPos();
                break;
            }
        }

        return pPoint;
    }

    // Finds the closest point in the corresponding direction
    public static Point moveNextPoint(Point current, ArrayList<Point> options, Direction direction){

        for(int i = options.size() - 1; i >= 0 ; i--){
            if(options.get(i).equals(current)){
                options.remove(i);
            }
        }

        ArrayList<Double> distances = new ArrayList<Double>();

        Point cPoint;

        double dx;
        double dy;

        if(direction == Direction.NORTH){
            for(int i = options.size() - 1; i >= 0; i--){
                dx = options.get(i).getX() - current.getX();
                dy = options.get(i).getY() - current.getY();
                if(!(dy < 0.0 && dx <= Math.abs(dy))){
                    options.remove(i);
                }
            }
        }else if(direction == Direction.EAST){
            for(int i = options.size() - 1; i >= 0; i--){
                dx = options.get(i).getX() - current.getX();
                dy = options.get(i).getY() - current.getY();
                if(!(dx > 0.0 && Math.abs(dy) <= dx)){
                    options.remove(i);
                }
            }
        }else if(direction == Direction.SOUTH){

            for(int i = options.size() - 1; i >= 0; i--){
                dx = options.get(i).getX() - current.getX();
                dy = options.get(i).getY() - current.getY();
                if(!(dy > 0.0 && Math.abs(dx) <= dy)){
                    options.remove(i);
                }
            }
        }else{
            // WEST
            for(int i = options.size() - 1; i >= 0; i--){
                dx = options.get(i).getX() - current.getX();
                dy = options.get(i).getY() - current.getY();
                if(!(dx < 0.0 && dy <= Math.abs(dx))){
                    options.remove(i);
                }
            }
        }

        for(int i = 0; i < options.size(); i++){
            cPoint = options.get(i);
            distances.add(Math.sqrt(Math.pow(cPoint.getY() - current.getY(), 2) + Math.pow(cPoint.getX() - current.getX(), 2)));
        }

       if(options.size() == 0){
            return current;
        }


        if(smallestVarVec(distances) == -1){
            return options.get(0);
        }

        return options.get(smallestVarVec(distances));

    }

    public static BasePiece.Side oppositeTurn(BasePiece.Side turn){
        if(turn == BasePiece.Side.White){
            return BasePiece.Side.Black;
        }
        return BasePiece.Side.White;
    }

    // Returns true if the king is in check
    public static boolean checkCheck(ArrayList<BasePiece> chessPieces, BasePiece.Side turn){
        int kingIdx = -1;
        for(int idx = 0; idx < chessPieces.size(); idx++){
            if(chessPieces.get(idx) instanceof King && chessPieces.get(idx).getSide() == turn){
                kingIdx = idx;
                break;
            }
        }

        if(kingIdx == -1){
            return false; // Should not happen
        }

        ArrayList<Point> covers = new ArrayList<Point>();
        chessPieces.get(kingIdx).hide(); // Hide the king from other pieces
        for(int i = 0; i < chessPieces.size(); i++){
            if(chessPieces.get(i).getSide() == oppositeTurn(turn)){
                covers.addAll(chessPieces.get(i).getNextCovers(getSidePoints(chessPieces, turn), getSidePoints(chessPieces, oppositeTurn(turn))));
            }
        }
        chessPieces.get(kingIdx).show();
        if(covers.contains(chessPieces.get(kingIdx).getPos())){
            return true;
        }

        return false;
    }

    public static void main(String[] args){
        // Initialize Chess Board
        Board chessBoard                 = new Board(new Color(234, 233, 210, 255), new Color(75, 115, 153, 255));
        ArrayList<BasePiece> chessPieces = initializeBoard(chessBoard); // Polymorphism

        // Initialize KeyListener
        KeyListener keyListener = new KeyListener(chessBoard);
        int keyPress            = 0; // Converts char to int

        // Initialize Selector
        Point selector           = new Point(0, 7); // Selector starting position
        Point subSelector        = selector;
        boolean selectorIsHidden = false;

        // Initialize Colors
        Color selectionColor    = new Color(245, 118, 26, 255);
        Color subSelectionColor = new Color(255, 168, 54, 255);
        Color optionsColor      = new Color(141, 182, 0, 255);
        Color invalidColor      = new Color(210, 31, 60, 255);
        Color specialColor      = new Color(153, 102, 204, 255);

        // Initialize gamestates (turn, checkmate)
        BasePiece.Side turn = BasePiece.Side.White; // White first
        boolean checkMate   = false;
        boolean staleMate   = false;
        int teamMovablePieces;
        int turnCount       = 1;

        // Other variables
        ArrayList<Point> covers           = new ArrayList<Point>();
        ArrayList<Point> subPoints        = new ArrayList<Point>();
        ArrayList<Point> invalidSubPoints = new ArrayList<Point>();
        ArrayList<Point> specialPositions  = new ArrayList<Point>();
        int selectedPieceIndex            = -1;
        int mostRecentId                  = 0;
        int secondMostRecentId            = 0;
        int idCounter                     = 0;
        boolean inSubState                = false;
        boolean isPointClear              = true;
        boolean canCastle                 = false;
        boolean usingSpecialPos;
        Point originalPos;
        Point kingPos;
        Point elimNextClearWhite          = new Point(8, 7);
        Point elimNextClearBlack          = new Point(11, 0);
       

        // Main loop
        while(!checkMate && !staleMate){
            chessBoard.refreshBoard();
            if(!selectorIsHidden){
                chessBoard.selectBoard(selector.x, selector.y, selectionColor);
            }

            if(turn == BasePiece.Side.White){
                chessBoard.setFrameTitle("RoboChess: White Turn");
            }else{
                chessBoard.setFrameTitle("RoboChess: Black Turn");
            }

            covers.clear();

            keyPress = waitKeyInterrupt(keyListener);

            // Respond to selection (refers to ASCII codes) 
            // NOT case sensitive
            // WASD - Moves the selector around
            // H - toggles the selector
            //
            //
            //
            switch(keyPress){
                case 87: case 119: // W w
                    // Move up
                    if(selector.y > 0){
                        selector.y--;
                    }
                    break;

                case 65: case 97: // A a
                    // Move left
                    if(selector.x > 0){
                        selector.x--;
                    }
                    break;

                case 83: case 115: // S s
                    // Move right
                    if(selector.y < 7){
                        selector.y++;
                    }
                    break;

                case 68: case 100: // D d
                    // Move down
                    if(selector.x < 7){
                        selector.x++;
                    }
                    break;

                case 72: case 104:
                    selectorIsHidden = !selectorIsHidden;
                    break;

                case 10: // Enter
                    selectedPieceIndex = getPieceAt(chessPieces, new Point(selector.x, selector.y));
                    break;

                case 27: // ESCape key
                    break;
            }

            // Verify selections
            // Check if a piece has been selected (not just some empty spot on the board)
            if(selectedPieceIndex == -1){
                continue;
            }

            // Check if the piece on the current side whose turn it is
            if(chessPieces.get(selectedPieceIndex).getSide() != turn){
                continue;
            }

            // Enter substate
            inSubState  = true;
            subSelector = selector;

            // Wait for user to select option
            while(inSubState){

                // Gets the next positions in which the piece can move to
                subPoints = chessPieces.get(selectedPieceIndex).getNextPositions(getSidePoints(chessPieces, turn), getSidePoints(chessPieces, oppositeTurn(turn)));
                invalidSubPoints.clear();
                specialPositions.clear();

                usingSpecialPos = false;

                covers.clear();
                originalPos = chessPieces.get(selectedPieceIndex).getPos();
                
                // Ensure invalid moves cannot be made involving the king
                if(chessPieces.get(selectedPieceIndex) instanceof King){
    
                    chessPieces.get(selectedPieceIndex).tempModeOn();
                    // Remove all the points that would put the king in check
                    chessPieces.get(selectedPieceIndex).hide(); // Hide the king from other pieces
                    for(int i = 0; i < chessPieces.size(); i++){
                        if(chessPieces.get(i).getSide() == oppositeTurn(turn)){
                            covers.addAll(chessPieces.get(i).getNextCovers(getSidePoints(chessPieces, turn), getSidePoints(chessPieces, oppositeTurn(turn))));
                        }
                    }
                    chessPieces.get(selectedPieceIndex).show();
                    for(int i = subPoints.size() - 1; i >= 0; i--){
                        if(covers.contains(subPoints.get(i))){
                            invalidSubPoints.add(subPoints.get(i));
                            subPoints.remove(i);
                        }
                    }
                    chessPieces.get(selectedPieceIndex).tempModeOff();
                    covers.clear();
                }else{

                    // Check for other moves that could put the king in check
                    // Check for moves that are allowed if the king is in check
                    chessPieces.get(selectedPieceIndex).tempModeOn();
                    for(int i = subPoints.size() - 1; i >= 0; i--){
                        chessPieces.get(selectedPieceIndex).moveToPoint(subPoints.get(i));
                        isPointClear = true;
                        for(int j = 0; j < chessPieces.size(); j++){
                            if(chessPieces.get(j).getPos().equals(subPoints.get(i)) && chessPieces.get(j).getSide() == oppositeTurn(turn)){
                                isPointClear = false;
                                chessPieces.get(j).hide();
                                if(checkCheck(chessPieces, turn)){
                                    chessPieces.get(j).show();
                                    invalidSubPoints.add(subPoints.get(i));
                                    subPoints.remove(i);
                                    break;

                                }
                                chessPieces.get(j).show();
                            }
       
                        }
                        
                        if(isPointClear && checkCheck(chessPieces, turn)){
                            invalidSubPoints.add(subPoints.get(i));
                            subPoints.remove(i);
                        }
                        
                        chessPieces.get(selectedPieceIndex).moveToPoint(originalPos);
                    }
                    chessPieces.get(selectedPieceIndex).tempModeOff();   
                    covers.clear();        
                }

                // Castle
                if(chessPieces.get(selectedPieceIndex) instanceof King &&
                   chessPieces.get(selectedPieceIndex).getMoves() == 0){
                    canCastle = true;
                    for(int i = 0; i < chessPieces.size(); i++){
                        if(chessPieces.get(i).getSide() == turn &&
                           !(chessPieces.get(i) instanceof Rook ||
                            chessPieces.get(i) instanceof King) &&
                            chessPieces.get(i).getPos().y == chessPieces.get(selectedPieceIndex).getPos().y){
                            covers.add(chessPieces.get(i).getPos());
                           }
                    }
                    chessPieces.get(selectedPieceIndex).hide();
                    chessPieces.get(selectedPieceIndex).tempModeOn();
                    originalPos = chessPieces.get(selectedPieceIndex).getPos();
                    for(int i = 1; i <= 3; i++){
                        if(covers.contains(new Point(i, chessPieces.get(selectedPieceIndex).getPos().y))){
                            canCastle = false;
                            break;
                        }
                    }
                    for(int i = 0; i < chessPieces.size(); i++){
                        if(chessPieces.get(i).getSide() == turn &&
                           chessPieces.get(i) instanceof Rook &&
                           chessPieces.get(i).getPos().x <= 4 &&
                           chessPieces.get(i).getMoves() > 0){
                            canCastle = false;
                            break;
                           }
                    }
                    if(canCastle){
                        chessPieces.get(selectedPieceIndex).moveToPoint(new Point(2, chessPieces.get(selectedPieceIndex).getPos().y));
                        if(!checkCheck(chessPieces, turn)){
                            specialPositions.add(new Point(2, chessPieces.get(selectedPieceIndex).getPos().y));
                        }
                        chessPieces.get(selectedPieceIndex).moveToPoint(originalPos);
                        
                    }
                    canCastle = true;

                    for(int i = 5; i <= 6; i++){
                        if(covers.contains(new Point(i, chessPieces.get(selectedPieceIndex).getPos().y))){
                            canCastle = false;
                            break;
                        }
                    }
                    for(int i = 0; i < chessPieces.size(); i++){
                        if(chessPieces.get(i).getSide() == turn &&
                           chessPieces.get(i) instanceof Rook &&
                           chessPieces.get(i).getPos().x >= 5 &&
                           chessPieces.get(i).getMoves() > 0){
                            canCastle = false;
                            break;
                           }
                    }
                    if(canCastle){
                        chessPieces.get(selectedPieceIndex).moveToPoint(new Point(6, chessPieces.get(selectedPieceIndex).getPos().y));
                        if(!checkCheck(chessPieces, turn)){
                            specialPositions.add(new Point(6, chessPieces.get(selectedPieceIndex).getPos().y));
                        }
                        chessPieces.get(selectedPieceIndex).moveToPoint(originalPos);
                    }


                    chessPieces.get(selectedPieceIndex).show();
                    chessPieces.get(selectedPieceIndex).tempModeOff();
                    subPoints.addAll(specialPositions);

                }

                // En Passant
                if(chessPieces.get(selectedPieceIndex) instanceof Pawn){
                    for(int i = 0; i < chessPieces.size(); i++){
                        if(chessPieces.get(i).getSide() == oppositeTurn(turn) &&
                           chessPieces.get(i) instanceof Pawn &&
                           Math.abs(chessPieces.get(i).getPos().y - chessPieces.get(selectedPieceIndex).getPos().y) == 0 &&
                           Math.abs(chessPieces.get(i).getPos().x - chessPieces.get(selectedPieceIndex).getPos().x) == 1 &&
                           ((Pawn)chessPieces.get(i)).canEnPassant() &&
                           chessPieces.get(i).getId() == mostRecentId){
                            usingSpecialPos = true;
                            specialPositions.add(chessPieces.get(i).getPos());
                            subPoints.add(new Point(chessPieces.get(i).getPos().x, chessPieces.get(i).getPos().y - ((turn == BasePiece.Side.White) ? 1 : -1)));
                           }
                    }
                }

                if(!selectorIsHidden){
                    for(Point iSubP: invalidSubPoints){
                        chessBoard.selectBoard(iSubP.x, iSubP.y, invalidColor);
                    }
                    for(Point subP: subPoints){
                        chessBoard.selectBoard(subP.x, subP.y, optionsColor);
                    }
                    for(Point iSP: specialPositions){
                        chessBoard.selectBoard(iSP.x, iSP.y, specialColor);
                    }

                    chessBoard.selectBoard(subSelector.x, subSelector.y, subSelectionColor);
                }

                // Display possible options for the piece
                

                keyPress = waitKeyInterrupt(keyListener);
                switch(keyPress){
                    case 87: case 119: // W w
                        subSelector = moveNextPoint(subSelector, subPoints, Direction.NORTH);
                        break;
    
                    case 65: case 97: // A a
                        subSelector = moveNextPoint(subSelector, subPoints, Direction.WEST);
                        break;
    
                    case 83: case 115: // S s
                        subSelector = moveNextPoint(subSelector, subPoints, Direction.SOUTH);
                        break;
    
                    case 68: case 100: // D d
                        subSelector = moveNextPoint(subSelector, subPoints, Direction.EAST);
                        break;
    
                    case 72: case 104:
                        selectorIsHidden = !selectorIsHidden;
                        break;
    
                    case 10: // Enter
                        if(subSelector.equals(selector)){
                            break;
                        }

                        secondMostRecentId = mostRecentId;
                        mostRecentId = chessPieces.get(selectedPieceIndex).getId();
                        // Move piece here
                        chessPieces.get(selectedPieceIndex).moveTo(subSelector);

                        if(chessPieces.get(selectedPieceIndex) instanceof King &&
                           specialPositions.contains(subSelector)){
                            for(int i = 0; i < chessPieces.size(); i++){
                                if(chessPieces.get(i).getSide() == turn &&
                                   chessPieces.get(i) instanceof Rook &&
                                   Math.abs(chessPieces.get(i).getPos().x - chessPieces.get(selectedPieceIndex).getPos().x) <= 2){
                                    chessPieces.get(i).moveTo(new Point(chessPieces.get(selectedPieceIndex).getPos().x == 2 ? 3 : 5,
                                                                        chessPieces.get(selectedPieceIndex).getPos().y));
                                   }
                            }
                        }

                        // eliminate piece
                        for(int i = chessPieces.size() - 1; i >= 0; i--){
                            if((chessPieces.get(i).getPos().equals(subSelector) &&
                                chessPieces.get(i).getSide() == oppositeTurn(turn)) ||
                                (chessPieces.get(i).getPos().equals(new Point(subSelector.x, subSelector.y + ((turn == BasePiece.Side.White) ? 1 : -1))) &&
                                usingSpecialPos)){
                                if(chessPieces.get(i).getSide() == BasePiece.Side.Black){
                                    chessPieces.get(i).eliminate(elimNextClearBlack);
                                    elimNextClearBlack.y++;
                                    if(elimNextClearBlack.y > 7){
                                        elimNextClearBlack.y = 0;
                                        elimNextClearBlack.x--;
                                    }
                                }else{
                                    chessPieces.get(i).eliminate(elimNextClearWhite);
                                    elimNextClearWhite.y--;
                                    if(elimNextClearWhite.y < 0){
                                        elimNextClearWhite.y = 7;
                                        elimNextClearWhite.x++;
                                    }
                                }
                                //chessPieces.remove(i);
                                break;
                            }
                        }
                        
                        turn = oppositeTurn(turn);
                        inSubState = false;
                        chessBoard.refreshBoard();
                        if(!selectorIsHidden){
                            
                            chessBoard.selectBoard(selector.x, selector.y, selectionColor);
                        }
                        selector = subSelector;
                        break;
    
                    case 27: // ESCape key
                        // Exit sub selections
                        inSubState = false;
                        chessBoard.refreshBoard();
                        if(!selectorIsHidden){
                            
                            chessBoard.selectBoard(selector.x, selector.y, selectionColor);
                        }
                        break;
                }

            }

            // Check for checkmate
            teamMovablePieces = 0;
            for(int idx = 0; idx < chessPieces.size(); idx++){
                if(chessPieces.get(idx).getSide() == turn){
                  
                    subPoints = chessPieces.get(idx).getNextPositions(getSidePoints(chessPieces, turn), getSidePoints(chessPieces, oppositeTurn(turn)));
                    originalPos = chessPieces.get(idx).getPos();
                    for(int i = subPoints.size() - 1; i >= 0; i--){
                        chessPieces.get(idx).tempModeOn();
                        chessPieces.get(idx).moveToPoint(subPoints.get(i));
                        isPointClear = true;
                        for(int j = 0; j < chessPieces.size(); j++){
                            if(chessPieces.get(j).getPos().equals(subPoints.get(i)) && chessPieces.get(j).getSide() == oppositeTurn(turn)){
                                isPointClear = false;
                                chessPieces.get(j).hide();
                                if(checkCheck(chessPieces, turn)){
                                    chessPieces.get(j).show();
                                    subPoints.remove(i);
                                    break;

                                }
                                chessPieces.get(j).show();
                            }
        
                        }
                        
                        if(isPointClear && checkCheck(chessPieces, turn)){
                            subPoints.remove(i);
                        }
                        
                        chessPieces.get(idx).moveToPoint(originalPos);
                        chessPieces.get(idx).tempModeOff();
                    }

                    if(subPoints.size() > 0){
                        teamMovablePieces++;
                    }
                }
            }

            if(teamMovablePieces == 0){
                if(checkCheck(chessPieces, turn)){
                    checkMate = true;
                }else{
                    staleMate = true;
                }
            }

            // Loop End measures
            selectedPieceIndex = -1;
            turnCount++;
        }

        // Game end
        chessBoard.refreshBoard();

        if(checkMate){
            kingPos = getPiecePos(chessPieces, King.class, turn);
            chessBoard.selectBoard(kingPos.x, kingPos.y, invalidColor);
            if(turn == BasePiece.Side.Black){
                chessBoard.setFrameTitle("RoboChess: White Wins!");
            }else{
                chessBoard.setFrameTitle("RoboChess: Black Wins!");
            }
        }

        if(staleMate){
            chessBoard.setFrameTitle("RoboChess: Stalemate!");
        }
    }

}
