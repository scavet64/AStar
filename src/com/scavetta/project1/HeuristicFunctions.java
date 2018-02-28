package com.scavetta.project1;

public class HeuristicFunctions
{

    private static BoardElement[] idealBoardArray = {
            BoardElement.White,
            BoardElement.White,
            BoardElement.White,
            BoardElement.Random,
            BoardElement.Black,
            BoardElement.Black,
            BoardElement.Black,
    };

    public static int function2(Board board)
    {
        BoardElement[] boardArray = board.getBoardArray();
        int value = 0;

        for (int i = 0; i < boardArray.length; i++)
        {
            if (boardArray[i] != idealBoardArray[i] && idealBoardArray[i] != BoardElement.Random)
            {
                value++;
            }
        }

        return value;
    }

    public static int suggestedH(Board board)
    {
        BoardElement[] boardArray = board.getBoardArray();
        int value = 0;

        //find left most white block and count number of black blocks it took to find it
        boolean foundLeftMostWhite = false;
        int i = 0;
        while(foundLeftMostWhite){
            if (boardArray[i] == BoardElement.White)
            {
                foundLeftMostWhite = true;
            }
            else if(boardArray[i] == BoardElement.Black)
            {
                value++;
            }
            i++;
        }
        return value;
    }
}
