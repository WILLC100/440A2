package com.example.search;

import java.util.ArrayList;

public class SEStorage {

    private ArrayList<StateEstimate> SEStore;
    private int rows, cols, lastindex;
    private BlockedMap blocked;
    private Grid grid;


    SEStorage(int rows, int cols, double prior, BlockedMap blocked, Grid grid){
        this.SEStore = new ArrayList<StateEstimate>();
        this.rows = rows;
        this.cols = cols;
        this.blocked = blocked;
        this.grid = grid;
        this.lastindex = 0;
        this.SEStore.add(new StateEstimate(rows, cols, prior, blocked));

    }

    public void create(ActionQueue actions, SensorQueue sensed ){
        if(actions.size() != sensed.size() ){
            return;
        }

        while(actions.size() != 0 ){

            Action current = actions.pop();
            CellType reading = sensed.pop();
                generateState(current, reading);

        }


    }

    private void generateState(Action action, CellType reading){
        // get prior stateEstimate
        StateEstimate prior = this.SEStore.get(SEStore.size()-1);
            double[][] priorAr = prior.getArr();
        //create current estimate
        StateEstimate current = new StateEstimate(prior.getRows(), prior.getCols());
            double[][] currentAr = current.getArr();
            double currprior = 0;
            double currentsum = 0;

            for(int i = 0; i<priorAr.length; i++){ //y
                for(int j = 0; j<priorAr[0].length; j++){ //x goes through all x first

                    // go do each one.

                    boolean validAction = isValid(action, priorAr[0].length, priorAr.length, i+1, j+1);

                    if(!validAction){ // means that the cell in that direction is blocked or you have reached the edge


                        currprior = priorAr[i][j] * 0.9;

                    }else{

                       currprior = actionCalc( action, i, j);

                    }
                    currentAr[i][j] = currprior;
                    currentsum += currprior;

                }
            }




        this.SEStore.add(current);





    }

    private double actionCalc(Action action, int y, int x){

        switch(action){
            case Up :








        }

    }


    private boolean isValid(Action action, int cols, int rows, int y, int x ){

        switch(action){
            case Up :
                if(y == 1){
                    return false;
                }
                if ( CellType.BLOCKED == this.grid.visit(x, y-1).getType() ) {
                    return false;
                }
                return true;
            case Down:
                if(y+1 > rows ){
                    return false;
                }
                if ( CellType.BLOCKED == this.grid.visit(x , y+1).getType() ) {
                    return false;
                }

                return true;
            case Left :
                if(x == 1){
                    return false;
                }
                if ( CellType.BLOCKED == this.grid.visit(x-1, y ).getType() ) {
                    return false;
                }
                return true;
            case Right :
                if(x+1 > cols){
                    return false;
                }
                if ( CellType.BLOCKED == this.grid.visit(x+1, y).getType() ) {
                    return false;
                }
                return true;


        }
        return false;

    }

    public void add(StateEstimate state){
        this.SEStore.add(state);
        this.lastindex = lastindex++;
    }
    public void print(){
        for(StateEstimate current : SEStore){
            current.print();
        }
    }


 /*
    private StateEstimate  generateState(Action action, CellType cell, StateEstimate prior){


            double[][] priorArr = prior.getArr();

            StateEstimate current = new StateEstimate(prior.getRows(), prior.getCols());

                double[][] currentArr = current.getArr();

                for(int i =0; i<currentArr.length; i++){

                    for(int j = 0; j<currentArr[0].length; j++){

                      //  currentArr[i][j] = calculation(current, prior, action, cell);
                    }
                }

                return current;

    }
*/




}
