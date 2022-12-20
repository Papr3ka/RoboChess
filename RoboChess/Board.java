import becker.robots.City;

public class Board{

    private City[] chessBoard; // Create singular element array to allow passing by reference


    public Board(){
        chessBoard = new City[]{new City(8, 8)};
    }

    // returns reference of City
    public City[] getState(){
        return chessBoard;
    }

}
