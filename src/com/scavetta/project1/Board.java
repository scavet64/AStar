package com.scavetta.project1;

import java.util.ArrayList;
import java.util.Arrays;

public class Board
{
    private BoardElement[] boardArray;

    public Board(BoardElement[] boardArray)
    {
        this.boardArray = boardArray;
    }

    public void swapElements(int elem1, int elem2)
    {
        BoardElement temp = boardArray[elem1];
        boardArray[elem1] = boardArray[elem2];
        boardArray[elem2] = temp;
    }

    public BoardElement[] getBoardArray() {
        return boardArray;
    }

    public void setBoardArray(BoardElement[] boardArray) {
        this.boardArray = boardArray;
    }

    public ArrayList<Move> getAllPossibleMoves(){
        ArrayList<Move> moves = new ArrayList<>();
        for (int i = 0; i < boardArray.length; i++) {
            int tmp = i;
            //check one to left
            if(tmp - 1 >= 0){
                if(boardArray[tmp - 1] == BoardElement.Empty){
                    // valid move
                    moves.add(new Move(Direction.Left, 1, i));
                }
            }

            //check one to right
            if(tmp + 1 < boardArray.length){
                if(boardArray[tmp + 1] == BoardElement.Empty){
                    // valid move
                    moves.add(new Move(Direction.Right, 1, i));
                }
            }

            //check two to left
            if(tmp - 2 >= 0){
                if(boardArray[tmp - 2] == BoardElement.Empty){
                    // valid move
                    moves.add(new Move(Direction.Left, 2, i));
                }
            }

            //check two to right
            if(tmp + 2 < boardArray.length){
                if(boardArray[tmp + 2] == BoardElement.Empty){
                    // valid move
                    moves.add(new Move(Direction.Right, 2, i));
                }
            }

        }
        return moves;
    }

    public Board executeMove(Move moveToDo){
        BoardElement[] boardArrayClone = new BoardElement[boardArray.length];
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

    public boolean isWon(){
        return (HeuristicFunctions.function2(this) == 0);
    }


    @Override
    public String toString()
    {
        String tmp = "| ";
        for(BoardElement b: boardArray)
        {
            tmp += b.toString() + " | ";
        }
        return tmp;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Board) {
            return Arrays.deepEquals(this.getBoardArray(), ((Board) other).getBoardArray());
        }
        return false;
    }
}
