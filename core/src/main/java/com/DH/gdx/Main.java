package com.DH.game;

import com.DH.gdx.Enemy;
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
    private Player player1;
    private Enemy enemy1;
    private Enemy enemy2;
    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false,1900,980);
        batch =new SpriteBatch();
//        sprite=new Sprite(image, 0,0,128,128);
//        sprite.setPosition(100,509);
        player1 = new Player(300.0f,500.0f);
        enemy1 = new Enemy(200.0f,100.0f);
        enemy2 = new Enemy(100.0f,200.0f);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
//        batch.draw(image, 800, 450);
        player1.draw(batch);
//        sprite.draw(batch);
        enemy1.draw(batch);
        enemy2.draw(batch);
        batch.end();

        //Updates
        player1.update(Gdx.graphics.getDeltaTime());
        Rectangle temp =new Rectangle(0,0,1900,10);
        if(player1.hits(temp)!=1)
        {
            player1.action(1,0,10);
        }
        //Controls
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT))
        {
            player1.moveLeft(Gdx.graphics.getDeltaTime());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT))
        {
            player1.moveRight(Gdx.graphics.getDeltaTime());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
        {
            player1.jump();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    //    image.dispose();
    }
}
