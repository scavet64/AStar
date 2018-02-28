package com.scavetta.project1;

public class Main {

    public static void main(String[] args) {
	    //create starting board
        BoardElement[] startingBoardArray =
                {
                        BoardElement.Black,
                        BoardElement.Black,
                        BoardElement.Black,
                        BoardElement.Empty,
                        BoardElement.White,
                        BoardElement.White,
                        BoardElement.White
                };

        Board board = new Board(startingBoardArray);
        AStar aStar = new AStar(board);
    }
}
