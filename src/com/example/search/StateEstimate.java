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
