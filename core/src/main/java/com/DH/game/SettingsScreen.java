package com.DH.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SettingsScreen implements Screen {
    private final Texture imageTexture;
    private Stage stage;
    private Main game;
    private Slider volumeSlider;
    private Music backgroundMusic;
    private Skin skin;
    private ImageButton backbutton;
    private Label volumeLabel; // Label to display volume percentage
    private Image image;

    public SettingsScreen(Main game, Music backgroundMusic) {
        this.game = game;
        this.backgroundMusic = backgroundMusic;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Load a skin
        skin = new Skin(Gdx.files.internal("C:\\Users\\keert\\Desktop\\Dead_Horizon\\assets\\ui\\uiskin.json"));

        // Load the settings background
        Texture settingsTexture = new Texture(Gdx.files.internal("Main menu background gradient.png"));
        Image background = new Image(settingsTexture);
        background.setFillParent(true);
        stage.addActor(background);

        imageTexture = new Texture(Gdx.files.internal("C:\\Users\\keert\\Downloads\\pngfind.com-sound-png-748701.png")); // Replace with your image file path
        image = new Image(imageTexture); // Create the Image actor

        // Set position and size (optional, adjust as needed)
        image.setPosition(Gdx.graphics.getWidth() / 2 - image.getWidth() / 2, Gdx.graphics.getHeight() / 2 - image.getHeight() / 2 +230);
        image.setSize(150, 150); // Set the size (width, height)

        // Add the image to the stage
        stage.addActor(image);

        // Volume label
        volumeLabel = new Label("Music Volume: 100%", skin); // Default to 100%
        volumeLabel.setPosition(Gdx.graphics.getWidth() / 2 - volumeLabel.getWidth() / 2, Gdx.graphics.getHeight() / 2 + 120);
        stage.addActor(volumeLabel);

        // Volume slider (range 0 to 100)
        volumeSlider = new Slider(0, 100, 1, false, skin);
        volumeSlider.setWidth(500);
        volumeSlider.setValue(backgroundMusic.getVolume() * 100); // Set initial volume level
        volumeSlider.setPosition(Gdx.graphics.getWidth() / 2 - volumeSlider.getWidth() / 2, Gdx.graphics.getHeight() / 2 - 50);
        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float volume = volumeSlider.getValue() / 100; // Convert to 0.0 - 1.0
                backgroundMusic.setVolume(volume); // Set the music volume based on the slider
                volumeLabel.setText("Music Volume: " + (int) volumeSlider.getValue() + "%"); // Update volume label
            }
        });
        stage.addActor(volumeSlider);

        // Back button to return to the main menu
        Texture buttonTexture = new Texture(Gdx.files.internal("Back Button.png"));
        backbutton = new ImageButton(new TextureRegionDrawable(buttonTexture));
        backbutton.setSize(250, 130);
        backbutton.setPosition(10, Gdx.graphics.getHeight() - backbutton.getHeight() - 10);
        backbutton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game, backgroundMusic)); // Pass music instance back
            }
        });

        stage.addActor(backbutton);
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
        skin.dispose();
    }
}
