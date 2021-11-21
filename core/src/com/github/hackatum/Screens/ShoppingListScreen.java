package com.github.hackatum.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.github.hackatum.Optimo;
import com.github.hackatum.resources.GreenScore;
import com.github.hackatum.resources.ShoppingList;

public class ShoppingListScreen extends ScreenAdapter {

    //Dimensions
    //--Back arrow
    private static final float BACK_ARROW_WIDTH = 81;
    private static final float BACK_ARROW_HEIGHT = 57;
    private static final float BACK_ARROW_X1 = 50;
    private static final float BACK_ARROW_Y1 = 375;
    private static final float BACK_ARROW_X2 = BACK_ARROW_X1 + BACK_ARROW_WIDTH;
    private static final float BACK_ARROW_Y2 = BACK_ARROW_Y1 + BACK_ARROW_HEIGHT;

    //--Add element to table
    private static final float PLUS_WIDTH = 50;
    private static final float PLUS_HEIGHT = 50;
    private static final float PLUS_X1 = 750;
    private static final float PLUS_Y1 = 380;
    private static final float PLUS_X2 = PLUS_X1 + PLUS_WIDTH;
    private static final float PLUS_Y2 = PLUS_Y1 + PLUS_HEIGHT;

    //--Text field
    private static final float TEXT_WIDTH = 200;
    private static final float TEXT_HEIGHT = 50;
    private static final float TEXT_X = PLUS_X1 - TEXT_WIDTH - 25;
    private static final float TEXT_Y = PLUS_Y1;


    private final Optimo game;
    private final ShoppingList shoppingList;
    private final GreenScore greenScore;

    private final Texture backArrowImg;
    private final Texture addElementImg;

    private final Skin uiSkin;

    private final List<String> list;
    private final TextField addElementTextField;

    public ShoppingListScreen(Optimo game, ShoppingList shoppingList, GreenScore greenScore) {
        this.game = game;
        this.shoppingList = shoppingList;
        this.greenScore = greenScore;

        //for testing
        shoppingList.add("test1");
        shoppingList.add("test2");
        shoppingList.add("test3");
        shoppingList.add("test4");
        shoppingList.add("test5");

        backArrowImg = new Texture(Gdx.files.internal("BackArrow.png"));
        addElementImg = new Texture(Gdx.files.internal("AddElement.png"));

        uiSkin = new Skin(Gdx.files.internal("glassy-ui.json"));

        list = new List<>(uiSkin);

        addElementTextField = new TextField("", uiSkin);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {
                int renderY = Gdx.graphics.getHeight() - y;
                if (x > BACK_ARROW_X1 && x < BACK_ARROW_X2 && renderY > BACK_ARROW_Y1 && renderY < BACK_ARROW_Y2) {
                    game.setScreen(new MainScreen(game, shoppingList, greenScore));
                } else if (x > PLUS_X1 && x < PLUS_X2 && renderY > PLUS_Y1 && renderY < PLUS_Y2) {
                    // add element to list
                }
                return true;
            }

            @Override
            public boolean keyDown(int keycode) {
                if ((keycode >= 29 && keycode <= 54)) {
                    addElementTextField.appendText(Input.Keys.toString(keycode));
                } else if (keycode == 62) {
                    addElementTextField.appendText(" ");
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
        game.getBatch().draw(addElementImg, PLUS_X1, PLUS_Y1, PLUS_WIDTH, PLUS_HEIGHT);
        renderShoppingList();
        renderTextField();
        game.getBatch().end();
    }

    private void renderShoppingList() {
        String[] listShopping = new String[shoppingList.size()];
        for (int i = 0; i < listShopping.length; i++) {
            listShopping[i] = shoppingList.get(i);
        }
        list.setItems(listShopping);
        list.setPosition(300, 30);
        list.setSize(200, 400);
        list.draw(game.getBatch(), 1);
    }

    private void renderTextField() {
        addElementTextField.setPosition(TEXT_X, TEXT_Y);
        addElementTextField.setSize(TEXT_WIDTH, TEXT_HEIGHT);
        addElementTextField.draw(game.getBatch(), 1);
    }
}
