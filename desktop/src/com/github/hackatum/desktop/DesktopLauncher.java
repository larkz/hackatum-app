package com.github.hackatum.desktop;


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.hackatum.Optimo.Optimo;
import com.github.hackatum.Optimo.OptimoGenerator;

import java.io.IOException;
import java.util.Arrays;

public class DesktopLauncher {

    public static void main(String[] arg) throws IOException {
        OptimoGenerator og = new OptimoGenerator(Arrays.asList("Barley", "Apples", "Bananas"), 500.0, 30000.0);
        og.run();
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//        config.title = "Óptimo";
        config.resizable = false;
        config.width = 1088;
        config.height = 459;
        new LwjglApplication(new Optimo(), config);
    }
}
