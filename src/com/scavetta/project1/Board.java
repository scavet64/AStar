package com.scavetta.project1;

import java.util.ArrayList;

public class Board
{
    private Integer[] boardArray;

    public Board(Integer[] boardArray)
    {
        this.boardArray = boardArray;
    }

    public void swapElements(int elem1, int elem2)
    {
        Integer temp = boardArray[elem1];
        boardArray[elem1] = boardArray[elem2];
        boardArray[elem2] = temp;
    }

    public Integer[] getBoardArray() {
        return boardArray;
    }


    /**
     * @return Returns a list of possible moves for this board.
     */
    public ArrayList<Move> getAllPossibleMoves(){
        ArrayList<Move> moves = new ArrayList<>();
        for (int i = 0; i < boardArray.length; i++) {
            int tmp = i;
            //check one to left
            if(tmp - 1 >= 0){
                if(boardArray[tmp - 1] == BoardElementInt.EMPTY){
                    // valid move
                    moves.add(new Move(Direction.Left, 1, i));
                }
            }

            //check one to right
            if(tmp + 1 < boardArray.length){
                if(boardArray[tmp + 1] == BoardElementInt.EMPTY){
                    // valid move
                    moves.add(new Move(Direction.Right, 1, i));
                }
            }

            //check two to left
            if(tmp - 2 >= 0){
                if(boardArray[tmp - 2] == BoardElementInt.EMPTY){
                    // valid move
                    moves.add(new Move(Direction.Left, 2, i));
                }
            }

            //check two to right
            if(tmp + 2 < boardArray.length){
                if(boardArray[tmp + 2] == BoardElementInt.EMPTY){
                    // valid move
                    moves.add(new Move(Direction.Right, 2, i));
                }
            }
        }
        return moves;
    }

    /**
     * Executes a move on a clone of this board.
     * @param moveToDo the move that this method will execute
     * @return deepclone of the current board with the move executed
     */
    public Board executeMove(Move moveToDo){
        Integer[] boardArrayClone = new Integer[boardArray.length];
        for(int i = 0; i < boardArray.length; i++){
            boardArrayClone[i] = boardArray[i];
        }
        Board newBoard = new Board(boardArrayClone);

        int destinationIndex = 0;
        if(moveToDo.getDirection() == Direction.Left){
            destinationIndex = moveToDo.getIndexOfMover() - moveToDo.getNumJumps();
        } else {
            destinationIndex = moveToDo.getIndexOfMover() + moveToDo.getNumJumps();
        }
        newBoard.swapElements(moveToDo.getIndexOfMover(), destinationIndex);
        return newBoard;
    }

    /**
     * Uses the heuristic function to determine if this is a goal state
     * @return true if this board is in a goal state
     */
    public boolean isGoal(){
        return (HeuristicFunctions.function2(this) == 0);
    }


    @Override
    public String toString()
    {
        String tmp = "| ";
        for(Integer b: boardArray)
        {
            tmp += b.toString() + " | ";
        }
        return tmp;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Board) {
            Integer[] otherArray = ((Board) other).getBoardArray();

            for(int i = 0; i < otherArray.length; i++){
                if(otherArray[i] != this.boardArray[i]){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
