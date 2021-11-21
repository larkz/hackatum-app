package com.github.hackatum.desktop;


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.hackatum.*;
import java.io.*;
import java.util.Arrays;

public class DesktopLauncher {
	public static void main (String[] arg) throws IOException {
		OptimoGenerator og = new OptimoGenerator(Arrays.asList("Barley", "Apples", "Bananas"),500.0, 30000.0);
		og.run();

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Optimo(), config);
	}
}
