package com.example.search;

import java.util.LinkedList;

public class Grid {
    private Node[][] grid;

    Grid(int rows, int cols){
        this.grid = new Node[rows][cols];
    }

    public Node[][] getGrid(){
        return grid;
    }


}
