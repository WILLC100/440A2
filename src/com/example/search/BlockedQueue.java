package com.example.search;

import java.util.LinkedList;

public class BlockedQueue {
    private LinkedList<Node> blockedNodes;

    BlockedQueue(){
        this.blockedNodes = new LinkedList<Node>();
    }

    public void add(Node node){
        blockedNodes.add(node);
    }
    public Node pop(){
        return blockedNodes.removeFirst();
    }
    public int size(){
        return blockedNodes.size();
    }

    public LinkedList<Node> getList(){
        return blockedNodes;
    }
}
