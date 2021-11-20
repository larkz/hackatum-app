package com.github.hackatum;
import ca.aqtech.mctreesearch4j.MDP;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.Collection;

// <OptimoState, OptimoAction>
public class OptimoMDP extends MDP<OptimoState, OptimoAction> {

    public void printIO() {
        System.out.print("Optimo MDP");
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
