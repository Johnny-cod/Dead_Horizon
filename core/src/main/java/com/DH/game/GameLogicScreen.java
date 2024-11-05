/*package com.DH.game;

import com.DH.game.Enemy;
import com.DH.gdx.Player;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
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

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
public class GameMain extends Game {
    private SpriteBatch batch;
    private Texture image;
    //    private Sprite sprite;
    private float elapsedTime;
    private int currentFrame;
    private Player player1;
    private Enemy enemy1;
    private Enemy enemy2;
//    private Floor floor1;
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
//        floor1 = new Floor();
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
//        floor1.draw(batch);
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
*/

package com.DH.game;

import com.DH.gdx.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameLogicScreen implements com.badlogic.gdx.Screen {
    private Main game;
    private SpriteBatch batch;
    private Texture image;
    private float elapsedTime;
    private int currentFrame;
    private Player player1;
    private Enemy enemy1;
    private Enemy enemy2;

    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;


    public GameLogicScreen(Main game) {
        // Constructor does not need a reference to GameMain
        this.game = game;
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(1920,1080,gamecam);
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("D:\\Dead_Horizon\\assets\\stuff\\level 1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gamecam.position.set(0, 0, 0 );
        elapsedTime = 0.0f;
        currentFrame = 0;
        player1 = new Player(300.0f, 500.0f);
        enemy1 = new Enemy(1000.0f, 700.0f);
        enemy2 = new Enemy(800.0f, 500.0f);
    }

    @Override
    public void show() {
       /* ScreenUtils.clear(0.85f, 0.15f, 0.2f, 1f);
        // Initialize game elements when the screen is shown
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");

        mapLoader = new TmxMapLoader();
        map = mapLoader.load("D:\\Dead_Horizon\\assets\\stuff\\level 1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        gamecam = new OrthographicCamera();
        gamePort = new FitViewport(1920,1080,gamecam);
        gamecam.position.set(0, 0, 0 );
        gamecam.update();
        System.out.println("The gamecam position x is " + gamecam.position.x);
        System.out.println("The gamecam position y is " + gamecam.position.y);
        elapsedTime = 0.0f;
        currentFrame = 0;
        player1 = new Player(300.0f, 500.0f);
        enemy1 = new Enemy(1000.0f, 700.0f);
        enemy2 = new Enemy(800.0f, 500.0f);*/
        if (map == null) {
            System.out.println("Map not loaded!");
        } else {
            System.out.println("Map loaded successfully!");
        }
    }
    public void handleInput(float delta)
    {
        if(Gdx.input.isTouched())
        {
            gamecam.position.x += 100 * delta;
//            System.out.println("The gamecam position x is " + gamecam.position.x);

//            System.out.println("The gamecam position y is " + gamecam.position.y);
        }

    }
    @Override
    public void render(float delta) {
        // Clear the screen and set background color



        update(delta);
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Begin rendering
      //  renderer.setView(gamecam);
        renderer.render();


        batch.begin();
        batch.setProjectionMatrix(gamecam.combined);
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
        Gdx.app.log("TAG", "Camera Position: " + gamecam.position.toString());
    }


    public void update(float delta)
    {
        handleInput(delta);
        gamecam.update();
        renderer.setView(gamecam);
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
        map.dispose();
        renderer.dispose();
        batch.dispose();
        image.dispose();
        player1.dispose();
        enemy1.dispose();
        enemy2.dispose();
    }
}

