package com.github.hackatum.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.hackatum.Optimo;
import com.github.hackatum.OptimoMDP;
import java.util.Arrays;
import java.util.List;
import java.io.IOException;

import ca.aqtech.mctreesearch4j.GenericSolver;

public class DesktopLauncher {
	public static void main (String[] arg) throws IOException {
		List<String> articleClassListInput = Arrays.asList("Barley", "Apples");
		OptimoMDP OM = new OptimoMDP(articleClassListInput, 500.0, 30000.0);
		// OM.printIO();
		OM.ingestGrocerySim();
		OM.initialState();
		GenericSolver solver = new GenericSolver(OM, 999, 0.28, 0.95, true);

		solver.runTreeSearch(99);

		// System.out.println(OM.actions(OM.initialState()));

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Optimo(), config);
	}
}
