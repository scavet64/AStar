package com.scavetta.homework2;

public class Move {
    private Direction direction;
    private int numJumps;
    private int indexOfMover;

    public Move(Direction direction, int numJumps, int indexOfMover) {
        this.direction = direction;
        this.numJumps = numJumps;
        this.indexOfMover = indexOfMover;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getNumJumps() {
        return numJumps;
    }

    public void setNumJumps(int numJumps) {
        this.numJumps = numJumps;
    }

    public int getIndexOfMover() {
        return indexOfMover;
    }

    public void setIndexOfMover(int indexOfMover) {
        this.indexOfMover = indexOfMover;
    }
}
