package com.github.hackatum.resources;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.github.hackatum.Optimo.Optimo;


public class GreenScore {

    private static final float SCORE_POSITION_X = 60;
    private static final float SCORE_POSITION_Y = 45;
    public static float currentScore;
    public Optimo game;
    BitmapFont font;
    private String scoreString;

    public GreenScore(Optimo game) {
        currentScore = 0;
        scoreString = "Greenscore: 0";
        font = new BitmapFont();
        font.setColor(1, 1, 1, 1);
    }

    public void update(float addedScore) {
        currentScore += addedScore;
        int formattedScore = (int) currentScore;
        scoreString = "Distance: " + formattedScore;
    }

    public void render(SpriteBatch batch) {
        font.draw(batch, scoreString, SCORE_POSITION_X, SCORE_POSITION_Y);
    }

    public void dispose() {
        font.dispose();
    }
}
