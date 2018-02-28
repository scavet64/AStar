package com.scavetta.project1;

import java.util.*;

public class AStar {
    private HashMap<Board, AStarState> states;
    private PriorityQueue<AStarState> frontier;
    private HashSet<AStarState> finished;
    private boolean verbose = true;

    public AStar(Board initialBoard) {
        frontier = new PriorityQueue<AStarState>();
        finished = new HashSet<>();
        states = new HashMap<Board, AStarState>();
        AStarState initial = new AStarState(null, initialBoard, null);
        // Populate PriorityQueue and HashMap
        frontier.add(initial);
        states.put(initial.getBoard(), initial);
        System.out.println(initial.getBoard());
        this.run();
    }

    /**
     * Run the A* algorithm.
     */
    public void run() {
        Stack<AStarState> winningMoves = new Stack<AStarState>();

        while (!frontier.isEmpty()) {
            AStarState prev = frontier.remove();

            if (verbose) {
                System.out.println("---------");
                System.out.println(prev.getBoard());
            }

            ArrayList<Move> allPossibleMoves = prev.getBoard().getAllPossibleMoves();
            for (int i = 0; i < allPossibleMoves.size(); i++) {

                AStarState current = new AStarState(prev, prev.getBoard().executeMove(allPossibleMoves.get(i)), allPossibleMoves.get(i));

                //is this win?
                if (current.getBoard().isWon()) {
                    AStarState stateTraversal = new AStarState(current.getPrevious(), current.getBoard(), current.getMoveThatGotHere());
                    while (stateTraversal.getPrevious() != null) {
                        winningMoves.push(stateTraversal);
                        stateTraversal = stateTraversal.getPrevious();
                    }

                    while (!(winningMoves.isEmpty())) {
                        AStarState popped = winningMoves.pop();
                        System.out.println("Direction: " + popped.getMoveThatGotHere().getDirection() + ", " + popped.getMoveThatGotHere().getNumJumps());
                        System.out.println(popped.getBoard());
                    }

                    System.out.println("Winner! Total Moves: " + current.getDistanceToHere());
                    return;
                } else {
                    if(!frontier.contains(current) && !finished.contains(current)){
                        frontier.add(current);
                    }
                }
            }
            //no winning state was found for parent. Add to finished
            finished.add(prev);
        }
    }
}
