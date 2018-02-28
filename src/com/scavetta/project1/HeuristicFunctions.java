package com.scavetta.project1;

public class HeuristicFunctions
{

    private static Integer[] idealBoardArray = {
            BoardElementInt.WHITE,
            BoardElementInt.WHITE,
            BoardElementInt.WHITE,
            BoardElementInt.RANDOM,
            BoardElementInt.BLACK,
            BoardElementInt.BLACK,
            BoardElementInt.BLACK,
    };

    public static int function2(Board board)
    {
        Integer[] boardArray = board.getBoardArray();
        int value = 0;

        for (int i = 0; i < boardArray.length; i++)
        {
            if (boardArray[i] != idealBoardArray[i] && idealBoardArray[i] != BoardElementInt.RANDOM)
            {
                value++;
            }
        }

        return value;
    }

    public static int suggestedH(Board board)
    {
        Integer[] boardArray = board.getBoardArray();
        int value = 0;

        //find left most white block and count number of black blocks it took to find it
        boolean foundLeftMostWhite = false;
        int i = 0;
        while(foundLeftMostWhite){
            if (boardArray[i] == BoardElementInt.WHITE)
            {
                foundLeftMostWhite = true;
            }
            else if(boardArray[i] == BoardElementInt.BLACK)
            {
                value++;
            }
            i++;
        }
        return value;
    }
}
