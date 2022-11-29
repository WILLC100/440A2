package com.example.search;

import java.util.Arrays;

public class StateEstimate {
    private static final int OFFSET = 1;
    private double[][] StateProb;

    StateEstimate(int rows, int cols, double prior, BlockedQueue blocked){
        this.StateProb = new double[rows][cols];

        for(double[] row : StateProb){
            Arrays.fill(row, prior );
        }

        for(Node current : blocked.getList()) {
            StateProb[current.getY() - OFFSET][current.getX() - OFFSET] = 0;
        }

    }
    StateEstimate(int rows, int cols){
        this.StateProb = new double[rows][cols];

    }


    StateEstimate(StateEstimate prior){
        double[][] priorarr = prior.getArr();
        this.StateProb = new double[prior.getRows()][prior.getCols()];

        for(int i =0; i< StateProb.length; i++){
            System.arraycopy(priorarr[i], 0, StateProb[i], 0, StateProb[i].length);
        }

    }
    public double[][] getArr(){
        return this.StateProb;
    }

    public int getRows(){
        return this.StateProb.length;
    }
    public int getCols(){
        return this.StateProb[0].length;
    }



    public void print(){

        for(int i = 0; i<StateProb.length; i++){
            System.out.print("ROW " + (i+OFFSET) + ": ");

            for(int j = 0; j<StateProb[0].length; j++){
                System.out.print( StateProb[i][j] + ", " );
            }

            System.out.println();
        }
    }





}
