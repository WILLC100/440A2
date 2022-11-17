package com.example.search;

import java.util.LinkedList;

public class ActionQueue {

    private LinkedList<Action> queue;

    ActionQueue(){
        this.queue = new LinkedList<Action>();
    }

    public void insert(Action action){
        this.queue.add(action);
    }

    public Action pop(){
        return this.queue.removeFirst();
    }


}
