package com.github.hackatum.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.hackatum.Optimo;
import com.github.hackatum.OptimoMDP;
import java.util.Arrays;

import java.util.List;

import java.io.IOException;

public class DesktopLauncher {
	public static void main (String[] arg) throws IOException {
		List<String> articleClassListInput = Arrays.asList("Barley", "Apples");
		OptimoMDP OM = new OptimoMDP(articleClassListInput);
		OM.printIO();
		OM.ingestGrocerySim();
		OM.initialState();

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Optimo(), config);
	}
}
