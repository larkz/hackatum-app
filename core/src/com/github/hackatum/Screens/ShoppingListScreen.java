package com.github.hackatum.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.github.hackatum.Optimo;

public class ShoppingListScreen extends ScreenAdapter {

    private final Optimo game;

    public ShoppingListScreen(Optimo game) {
        this.game = game;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.25f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        game.getFont().draw(game.getBatch(), "Happy Shopping!", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);
        game.getBatch().end();
    }
}