package com.example.search;

import java.util.ArrayList;

public class SEStorage {

    private ArrayList<StateEstimate> SEStore;
    private int rows, cols, lastindex;
    private BlockedMap blocked;
    private Grid grid;


    SEStorage(int rows, int cols, double prior, BlockedMap blocked, Grid grid){
        this.SEStore = new ArrayList<StateEstimate>();
        this.rows = rows;
        this.cols = cols;
        this.blocked = blocked;
        this.grid = grid;
        this.lastindex = 0;
        this.SEStore.add(new StateEstimate(rows, cols, prior, blocked));

    }

    public void create(ActionQueue actions, SensorQueue sensed ){
        if(actions.size() != sensed.size() ){
            return;
        }

        while(actions.size() != 0 ){

            Action current = actions.pop();
            CellType reading = sensed.pop();
                generateState(current, reading);

        }


    }

    private void generateState(Action action, CellType reading){
        // get prior stateEstimate
        StateEstimate prior = this.SEStore.get(SEStore.size()-1);
            double[][] priorAr = prior.getArr();
        //create current estimate
        StateEstimate current = new StateEstimate(prior.getRows(), prior.getCols());
            double[][] currentAr = current.getArr();
            double currprior = 0;
            double currentsum = 0;

            for(int i = 0; i<priorAr.length; i++){ //y
                for(int j = 0; j<priorAr[0].length; j++){ //x goes through all x first

                    // go do each one.


                   /* boolean validAction = isValid(action, priorAr[0].length, priorAr.length, i+1, j+1);

                    if(!validAction){ // means that the cell in that direction is blocked or you have reached the edge

                        currprior = priorAr[i][j] * 0.9;
                        System.out.println("INVALID ACTION");
                    }else{  } */

                       currprior = actionCalc(priorAr, reading, action, i, j, rows, cols);


                    currentAr[i][j] = currprior;
                  //  System.out.println("AT " + i + " " + j + ": " + currprior);
                    currentsum += currprior;

                }
            }

        normalize(currentAr, currentsum);
      //  System.out.println(currentsum);
        this.SEStore.add(current);

    }

    private void normalize(double[][] currentAr, double currentsum){

        for(int i = 0; i<currentAr.length; i++){
            for (int j = 0; j<currentAr[0].length ; j++){
                currentAr[i][j] = currentAr[i][j]/currentsum;
            }
        }

    }

    private double actionCalc(double[][] priorAr,
                              CellType reading, Action action,
                              int y, int x,
                              int rows, int cols){

        if(this.grid.visit(x+1, y+1).getType() == CellType.BLOCKED){
                return 0;
        }


        double total = 0;

        switch(action){
            case Right : // move the right
               if(x > 0  ){ // if there is a contributing cell to the left

                   if(this.grid.visit(x+1, y+1).getType() == reading){ // correct
                       total+= priorAr[y][x-1] * 0.9 * 0.9;
                      //  System.out.println("CORRECT RIGHT ");
                   }else{ // correct incorrect reading
                       total+= priorAr[y][x-1] * 0.9 * 0.05;
                      // System.out.println("INCORRECT RIGHT ");
                   }
               }else{
               //System.out.println(x + " BAD X");
                    }

               if( x+2<=rows && this.grid.visit(x+2, y+1).getType() == CellType.BLOCKED){
                   return priorAr[y][x] * 0.9;
               }
               break;
            case Left :
                if(x < cols-1 ){ // if there is a contributing cell to the left

                    if(this.grid.visit(x+1, y+1).getType() == reading){ // correct
                        total+= priorAr[y][x+1] * 0.9 * 0.9;
                        //System.out.println("CORRECT LEFT ");
                    }else{ // correct incorrect reading
                        total+= priorAr[y][x+1] * 0.9 * 0.05;
                       // System.out.println("INCORRECT LEFT ");
                    }
                }

                if( x >= 1 && this.grid.visit(x, y+1).getType() == CellType.BLOCKED){
                    return priorAr[y][x] * 0.9;
                }
                break;
            case Up :
                if(y < rows-1 ){ // if there is a contributing cell to the left

                    if(this.grid.visit(x+1, y+1).getType() == reading){ // correct
                        total+= priorAr[y+1][x] * 0.9 * 0.9;
                        //System.out.println("CORRECT UP");
                    }else{ // correct incorrect reading
                        total+= priorAr[y+1][x] * 0.9 * 0.05;
                       // System.out.println("INCORRECT UP");
                    }
                }
                if( y >= 1 && this.grid.visit(x+1, y).getType() == CellType.BLOCKED){
                    return priorAr[y][x] * 0.9;
                }
                break;
            case Down :
                if(y > 0 ){ // if there is a contributing cell to the left

                    if(this.grid.visit(x+1, y+1).getType() == reading){ // correct
                        total+= priorAr[y-1][x] * 0.9 * 0.9;
                       // System.out.println("CORRECT DOWN");
                    }else{ // correct incorrect reading
                        total+= priorAr[y-1][x] * 0.9 * 0.05;
                       // System.out.println("INCORRECT DOWN");
                    }
                }
                if( y+2 <= rows && this.grid.visit(x+1, y+2).getType() == CellType.BLOCKED){
                    return priorAr[y][x] * 0.9;
                }
                break;
        }

        if(this.grid.visit(x+1, y+1).getType() == reading ){ //correct reading and stayed in place

            total+= priorAr[y][x] * 0.1 * 0.9;
           // System.out.println("CORRECT STAY ");
        }else{ //incorrect and stayed
            total+= priorAr[y][x] * 0.1 * 0.05;
          //  System.out.println("INCORRECT STAY ");
        }



        return total;

    }


    private boolean isValid(Action action, int cols, int rows, int y, int x ){

        switch(action){
            case Up :
                if(y == 1){
                    return false;
                }
                if ( CellType.BLOCKED == this.grid.visit(x, y-1).getType() ) {
                    return false;
                }
                return true;
            case Down:
                if(y+1 > rows ){
                    return false;
                }
                if ( CellType.BLOCKED == this.grid.visit(x , y+1).getType() ) {
                    return false;
                }

                return true;
            case Left :
                if(x == 1){
                    return false;
                }
                if ( CellType.BLOCKED == this.grid.visit(x-1, y ).getType() ) {
                    return false;
                }
                return true;
            case Right :
                if(x == cols){
                    return false;
                }
                if ( CellType.BLOCKED == this.grid.visit(x+1, y).getType() ) {
                    return false;
                }
                return true;


        }
        return false;

    }

    public void add(StateEstimate state){
        this.SEStore.add(state);
        this.lastindex = lastindex++;
    }
    public void print(){
        for(StateEstimate current : SEStore){
            current.print();
        }
    }






}
