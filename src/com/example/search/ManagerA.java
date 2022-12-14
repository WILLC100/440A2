package com.example.search;

public class ManagerA {

    public static void main(String[] args){

        int rows = 3;
        int cols = 3;
        // create grid base and mark blocked map
        Grid base = new Grid(rows, cols);
        BlockedMap blocked = new BlockedMap();
            base.assignType(1,1, CellType.HIGHWAY, blocked);
            base.assignType(2,1, CellType.HIGHWAY, blocked);
            base.assignType(3,1,CellType.TRAVERSE, blocked);
            base.assignType(1,2, CellType.NORMAL, blocked);
            base.assignType(2,2, CellType.NORMAL, blocked);
            base.assignType(3,2, CellType.NORMAL, blocked);
            base.assignType(1,3, CellType.NORMAL, blocked);
            base.assignType(2,3,CellType.BLOCKED, blocked);
            base.assignType(3,3, CellType.HIGHWAY, blocked);

        double prior =  1/ 8.0;
            //create action list
        ActionQueue actions = new ActionQueue();
            actions.add(Action.Right);
            actions.add(Action.Right);
          //  actions.add(Action.Down);
        //    actions.add(Action.Down);

            //create sensor list
        SensorQueue sensed = new SensorQueue();
            sensed.add(CellType.NORMAL);
            sensed.add(CellType.NORMAL);
       //     sensed.add(CellType.HIGHWAY);
       //     sensed.add(CellType.HIGHWAY);
         // Generate State Estimates     and print

       // System.out.println(prior);
        SEStorage store = new SEStorage(rows, cols, prior, blocked, base);
            store.create(actions, sensed);
            store.print();





    }
}
