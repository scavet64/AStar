package com.scavetta.project1;



public class Main {

    public static void main(String[] args) {
	    //create starting board
        Integer[] startingBoardArray =
                {
                        BoardElementInt.BLACK,
                        BoardElementInt.BLACK,
                        BoardElementInt.BLACK,
                        BoardElementInt.EMPTY,
                        BoardElementInt.WHITE,
                        BoardElementInt.WHITE,
                        BoardElementInt.WHITE
                };

        Board board = new Board(startingBoardArray);
        AStar aStar = new AStar(board);
    }
}
