package com.github.hackatum.Optimo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.github.hackatum.Screens.MainScreen;
import com.github.hackatum.resources.GreenScore;
import com.github.hackatum.resources.ShoppingList;

public class Optimo extends Game {

    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;

    private ShoppingList shoppingList;
    private GreenScore greenScore;

    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        shoppingList = new ShoppingList(); //Storing a shoppingList locally might be too much for this project
        greenScore = new GreenScore(this);
        setScreen(new MainScreen(this, shoppingList, greenScore));
    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public ShapeRenderer getShapeRenderer() {
        return shapeRenderer;
    }

    public BitmapFont getFont() {
        return font;
    }
}
