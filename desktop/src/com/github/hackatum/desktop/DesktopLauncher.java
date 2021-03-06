package com.github.hackatum.desktop;


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.github.hackatum.Optimo.Optimo;
import com.github.hackatum.Optimo.OptimoGenerator;

import java.io.IOException;
import java.util.Arrays;

public class DesktopLauncher {

    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//        config.title = "Óptimo";
        config.resizable = false;
        config.width = 1088;
        config.height = 459;
        new LwjglApplication(new Optimo(), config);
    }
}
