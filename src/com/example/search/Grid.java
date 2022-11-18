package com.example.search;

import java.util.LinkedList;

public class Grid {
    private Node[][] ground;

    Grid(int rows, int cols){
        this.ground = new Node[rows][cols];
    }

    public Node[][] getGround(){
        return ground;
    }


}
