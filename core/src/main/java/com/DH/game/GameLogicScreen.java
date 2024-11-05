package com.DH.game;

import com.DH.gdx.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameLogicScreen implements com.badlogic.gdx.Screen {
    private SpriteBatch batch;
    private Texture image;
    private float elapsedTime;
    private int currentFrame;
    private Player player1;
    private Enemy enemy1;
    private Enemy enemy2;

    public GameLogicScreen() {
        // Constructor does not need a reference to GameMain
    }

    @Override
    public void show() {
        // Initialize game elements when the screen is shown
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, 1900, 980);  // Set the camera dimensions
        elapsedTime = 0.0f;
        currentFrame = 0;
        player1 = new Player(300.0f, 500.0f);
        enemy1 = new Enemy(1000.0f, 700.0f);
        enemy2 = new Enemy(800.0f, 500.0f);
    }

    @Override
    public void render(float delta) {
        // Clear the screen and set background color
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        // Begin rendering
        batch.begin();
        player1.draw(batch);  // Draw the player
        enemy1.draw(batch);    // Draw the first enemy
        enemy2.draw(batch);    // Draw the second enemy
        batch.end();

        // Update game elements
        player1.update(delta);

        // Handle player input
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player1.moveLeft(delta);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player1.moveRight(delta);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            player1.jump();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT)) {
            player1.attack();
        }
    }

    @Override
    public void resize(int width, int height) {
        // Handle resizing, if needed
    }

    @Override
    public void hide() {
        // Cleanup resources when the screen is hidden
    }

    @Override
    public void pause() {
        // Handle pause, if needed
    }

    @Override
    public void resume() {
        // Handle resume, if needed
    }

    @Override
    public void dispose() {
        // Cleanup resources when done
        batch.dispose();
        image.dispose();
        player1.dispose();
        enemy1.dispose();
        enemy2.dispose();
    }
}
