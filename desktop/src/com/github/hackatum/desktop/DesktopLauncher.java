package com.github.hackatum.desktop;

import ca.aqtech.mctreesearch4j.GenericSolver;
import ca.aqtech.mctreesearch4j.Node;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.hackatum.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DesktopLauncher {
	public static void main (String[] arg) throws IOException {
		OptimoGenerator og = new OptimoGenerator();
		og.run();



		// LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		// new LwjglApplication(new Optimo(), config);
	}
}
