package com.github.hackatum;

import ca.aqtech.mctreesearch4j.GenericSolver;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class OptimoGenerator {



    public void run() throws IOException {
        List<String> articleClassListInput = Arrays.asList("Barley", "Apples");
        OptimoMDP OM = new OptimoMDP(articleClassListInput, 500.0, 30000.0);
        // OM.printIO();
        OM.ingestGrocerySim();
        OM.initialState();
        GenericSolver<OptimoState, OptimoAction> solver = new GenericSolver<OptimoState, OptimoAction>(OM, 99, 0.28, 0.98, true);

        // System.out.println(solver.extractOptimalAction());

        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        PrintStream PS = new PrintStream(boas);
        PrintStream old = System.out;
        System.setOut(PS);

        solver.runTreeSearch(4);
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println("-------");

        String[] stdoutStr = boas.toString().split("\n");
        System.out.println(stdoutStr[stdoutStr.length-2]);

        String outputStr = stdoutStr[stdoutStr.length-2];
    }


}
