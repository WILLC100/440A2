package com.example.search;

import java.util.Arrays;

public class FilterEstimate {

    private double[][] StateProb;

    FilterEstimate(int rows, int cols, double prior, BlockedQueue blocked){
        this.StateProb = new double[rows][cols];
        Arrays.fill(StateProb, prior );



    }




}
