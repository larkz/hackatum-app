package com.github.hackatum.Optimo;

public class OptimoState {
    double weight;
    double price;
    double calories;
    double health;
    double co2;

    public OptimoState(double weightInput, double priceInput, double caloriesInput, double healthInput, double co2Input) {
        weight = weightInput;
        price = priceInput;
        calories = caloriesInput;
        health = healthInput;
        co2 = co2Input;
    }

    @Override
    public String toString() {
        return "w|" + weight + " p|" + price + " c|" + calories + " h|" + health + " co2|" + co2;
    }


}
