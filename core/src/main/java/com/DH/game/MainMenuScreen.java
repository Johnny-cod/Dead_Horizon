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

public class MainMenuScreen implements Screen {
    private Stage stage;
    private Texture backgroundTexture;
    private ImageButton playButton, settingsButton, creditsButton, quitButton;
    private Main game;
    private Music backgroundMusic;

    // Updated constructor to accept backgroundMusic
    public MainMenuScreen(Main game, Music backgroundMusic) {
        this.game = game;
        this.backgroundMusic = backgroundMusic; // Store the music instance
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        backgroundMusic.setLooping(true);
        backgroundMusic.play();

        backgroundTexture = new Texture(Gdx.files.internal("Main menu.png"));
        Image backgroundImage = new Image(backgroundTexture);
        backgroundImage.setFillParent(true);
        stage.addActor(backgroundImage);

        Texture buttonTexture = new Texture(Gdx.files.internal("Play button.png"));
        Texture buttonTexture1 = new Texture(Gdx.files.internal("Settings button.png"));
        Texture buttonTexture2 = new Texture(Gdx.files.internal("Credits button.png"));
        Texture buttonTexture3 = new Texture(Gdx.files.internal("Quit button.png"));

        playButton = new ImageButton(new TextureRegionDrawable(buttonTexture));
        settingsButton = new ImageButton(new TextureRegionDrawable(buttonTexture1));
        creditsButton = new ImageButton(new TextureRegionDrawable(buttonTexture2));
        quitButton = new ImageButton(new TextureRegionDrawable(buttonTexture3));

        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }

        });

        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new SettingsScreen(game, backgroundMusic)); // Pass music instance
            }
        });

        creditsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new CreditsScreen(game, backgroundMusic)); // Pass music instance
            }
        });

        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        stage.addActor(playButton);
        stage.addActor(settingsButton);
        stage.addActor(creditsButton);
        stage.addActor(quitButton);
    }

    @Override
    public void show() {
        float buttonWidth = 210;
        float buttonHeight = 60;
        float buttonWidth1 = 350;
        float buttonHeight1 = 150;

        playButton.setSize(buttonWidth, buttonHeight);
        settingsButton.setSize(buttonWidth1, buttonHeight1);
        creditsButton.setSize(buttonWidth1, buttonHeight1);
        quitButton.setSize(buttonWidth, buttonHeight);

        float centerX = Gdx.graphics.getWidth() / 2 - buttonWidth / 2;
        float centerX1 = Gdx.graphics.getWidth() / 2 - buttonWidth1 / 2;

        playButton.setPosition(centerX, Gdx.graphics.getHeight() / 2 - 80);
        settingsButton.setPosition(centerX1, Gdx.graphics.getHeight() / 2 - 230);
        creditsButton.setPosition(centerX1, Gdx.graphics.getHeight() / 2 - 350);
        quitButton.setPosition(centerX, Gdx.graphics.getHeight() / 2 - 420);
    }

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
        backgroundTexture.dispose();
        backgroundMusic.stop(); // Stop music when disposing
        backgroundMusic.dispose();
    }
}
