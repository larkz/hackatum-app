package com.github.hackatum.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.github.hackatum.Optimo.Optimo;
import com.github.hackatum.Optimo.OptimoGenerator;
import com.github.hackatum.resources.GreenScore;
import com.github.hackatum.resources.ShoppingList;

import java.io.IOException;
import java.util.Map;

public class ShoppingListScreen extends ScreenAdapter {

    //Dimensions
    //--Back arrow
    private static final float BACK_ARROW_WIDTH = 81;
    private static final float BACK_ARROW_HEIGHT = 57;
    private static final float BACK_ARROW_X1 = 40;
    private static final float BACK_ARROW_Y1 = 375;
    private static final float BACK_ARROW_X2 = BACK_ARROW_X1 + BACK_ARROW_WIDTH;
    private static final float BACK_ARROW_Y2 = BACK_ARROW_Y1 + BACK_ARROW_HEIGHT;

    //--Add element to table
    private static final float PLUS_WIDTH = 50;
    private static final float PLUS_HEIGHT = 50;
    private static final float PLUS_X1 = 600;
    private static final float PLUS_Y1 = 380;
    private static final float PLUS_X2 = PLUS_X1 + PLUS_WIDTH;
    private static final float PLUS_Y2 = PLUS_Y1 + PLUS_HEIGHT;

    //--clear
    private static final float CLEAR_WIDTH = 102;
    private static final float CLEAR_HEIGHT = 57;
    private static final float CLEAR_X1 = 400;
    private static final float CLEAR_Y1 = 323;
    private static final float CLEAR_X2 = CLEAR_X1 + CLEAR_WIDTH;
    private static final float CLEAR_Y2 = CLEAR_Y1 + CLEAR_HEIGHT;

    //--clear table
    private static final float CLEAR_TABLE_WIDTH = 200;
    private static final float CLEAR_TABLE_HEIGHT = 67;
    private static final float CLEAR_TABLE_X1 = 400;
    private static final float CLEAR_TABLE_Y1 = 80;
    private static final float CLEAR_TABLE_X2 = CLEAR_TABLE_X1 + CLEAR_TABLE_WIDTH;
    private static final float CLEAR_TABLE_Y2 = CLEAR_TABLE_Y1 + CLEAR_TABLE_HEIGHT;

    //--call the optimizer
    private static final float START_WIDTH = 280;
    private static final float START_HEIGHT = 70;
    private static final float START_X1 = 120;
    private static final float START_Y1 = 75;
    private static final float START_X2 = START_X1 + START_WIDTH;
    private static final float START_Y2 = START_Y1 + START_HEIGHT;

    //--optimized purchase table
    private static final float OPT_TABLE_X1 = 920;
    private static final float OPT_TABLE_Y1 = 420;

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
    private final Texture clearTextImg;
    private final Texture clearTableImg;
    private final Texture goShoppingImg;

    private final Skin uiSkin;

    private final List<String> list;
    private final TextField addElementTextField;

    private final Table table;

    public ShoppingListScreen(Optimo game, ShoppingList shoppingList, GreenScore greenScore) {
        this.game = game;
        this.shoppingList = shoppingList;
        this.greenScore = greenScore;

        backArrowImg = new Texture(Gdx.files.internal("BackArrow.png"));
        addElementImg = new Texture(Gdx.files.internal("AddElement.png"));
        clearTextImg = new Texture(Gdx.files.internal("Clear.jpg"));
        clearTableImg = new Texture(Gdx.files.internal("ClearTable.png"));
        goShoppingImg = new Texture(Gdx.files.internal("StartButton.png"));

        uiSkin = new Skin(Gdx.files.internal("glassy-ui.json"));

        list = new List<>(uiSkin);
        addElementTextField = new TextField("", uiSkin);
        table = new Table(uiSkin);
        table.add(new Label("Brand", uiSkin)).width(135f).center();
        table.add(new Label("Price", uiSkin)).width(135f).center();
        table.add(new Label("CO2", uiSkin)).width(135f).center();
        table.row();
        table.setPosition(OPT_TABLE_X1, OPT_TABLE_Y1);
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
                    // add element to table
                    if (addElementTextField.getText().length()> 0) {
                        shoppingList.add(addElementTextField.getText());
                        addElementTextField.setText("");
                    }
                } else if (x > CLEAR_X1 && x < CLEAR_X2 && renderY > CLEAR_Y1 && renderY < CLEAR_Y2) {
                    // clear textbox
                    addElementTextField.setText("");
                } else if (x > CLEAR_TABLE_X1 && x < CLEAR_TABLE_X2 && renderY > CLEAR_TABLE_Y1 && renderY < CLEAR_TABLE_Y2) {
                    // clear table
                    shoppingList.clear();
                } else if (x > START_X1 && x < START_X2 && renderY > START_Y1 && renderY < START_Y2) {
                    // start the optimizer
                    // Arrays.asList("Barley", "Apples", "Bananas")
                    OptimoGenerator og = new OptimoGenerator(shoppingList, 500.0, 30000.0);
                    try {
                        String output = og.run();
                        java.util.List<Map<String, String>> parsedOutput = og.parseOutput(output);
                        table.reset();
                        table.add(new Label("Brand", uiSkin)).width(135f).center();
                        table.add(new Label("Price", uiSkin)).width(135f).center();
                        table.add(new Label("CO2", uiSkin)).width(135f).center();
                        table.row();
                        int rows = 0;
                        for (Map<String, String> row:parsedOutput) {
                            if (rows > 10) break;
                            table.add(new Label(row.get("brand"), uiSkin)).width(135f).center();
                            table.add(new Label(row.get("p"), uiSkin)).width(135f).center();
                            table.add(new Label(row.get("co2"), uiSkin)).width(135f).center();
                            table.row();
                            rows++;
                        }
                        table.setPosition(OPT_TABLE_X1, OPT_TABLE_Y1 - 22 * table.getRows()/2);
                    } catch (IOException e) {
                        System.out.println("Error: " + e.toString());
                    }
                }
                return true;
            }

            @Override
            public boolean keyDown(int keycode) {
                if ((keycode >= 29 && keycode <= 54)) {
                    addElementTextField.appendText(Input.Keys.toString(keycode));
                } else if (keycode == 62) {
                    addElementTextField.appendText(" ");
                } else if (keycode == 67) {
                    addElementTextField.setText("");
                } else if (keycode == 66) {
                    if (addElementTextField.getText().length()> 0) {
                        shoppingList.add(addElementTextField.getText());
                        addElementTextField.setText("");
                    }
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
        game.getBatch().draw(clearTextImg, CLEAR_X1, CLEAR_Y1, CLEAR_WIDTH, CLEAR_HEIGHT);
        game.getBatch().draw(clearTableImg, CLEAR_TABLE_X1, CLEAR_TABLE_Y1, CLEAR_TABLE_WIDTH, CLEAR_TABLE_HEIGHT);
        game.getBatch().draw(goShoppingImg, START_X1, START_Y1, START_WIDTH, START_HEIGHT);
        table.draw(game.getBatch(), 1);
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
        list.setPosition(150, 425 - 23 * list.getItems().size);
        list.setSize(200, 23 * list.getItems().size);
        list.draw(game.getBatch(), 1);
    }

    private void renderTextField() {
        addElementTextField.setPosition(TEXT_X, TEXT_Y);
        addElementTextField.setSize(TEXT_WIDTH, TEXT_HEIGHT);
        addElementTextField.draw(game.getBatch(), 1);
    }
}
