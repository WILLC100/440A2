package com.example.search;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class ManagerB {

    public static void main(String[] args){
            //System.out.println(System.getProperty("user.dir"));
            int i = 2;
            int j = 4;

            File current = new File("src/com/example/search/grids/grid" + i + "/grid" + i + ".txt");
            try {
                Scanner reader = new Scanner(current);

                int rows = reader.nextInt();
                int cols = reader.nextInt();

                Grid gridcurr = new Grid(rows, cols);
                BlockedMap blocked = new BlockedMap();
                while(reader.hasNext()){

                    int y = reader.nextInt();
                    int x = reader.nextInt();
                    CellType cell = CellType.parse(reader.nextLine() );

                    gridcurr.assignType(x, y, cell, blocked);

                }





                    try {
                        FileWriter pather = new FileWriter(new File("src/com/example/search/grids/grid"+i+"/path"+j+".txt"));

                         int x = randomX(cols);
                         int y = randomY(rows);

                        while(blocked.containsKey(x+","+y)){
                            x = randomX(cols);
                            y = randomY(rows);

                           // System.out.println("wack");
                        }

                        //System.out.println(x);
                        //System.out.println(y);

                        pather.write( x + " ");
                        pather.write(y+ "\n");

                        ActionQueue action100 = new ActionQueue();


                        actionList(action100 );

                        pather.write(action100.printAsString() +"\n");
                        

                        Node visitation = gridcurr.visit(x,y);

                        GroundTruthQueue GTQ = new GroundTruthQueue(x, y);

                        GTQ.generate(action100, gridcurr);

                        pather.write( GTQ.print() +  "\n");

                        SensorQueue sensed = new SensorQueue(visitation.getType());

                        sensed.generate(GTQ, gridcurr);


                       // System.out.println("WACK"); //sajdoiaojd


                        pather.write(sensed.print() + "\n");
                        pather.close();


                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }







            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }




    }




    public static void actionList(ActionQueue actions){
        Random rand = new Random();

        while(actions.size() != 100){

            int current = rand.nextInt(4);


            switch (current){

                case  0 :
                    actions.add(Action.Down);
                    break;
                case 1 :
                    actions.add(Action.Up);
                    break;
                case 2 :
                    actions.add(Action.Left);
                    break;
                case 3 :
                    actions.add(Action.Right);
                    break; 
                default:


            }




        }


    }
    public static int randomX(int x){

        Random rand = new Random();
        int ubound = x;
        return rand.nextInt(ubound) + 1;
    }

    public static int randomY(int y){
        Random rand = new Random();
        int ubound = y;
        return rand.nextInt(ubound) + 1;
    }
}
