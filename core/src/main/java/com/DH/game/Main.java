package com.DH.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture image;
    private Enemy enemy1,enemy2,enemy3;
    private Player player1;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");

        float h = Gdx.graphics.getHeight();
        float w = Gdx.graphics.getWidth();
        camera = new OrthographicCamera(1,h/w);
        camera.setToOrtho(false);
        image.setFilter(TextureFilter.Linear, Texture.TextureFilter.Linear);
        enemy1 = new Enemy();
        enemy1.setPosition(100,300);
        enemy2 = new Enemy();
        enemy2.setPosition(300,300);
        enemy3 = new Enemy();
        enemy3.setPosition(600,800);
        player1 = new Player();
        player1.setPosition(0,3);
       // TextureRegion region = new TextureRegion(image,0,0,512,275)

    }
    @Override
    public void render() {
        ScreenUtils.clear(0.2f, 0.2f, 0.2f, 1f);
        batch.begin();
        batch.draw(image, 800, 450);
        player1.draw(batch);
        enemy1.draw(batch);
        enemy2.draw(batch);
        enemy3.draw(batch);
        batch.end();

        //Updates
        player1.update(Gdx.graphics.getDeltaTime());
        Rectangle temp = new Rectangle(0, 0, 1920, 10);

       /* if(player1.hits(temp) != -1)
        {
            player1.action(1,10,0);
        }*/

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
        image.dispose();
    }
}
