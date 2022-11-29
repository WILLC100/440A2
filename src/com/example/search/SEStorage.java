package com.example.search;

import javax.swing.plaf.nimbus.State;
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
    public void add(StateEstimate state){
        this.SEStore.add(state);
        this.lastindex = lastindex++;
    }
    public void print(){
        for(StateEstimate current : SEStore){
            current.print();
        }
    }

    public void estimateCurrent(Action action, CellType cell){

        this.SEStore.add( generateState(action, cell, SEStore.get(lastindex)) );
    }

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

    private double calculation(){
        return 0;
    }



}
