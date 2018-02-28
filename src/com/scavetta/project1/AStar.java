package com.scavetta.project1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Stack;

public class AStar {
    private HashMap<Board, AStarState> states;
    private PriorityQueue<AStarState> priorityQueue;
    private boolean verbose = true;

    public AStar(Board initialBoard) {
        priorityQueue = new PriorityQueue<AStarState>();
        states = new HashMap<Board, AStarState>();
        AStarState initial = new AStarState(null, initialBoard, null);
        // Populate PriorityQueue and HashMap
        priorityQueue.add(initial);
        states.put(initial.getBoard(), initial);
        System.out.println(initial.getBoard());
//        if (initial.getBoard().isWon()) {
//            System.out.println("Initial board is already won.");
//            return;
//        }
        this.run();
    }

    /**
     * The actual A* algorithm.
     * Pop the min, fetch possible moves, if moves not seen before, add them to PQ.
     * Repeat with some magic and you've solved sliding block puzzles.
     */
    public void run() {
        //TODO: check for unsolvable (Queue is empty, and no solution yet)
        //		print solution path
        Stack<AStarState> moves = new Stack<AStarState>();
        while (!priorityQueue.isEmpty()) {
            AStarState prev = priorityQueue.remove();

            if (verbose) {
                System.out.println("---------");
                System.out.println(prev.getBoard());
                System.out.printf("Removed min.\nWeight: %d\nDist: %d\n", prev.getWeight(), prev.getDistanceToHere());
            }

            ArrayList<Move> allPossibleMoves = prev.getBoard().getAllPossibleMoves();
            for (int i = 0; i < allPossibleMoves.size(); i++) {

//                if (verbose) {
//                    System.out.println("Direction: " + dirs.get(i));
//                }

                AStarState current = new AStarState(prev, prev.getBoard().executeMove(allPossibleMoves.get(i)), allPossibleMoves.get(i));

                // The case where we find the same board state that already exists in the queue but we found a faster path.
                if (states.containsKey(current.getBoard())) { // containsKey uses equals() in AStarState
                    if (states.get(current.getBoard()).getWeight() > current.getWeight()) {
                        states.remove(current.getBoard());
                        states.put(current.getBoard(), current);
                        priorityQueue.remove(current);
                        priorityQueue.add(current);
                    }
                    if (verbose) {
                        System.out.printf("Found existing state in map. \nWeight: %d\nDist: %d\n", current.getWeight(), current.getDistanceToHere());
                    }
                }

                if (!current.getBoard().isWon()) {
                    if (!states.containsKey(current.getBoard())) {
                        priorityQueue.add(current);
                        if (verbose) {
                            System.out.printf("Added to PQ.\nWeight: %d\nDist: %d\n", current.getWeight(), current.getDistanceToHere());
                        }
                        states.put(current.getBoard(), current);

                    }
                } else {
                    AStarState rover = new AStarState(current.getPrevious(), current.getBoard(), current.getDirectionThatToHere());
                    while (rover.getPrevious() != null) {
                        moves.push(rover);
                        rover = rover.getPrevious();
                    }

                    while (!(moves.isEmpty())) {
                        AStarState popped = moves.pop();
                        System.out.println("Direction: " + popped.getDirectionThatToHere());
                        System.out.println(popped.getBoard());
                    }

                    System.out.println("It works! Moves: " + current.getDistanceToHere());
                    return;
                }

            }
        }
    }
}
