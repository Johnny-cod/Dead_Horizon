package com.DH.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;

public class Main extends Game {
    private Music backgroundMusic;

    @Override
    public void create() {
        // Load background music and set it to loop
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("neon.mp3"));
        backgroundMusic.setLooping(true);
        backgroundMusic.setVolume(1.0f); // Set initial volume to 100%
        backgroundMusic.play();
        // Set the initial screen to the main menu
        setScreen(new MainMenuScreen(this, backgroundMusic)); // Pass the music instance
    }

    public Music getBackgroundMusic() {
        return backgroundMusic;
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    @Override
    public void dispose() {
        backgroundMusic.stop(); // Stop music when disposing
        backgroundMusic.dispose();
    }
}
