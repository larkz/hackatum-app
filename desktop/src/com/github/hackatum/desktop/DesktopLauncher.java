package com.github.hackatum.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.hackatum.Optimo;
import com.github.hackatum.OptimoMDP;

import java.io.IOException;

public class DesktopLauncher {
	public static void main (String[] arg) throws IOException {
		OptimoMDP OM = new OptimoMDP();
		OM.printIO();
		OM.ingestGrocerySim();

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Optimo(), config);
	}
}
