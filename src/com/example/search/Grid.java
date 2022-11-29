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

    public void assignType(  int x, int y, CellType cell, BlockedMap blocked){
        Node temp = new Node(x, y, cell );

        ground[y-1][x-1] = temp;

        if(temp.getType() == CellType.BLOCKED){
            blocked.put(temp.getPos());
        }


    }

    public Node visit(int x, int y){
        return this.ground[y-1][x-1];
    }



}
