package com.github.hackatum.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.hackatum.Optimo;
import com.github.hackatum.OptimoMDP;

public class DesktopLauncher {
	public static void main (String[] arg) {
		OptimoMDP OM = new OptimoMDP();
		OM.printIO();



		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Optimo(), config);
	}
}
