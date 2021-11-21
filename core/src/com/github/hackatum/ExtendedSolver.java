package com.github.hackatum;

import ca.aqtech.mctreesearch4j.GenericSolver;
import ca.aqtech.mctreesearch4j.MDP;
import ca.aqtech.mctreesearch4j.Node;
import ca.aqtech.mctreesearch4j.StateNode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ExtendedSolver extends GenericSolver<OptimoState, OptimoAction> {

    public ExtendedSolver(@NotNull MDP<OptimoState, OptimoAction> mdp, int simulationDepthLimit, double explorationConstant, double rewardDiscountFactor, boolean verbose) {
        super(mdp, simulationDepthLimit, explorationConstant, rewardDiscountFactor, verbose);
    }

    List<OptimoAction> getOptimalHorizon() {
        List<OptimoAction> optimalHorizonArr = new ArrayList();
        Node node = getRoot();
        OptimoAction firstAction = extractOptimalAction();

        node.getChildren(firstAction);

        // root.getChildren(new OptimoAction("Apple1"));
        return optimalHorizonArr;
    }

    /*
    fun getOptimalHorizon(): List<ActionType> {
        val optimalHorizonArr = mutableListOf<ActionType>()
        var node = root

        while (true){
            node = node.getChildren().maxByOrNull { c -> c.n } ?: break
            optimalHorizonArr.add(node.inducingAction!!)
        }

        return optimalHorizonArr
    }
     */

}
