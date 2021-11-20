package com.github.hackatum.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.github.hackatum.Optimo;
import com.github.hackatum.resources.ShoppingList;

public class MainScreen extends ScreenAdapter {

    //Dimensions
    //--Supermarket
    private static final float SUPERMARKET_WIDTH = 279;
    private static final float SUPERMARKET_HEIGHT = 135;
    private static final float SUPERMARKET_X = 800;
    private static final float SUPERMARKET_Y = 130;

    //--Play button
    private static final float PLAYBUTTON_WIDTH = 476;
    private static final float PLAYBUTTON_HEIGHT = 90;
    private static final float PLAYBUTTON_X1 = 250;
    private static final float PLAYBUTTON_Y1 = 170;
    private static final float PLAYBUTTON_X2 = PLAYBUTTON_X1 + PLAYBUTTON_WIDTH;
    private static final float PLAYBUTTON_Y2 = PLAYBUTTON_Y1 + PLAYBUTTON_HEIGHT;

    //--Shopping list button
    private static final float LISTBUTTON_WIDTH = 299;
    private static final float LISTBUTTON_HEIGHT = 65;
    private static final float LISTBUTTON_X1 = 350;
    private static final float LISTBUTTON_Y1 = 130;
    private static final float LISTBUTTON_X2 = LISTBUTTON_X1 + LISTBUTTON_WIDTH;
    private static final float LISTBUTTON_Y2 = LISTBUTTON_Y1 + LISTBUTTON_HEIGHT;

    //--Running Avatar
    private static final int AVATAR_FRAME_COLS = 4;
    private static final int AVATAR_FRAME_ROWS = 3;
    private static final int AVATAR_FRAMES = 10;
    private static final float AVATAR_WIDTH = 140;
    private static final float AVATAR_HEIGHT = 162;
    private static final float AVATAR_X = 50;
    private static final float AVATAR_Y = 150;

    private final Optimo game;


    private final Texture supermarketImg;
    private final Texture playButtonImg;
    private final Texture listButtonImg;
    private Animation<TextureRegion> avatarRunningAnimation;

    private ShoppingList shoppingList;

    private float stateTime;

    public MainScreen(Optimo game, ShoppingList shoppingList) {
        this.game = game;

        supermarketImg = new Texture(Gdx.files.local("core/assets/supermarket.png"));
        playButtonImg = new Texture(Gdx.files.local("core/assets/StartButton.png"));
        listButtonImg = new Texture(Gdx.files.local("core/assets/ShoppingList.png"));
        initializeAvatarAnimation();

        this.shoppingList = shoppingList;

        stateTime = 0.0f;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {
                int renderY = Gdx.graphics.getHeight() - y;
                if (x > PLAYBUTTON_X1 && x < PLAYBUTTON_X2 && renderY > PLAYBUTTON_Y1 && renderY < PLAYBUTTON_Y2) {
                    //check if shopping list is empty
                    //go to a page with maybe a bar code scanner?
                } else if (x > LISTBUTTON_X1 && x < LISTBUTTON_X2 && renderY > LISTBUTTON_Y1 && renderY < LISTBUTTON_Y2) {
                    game.setScreen(new ShoppingListScreen(game));
                }
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        stateTime += Gdx.graphics.getDeltaTime();

        Gdx.gl.glClearColor(0.094f, 0.396f, 0.416f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        TextureRegion avatarCurrentFrame = avatarRunningAnimation.getKeyFrame(stateTime, true);
        game.getBatch().draw(avatarCurrentFrame, AVATAR_X, AVATAR_Y, AVATAR_WIDTH, AVATAR_HEIGHT);
        game.getBatch().draw(supermarketImg, SUPERMARKET_X, SUPERMARKET_Y, SUPERMARKET_WIDTH, SUPERMARKET_HEIGHT);
        game.getBatch().draw(playButtonImg, PLAYBUTTON_X1, PLAYBUTTON_Y1, PLAYBUTTON_WIDTH, PLAYBUTTON_HEIGHT);
        game.getBatch().draw(listButtonImg, LISTBUTTON_X1, LISTBUTTON_Y1, LISTBUTTON_WIDTH, LISTBUTTON_HEIGHT);
        game.getBatch().end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    private void initializeAvatarAnimation() {
        // load the avatar sprite sheet as a Texture
        Texture avatarRunningSheet = new Texture(Gdx.files.internal("core/assets/spritesheet_avatar.png"));

        // Use the split utility method to create a 2D array of TextureRegions. This is
        // possible because this sprite sheet contains frames of equal size and they are
        // all aligned.
        TextureRegion[][] tmp = TextureRegion.split(avatarRunningSheet,
                avatarRunningSheet.getWidth() / AVATAR_FRAME_COLS,
                avatarRunningSheet.getHeight() / AVATAR_FRAME_ROWS);

        // Place the regions into a 1D array in the correct order, starting from the top
        // left, going across first. The Animation constructor requires a 1D array.
        TextureRegion[] avatarFlyFrames = new TextureRegion[AVATAR_FRAMES];
        for (int i = 0; i < AVATAR_FRAMES; i++) {
            avatarFlyFrames[i] = tmp[i / AVATAR_FRAME_COLS][i % AVATAR_FRAME_COLS];
        }

        // Initialize the Animation with the frame interval and array of frames
        avatarRunningAnimation = new Animation<>(0.15f, avatarFlyFrames);
    }
}
