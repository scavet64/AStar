package com.scavetta.homework2;

import java.util.*;

public class AStar {
    private PriorityQueue<AStarState> frontier;
    private HashSet<AStarState> finished;

    public AStar(Board initialBoard) {
        frontier = new PriorityQueue<>();
        finished = new HashSet<>();
        AStarState initial = new AStarState(null, initialBoard, null);
        frontier.add(initial);
        System.out.println("Starting Board:");
        System.out.println(initial.getBoard());
        System.out.println("---------------------------");
    }

    /**
     * Run the A* algorithm.
     */
    public void run() {
        Stack<AStarState> winningMoves = new Stack<>();

        while (!frontier.isEmpty()) {
            AStarState prev = frontier.remove();

            //System.out.println("---------");
            //System.out.println(prev.getBoard());

            ArrayList<Move> allPossibleMoves = prev.getBoard().getAllPossibleMoves();
            for (int i = 0; i < allPossibleMoves.size(); i++) {

                AStarState current = new AStarState(prev, prev.getBoard().executeMove(allPossibleMoves.get(i)), allPossibleMoves.get(i));

                //is this win?
                if (current.getBoard().isGoal()) {
                    AStarState stateTraversal = new AStarState(current.getPrevious(), current.getBoard(), current.getMoveThatGotHere());
                    while (stateTraversal.getPrevious() != null) {
                        winningMoves.push(stateTraversal);
                        stateTraversal = stateTraversal.getPrevious();
                    }

                    while (!(winningMoves.isEmpty())) {
                        AStarState popped = winningMoves.pop();
                        System.out.println(
                                "Move index: "
                                        + popped.getMoveThatGotHere().getIndexOfMover()
                                        + ", "
                                        + popped.getMoveThatGotHere().getDirection()
                                        + " "
                                        + popped.getMoveThatGotHere().getNumJumps()
                                        + " places");
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
