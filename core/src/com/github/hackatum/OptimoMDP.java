package com.github.hackatum;
import ca.aqtech.mctreesearch4j.MDP;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.io.FileReader;
import java.io.File;
import au.com.bytecode.opencsv.CSVReader;
import java.util.Arrays;


// <OptimoState, OptimoAction>
public class OptimoMDP extends MDP<OptimoState, OptimoAction> {

    public void printIO() {
        System.out.println("Optimo MDP");
        System.out.println(System.getProperty("user.dir") + "/python/data/sim_food.csv" );
    }

    public void ingestGrocerySim() throws FileNotFoundException, IOException {

        String filePath = System.getProperty("user.dir") + "/python/data/sim_food.csv" ;
        FileReader fr = new FileReader(filePath);
        CSVReader reader = new CSVReader(fr, ',' , '"' , 1);

        //Read CSV line by line and use the string array as you want
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine != null) {
                //Verifying the read data here
                System.out.println(Arrays.toString(nextLine));
            }
        }
    }



    @Override
    public OptimoState transition(OptimoState state, OptimoAction action) {
        return new OptimoState(5.0, 6.0, 1.1);
    }

    @Override
    public double reward(OptimoState previousState, OptimoAction action, OptimoState state) {
        return 5.0;
    }

    @Override
    public OptimoState initialState() {
        return new OptimoState(0.0, 0.0, 0.0);
    }

    @Override
    public boolean isTerminal(OptimoState state) {
        return false;
    }

    @Override
    public Collection<OptimoAction> actions(OptimoState state) {
        ArrayList<OptimoAction> actionsAvail = new ArrayList<OptimoAction>();
        actionsAvail.add(new OptimoAction("Apple1"));
        return actionsAvail;
    }
}
