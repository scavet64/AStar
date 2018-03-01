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

    /**
     * Heuristic function that returns a value for  the passed in board.
     * This will compare the current board's state with the ideal board state.
     * any board element is not in an ideal configuration, the h value is incremented.
     *
     * @param board board to evaluate
     * @return h value for this board
     */
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

    /**
     * The example heuristic that was provided on the homework assignment.
     * @param board board to evaluate
     * @return h value for this board
     */
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
