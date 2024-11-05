package com.DH.game;

import com.DH.gdx.Enemy;
import com.DH.gdx.Floor;
import com.DH.gdx.Player;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;


import java.awt.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
//    private Sprite sprite;
    private float elapsedTime;
    private int currentFrame;
    private Player player1;
    private Enemy enemy1;
    private Enemy enemy2;
    private Floor floor1;
    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false,1900,980);
        elapsedTime = 0.0f;
        currentFrame = 0;
//        sprite=new Sprite(image, 0,0,128,128);
//        sprite.setPosition(100,509);
        player1 = new Player(300.0f,500.0f);
        floor1 = new Floor();
        enemy1 = new Enemy(1000.0f,700.0f);
        enemy2 = new Enemy(800.0f,500.0f);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        float delta = Gdx.graphics.getDeltaTime();

        // Begin rendering
        batch.begin();
        player1.draw(batch);
        enemy1.draw(batch);
        enemy2.draw(batch);
        floor1.draw(batch);
        batch.end();

        // Updates
        player1.update(delta);

        // Controls
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
    public void dispose() {
        batch.dispose();
        image.dispose();
        player1.dispose();
        enemy1.dispose();
        enemy2.dispose();
    }

}
