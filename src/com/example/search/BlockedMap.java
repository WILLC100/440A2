package com.example.search;

import java.util.HashMap;

public class BlockedMap {
    private HashMap<String, Integer> blockedPoints;

    BlockedMap(){
        this.blockedPoints = new HashMap<String, Integer>();
    }

    public int size(){
        return this.blockedPoints.size();
    }
    public void put(String coord){
        this.blockedPoints.put(coord, 1);
    }

    public boolean containsKey(String coord){
        return this.blockedPoints.containsKey(coord);
    }

}
