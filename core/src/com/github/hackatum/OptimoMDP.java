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
import java.util.Arrays;
import java.util.List;

// <OptimoState, OptimoAction>
public class OptimoMDP extends MDP<OptimoState, OptimoAction> {

    List<List<String>> foodData;
    List<String> articleClassList;
    List<OptimoAction> possibleActions;
    List<List<String>> goalFoodData;
    Double weightMax;
    Double priceMax;

    public OptimoMDP(List<String> articleClassListInput, Double weightMaxInput, Double priceMaxInput) throws IOException {
        ingestGrocerySim();
        articleClassList = articleClassListInput;
        weightMax = weightMaxInput;
        priceMax = priceMaxInput;

        possibleActions = new ArrayList();
        goalFoodData = new ArrayList();

        for (int i = 0; i < foodData.size(); i++) {
            String articleClass = foodData.get(i).get(1);
            if ( articleClassList.contains(articleClass )  ) {
                List<String> filterList = foodData.get(i);
                goalFoodData.add( filterList );
                possibleActions.add( new OptimoAction(foodData.get(i).get(2) ) );
                // System.out.println(foodData.get(i).get(2));
            }
        }
        // System.out.println(goalFoodData.size());

    }

    public void printIO() {
        System.out.println("Optimo MDP");
        System.out.println(System.getProperty("user.dir") + "/python/data/sim_food.csv" );
        System.out.println(articleClassList);
    }

    public void ingestGrocerySim() throws FileNotFoundException, IOException {

        String filePath = System.getProperty("user.dir") + "/python/data/sim_food.csv" ;
        FileReader fr = new FileReader(filePath);
        CSVReader reader = new CSVReader(fr, ',' , '"' , 1);

        List<List<String>> ingestedData = new ArrayList<>();

        //Read CSV line by line and use the string array as you want
        String[] nextLine;
        while ((nextLine = reader.readNext()) != null) {
            if (nextLine != null) {
                //Verifying the read data here
                String commaSepString = Arrays.toString( nextLine).replace("[","").replace("]","").replace(" ","");
                ingestedData.add( Arrays.asList(commaSepString.split(",")));
            }
        }
        foodData = ingestedData;
    }

    @Override
    public OptimoState transition(OptimoState state, OptimoAction action) {

        double weightDelta = 0.0;
        double priceDelta = 0.0;
        double caloriesDelta = 0.0;
        double healthDelta = 0.0;
        double co2Delta = 0.0;

        for (int i = 0; i < goalFoodData.size(); i++) {
            List<String> foodData = goalFoodData.get(i);
            if (action.article == foodData.get(2) ){
                weightDelta = Double.parseDouble(foodData.get(3));
                priceDelta = Double.parseDouble(foodData.get(4));
                caloriesDelta = Double.parseDouble(foodData.get(5));
                healthDelta = Double.parseDouble(foodData.get(6));
                co2Delta = Double.parseDouble(foodData.get(7));
            }
        }

        double newWeight = state.weight + weightDelta;
        double newPrice = state.price + priceDelta;
        double newCalories = state.calories + caloriesDelta;
        double newHealth = state.health + healthDelta;
        double newCo2 = state.co2 + co2Delta;

        return new OptimoState(newWeight, newPrice, newCalories, newHealth, newCo2);
    }

    @Override
    public double reward(OptimoState previousState, OptimoAction action, OptimoState state) {
        return - state.co2;
    }

    @Override
    public OptimoState initialState() {
        // System.out.println(goalFoodData);
        return new OptimoState(0.0, 0.0, 0.0, 0.0, 0.0);
    }

    @Override
    public boolean isTerminal(OptimoState state) {
        if (state.weight <= weightMax && state.price <= priceMax && state.calories > 1100){
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Collection<OptimoAction> actions(OptimoState state) {
        // List<OptimoAction> actionsAvail = possibleActions;
        // actionsAvail.add(new OptimoAction("Apple1"));
        return possibleActions;
    }
}
