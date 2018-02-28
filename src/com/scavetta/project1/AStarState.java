package com.scavetta.project1;

public class AStarState implements Comparable<AStarState>{

    private Board board;
    private int distanceToHere;
    private AStarState previous;
    private Move moveThatGotHere;


    public AStarState(AStarState previous, Board board, Move move)
    {
        this.board = board;
        if (previous != null)
        {
            this.previous = previous;
            this.distanceToHere = previous.getDistanceToHere() + move.getNumJumps();
            this.moveThatGotHere = move;
        }
        else
        {
            this.distanceToHere = 0;
        }
    }

    public int getWeight()
    {
        return heuristic(board) + distanceToHere;
    }

    private int heuristic(Board b)
    {
        return HeuristicFunctions.function2(b);
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getDistanceToHere() {
        return distanceToHere;
    }

    public void setDistanceToHere(int distanceToHere) {
        this.distanceToHere = distanceToHere;
    }

    public AStarState getPrevious() {
        return previous;
    }

    public void setPrevious(AStarState previous) {
        this.previous = previous;
    }

    public Move getDirectionThatToHere() {
        return moveThatGotHere;
    }

    public void setDirectionThatToHere(Move moveThatGotHere) {
        this.moveThatGotHere = moveThatGotHere;
    }

    @Override
    public int compareTo(AStarState o) {
        int a = this.getWeight();
        int b = o.getWeight();
        return a - b;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof AStarState) {
            return this.getBoard().equals(((AStarState) other).getBoard());
        }
        return false;
    }
}
