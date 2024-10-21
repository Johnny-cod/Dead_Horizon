package com.DH.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Player {
    Rectangle bottom, left, right, top;
    Sprite sprite;
    SpriteBatch batch;
    Texture texture;
    int action;


    float velocityY;

    public Player() {
        bottom = new Rectangle(0.0f, 0.0f, 120.0f, 130.0f);
        //assuming that the picture is 128x128 pixels
        texture = new Texture("C:/Users/keert/Downloads/player1.png");
        sprite = new Sprite(texture, 30, 100, 120, 130);
        this.setPosition(0, 0);
        velocityY = 0;
    }

    public int hits(Rectangle r) {
        if (bottom.overlaps(r)) {
            return 1;
        }
        return -1;
    }

    public void moveLeft(float delta)
    {
        bottom.x -= (100 * delta);
        sprite.setPosition(bottom.x, bottom.y);
    }
    public void moveRight(float delta)
    {
        bottom.x += (200 * delta);
        sprite.setPosition(bottom.x, bottom.y);
    }
    public void setPosition(float x,float y)
    {
        bottom.x = x;
        bottom.y = y;
        sprite.setPosition(x,y);
    }
    public void draw(SpriteBatch batch)
    {
        sprite.draw(batch);
    }
    public void jump()
    {
        velocityY = 30;
    }
    public void update(float delta)
    {
        velocityY -= 50 * delta;
        bottom.y += velocityY;
        sprite.setPosition(bottom.x,bottom.y);
    }
    public void action(int type, float x, float y)
    {
        if(type == 1)
        {
            this.setPosition(x,y);
        }
    }
}
