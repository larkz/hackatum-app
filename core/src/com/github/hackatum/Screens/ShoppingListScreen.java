package com.github.hackatum.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
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
    private Skin uiSkin;

    public ShoppingListScreen(Optimo game, ShoppingList shoppingList, GreenScore greenScore) {
        this.game = game;
        this.shoppingList = shoppingList;
        this.greenScore = greenScore;

        //for testing
        shoppingList.add("test1");shoppingList.add("test2");shoppingList.add("test3");shoppingList.add("test4");shoppingList.add("test5");

        backArrowImg = new Texture(Gdx.files.internal("BackArrow.png"));

        uiSkin = new Skin(Gdx.files.internal("glassy-ui.json"));

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
        list.setItems("test1", "test2", "test3", "test4");
        list.setPosition(500, 200);
        list.setSize(100, 300);
        list.draw(game.getBatch(), 1);
//        renderShoppingList();
        game.getBatch().end();
    }

    private void renderShoppingList() {
        int x = 100;
        int y = 0;
        for (String s : shoppingList) {
            game.getFont().draw(game.getBatch(), s, x, y);
            y += 20;
        }
    }
}
