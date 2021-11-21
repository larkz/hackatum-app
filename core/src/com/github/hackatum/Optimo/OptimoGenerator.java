package com.github.hackatum.Optimo;

import ca.aqtech.mctreesearch4j.GenericSolver;

import java.io.*;
import java.util.*;

public class OptimoGenerator {

    List<String> articleClasses;
    Double weightMax;
    Double priceMax;

    public OptimoGenerator(List<String> articleClassesInput, double weightMaxInput, double priceMaxInput) {
        articleClasses = articleClassesInput;
        weightMax = weightMaxInput;
        priceMax = priceMaxInput;
    }

    public String run() throws IOException {
        List<String> articleClassListInput = Arrays.asList("Barley", "Apples");
        OptimoMDP OM = new OptimoMDP(articleClasses, weightMax, priceMax);
        // OM.printIO();
        OM.ingestGrocerySim();
        OM.initialState();
        GenericSolver<OptimoState, OptimoAction> solver = new GenericSolver<OptimoState, OptimoAction>(OM, 99, 0.28, 0.98, true);

        // System.out.println(solver.extractOptimalAction());

        ByteArrayOutputStream boas = new ByteArrayOutputStream();
        PrintStream PS = new PrintStream(boas);
        PrintStream old = System.out;
        System.setOut(PS);

        solver.runTreeSearch(99);
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println("-------");

        String[] stdoutStr = boas.toString().split("\n");
        System.out.println(stdoutStr[stdoutStr.length - 2]);

        String outputStr = stdoutStr[stdoutStr.length - 2];
        return outputStr;
    }

    public List<Map<String, String>> parseOutput(String outputStr) {
        outputStr = outputStr.substring(3);
        List<Map<String, String>> parsedOutput = new ArrayList<>();
        String[] elements = outputStr.split(" -> ");
        for (int i = 0; i < elements.length - 1; i += 2) {
            Map<String, String> row = new HashMap<>();
            row.put("brand", elements[i]);
            String[] values = elements[i+1].split(" ");
            for (String param:values) {
                String[] fvPair = param.split("\\|");
                row.put(fvPair[0], String.format("%.2f", Float.parseFloat(fvPair[1])));
            }
            parsedOutput.add(row);
        }
        return parsedOutput;
    }


}
