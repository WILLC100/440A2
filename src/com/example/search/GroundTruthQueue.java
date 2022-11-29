package com.example.search;

import java.util.ArrayList;

public class GroundTruthQueue {

    private ArrayList<String> truths;


    GroundTruthQueue(int x, int y){
        this.truths = new ArrayList<String>();
        this.truths.add(x+","+y);
    }

    public void generate(ActionQueue actions, BlockedMap blocked, Grid grid){

        String pos = this.truths.get(truths.size()-1);
        String[] coords = pos.split(",");
        int x = Integer.parseInt(coords[0]);
        int y = Integer.parseInt(coords[1]);

        for(Action current : actions){
            switch(current){

                case Down :
                    

            }



        }

    }
    //return true if valid

    public void add(String coord){
        this.truths.add(coord);
    }

    public String print(){

    }


}
