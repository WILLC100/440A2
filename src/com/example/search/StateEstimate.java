package com.example.search;

import java.util.Arrays;

public class StateEstimate {
    private static final int OFFSET = 1;
    private double[][] StateProb;

    StateEstimate(int rows, int cols, double prior, BlockedMap blocked){
        this.StateProb = new double[rows][cols];

        for(double[] row : StateProb){
            Arrays.fill(row, prior );
        }

        for(String coord : blocked.keySet()){
            String[] temp = coord.split(",");
            this.StateProb[Integer.parseInt(temp[1])-1][Integer.parseInt(temp[0])-1] = 0;
        }

        //System.out.print(prior);

    }
    StateEstimate(int rows, int cols){
        this.StateProb = new double[rows][cols];

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
            System.out.print((i+OFFSET) + ": ");

            for(int j = 0; j<StateProb[0].length; j++){
                System.out.print( StateProb[i][j] + ", " );
            }



            System.out.println();
        }
        System.out.println();
    }





}
