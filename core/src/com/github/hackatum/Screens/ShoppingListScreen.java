package com.github.hackatum.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.github.hackatum.Optimo;
import com.github.hackatum.resources.GreenScore;
import com.github.hackatum.resources.ShoppingList;

public class ShoppingListScreen extends ScreenAdapter {

    private static final float BACK_ARROW_WIDTH = 81;
    private static final float BACK_ARROW_HEIGHT = 57;
    private static final float BACK_ARROW_X1 = 50;
    private static final float BACK_ARROW_Y1 = 375;
    private static final float BACK_ARROW_X2 = BACK_ARROW_X1 + BACK_ARROW_WIDTH;
    private static final float BACK_ARROW_Y2 = BACK_ARROW_Y1 + BACK_ARROW_HEIGHT;

    private final Optimo game;
    private final ShoppingList shoppingList;
    private final GreenScore greenScore;

    private final Texture backArrowImg;

    private final List<String> list;

    public ShoppingListScreen(Optimo game, ShoppingList shoppingList, GreenScore greenScore) {
        this.game = game;
        this.shoppingList = shoppingList;
        this.greenScore = greenScore;

        backArrowImg = new Texture(Gdx.files.internal("BackArrow.png"));

        Skin uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
        list = new List<>(uiSkin);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {
                int renderY = Gdx.graphics.getHeight() - y;
                if (x > BACK_ARROW_X1 && x < BACK_ARROW_X2 && renderY > BACK_ARROW_Y1 && renderY < BACK_ARROW_Y2) {
                    game.setScreen(new MainScreen(game, shoppingList, greenScore));
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.094f, 0.396f, 0.416f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        greenScore.render(game.getBatch());
        game.getBatch().draw(backArrowImg, BACK_ARROW_X1, BACK_ARROW_Y1, BACK_ARROW_WIDTH, BACK_ARROW_HEIGHT);
        list.setItems("test1", "test7", "test6", "test5", "test4", "test3", "test2");
        list.draw(game.getBatch(), 20);
        game.getBatch().end();
    }
}
