package com.example.search;

import java.util.ArrayList;

public class SensorQueue {

    private ArrayList<CellType> readings;

    SensorQueue(){
        this.readings = new ArrayList<CellType>();
    }

    public void add(CellType cell){
        this.readings.add(cell);
    }
}
