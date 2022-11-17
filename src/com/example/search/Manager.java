package com.example.search;

public class Manager {

    public static void main(String[] args){



        // have it taken in arg and files at some point

        Grid grid = new Grid(3,3);
            grid[0][0] = new Node(1,1,CellType.HIGHWAY);
            grid[0][1] = new Node(1,2,CellType.HIGHWAY);
            grid[0][2] = new Node(1,3,CellType.TRAVERSE);
            grid[1][0] = new Node(2,1,CellType.NORMAL);
            grid[1][1] = new Node(2,2,CellType.NORMAL);
            grid[1][2] = new Node(2,3,CellType.NORMAL);
            grid[2][0] = new Node(3,1,CellType.NORMAL);
            grid[2][1] = new Node(3,2,CellType.BLOCKED);
            grid[2][2] = new Node(3,3,CellType.HIGHWAY);







    }
}
