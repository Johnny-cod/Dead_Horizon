package com.DH.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class CreditsScreen implements Screen {
    private Stage stage;
    private ImageButton backButton;
    private Main game;
    private Music backgroundMusic;

    // Constructor now accepts backgroundMusic
    public CreditsScreen(Main game, Music backgroundMusic) {
        this.game = game;
        this.backgroundMusic = backgroundMusic; // Store the music instance
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Load the credits background
        Texture creditsTexture = new Texture(Gdx.files.internal("Main menu background gradient.png"));
        Image background = new Image(creditsTexture);
        background.setFillParent(true);
        stage.addActor(background);

        // Create a back button
        Texture buttonTexture = new Texture(Gdx.files.internal("Back Button.png"));
        backButton = new ImageButton(new TextureRegionDrawable(buttonTexture));
        float buttonWidth = 250; // Increased width for buttons
        float buttonHeight = 130;
        backButton.setSize(buttonWidth, buttonHeight);
        backButton.setPosition(10, Gdx.graphics.getHeight() - backButton.getHeight() - 10);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game, backgroundMusic)); // Pass music instance back
            }
        });

        stage.addActor(backButton);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
    }
}
