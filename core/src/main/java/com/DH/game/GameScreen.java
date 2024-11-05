package com.DH.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    private Main game;
    private com.DH.gdx.Player player;
    private SpriteBatch batch;

    public GameScreen(Main game) {
        this.game = game;
        this.player = new com.DH.gdx.Player(30,30); // Initialize the player
        this.batch = new SpriteBatch(); // Create a SpriteBatch for rendering
    }
    @Override
    public void show() {
        // Initialize game elements if needed
    }

    @Override
    public void render(float delta) {
        // Update game logic
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
      //  float delta = Gdx.graphics.getDeltaTime();
        update(delta);

        // Clear the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render game elements
        batch.begin();
        player.draw(batch); // Draw the player
        batch.end();
    }

    private void update(float delta) {
        // Handle input for player movement
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            player.moveLeft(delta);
        }
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            player.moveRight(delta);
        }
        if (Gdx.input.isKeyPressed(Keys.SPACE)) {
            player.jump();
        }

        // Update the player position and state
        player.update(delta);
    }

    @Override
    public void resize(int width, int height) {
        // Handle resizing the game screen
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        // Clean up resources
        batch.dispose();
        player.textureSheet.dispose(); // Dispose of the player texture if needed
    }
}
