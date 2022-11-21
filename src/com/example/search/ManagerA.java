package com.example.search;

public class ManagerA {

    public static void main(String[] args){



        // have it taken in arg and files at some point
        BlockedQueue blocked = new BlockedQueue();
        Grid grid = new Grid(3,3);
        Node[][] ground = grid.getGround();
            ground[0][0] = new Node(1,1,CellType.HIGHWAY);
            ground[0][1] = new Node(2,1,CellType.HIGHWAY);
            ground[0][2] = new Node(3,1,CellType.TRAVERSE);
            ground[1][0] = new Node(1,2,CellType.NORMAL);
            ground[1][1] = new Node(2,2,CellType.NORMAL);
            ground[1][2] = new Node(3,2,CellType.NORMAL);
            ground[2][0] = new Node(1,3,CellType.NORMAL);
            ground[2][1] = new Node(2,3,CellType.BLOCKED);
            blocked.add(ground[2][1]);
            ground[2][2] = new Node(3,3,CellType.HIGHWAY);

        double prior = 1.0/8.0;
        StateEstimate current = new StateEstimate(3,3, prior, blocked);
        current.print();

        ActionQueue actions = new ActionQueue();
            actions.add(Action.Right);
            actions.add(Action.Right);
            actions.add(Action.Down);
            actions.add(Action.Down);







    }
}
