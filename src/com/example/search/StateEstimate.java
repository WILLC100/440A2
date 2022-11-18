package com.example.search;

import java.util.Arrays;

public class StateEstimate {
    private static final int OFFSET = 1;
    private double[][] StateProb;

    StateEstimate(int rows, int cols, double prior, BlockedQueue blocked){
        this.StateProb = new double[rows][cols];
        Arrays.fill(StateProb, prior );
        for(Node current : blocked.getList()) {
            StateProb[current.getY() - OFFSET][current.getX() - OFFSET] = 0;
        }

    }





}
