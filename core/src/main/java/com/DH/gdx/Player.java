package com.DH.gdx;

import java.awt.*;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Player {
    Rectangle bottom, left, right, top;
    Texture texture;
    Sprite sprite;
    int action;
    float velocityY;
    int health;

    public Player()
    {
        bottom = new Rectangle(0.0f, 0.0f,128.0f, 128.0f);

        texture = new Texture("libgdx.png");
        sprite = new Sprite(texture, 0, 0,128,128);
        this.setPosition(1000, 900);
        velocityY=0;
        health = 100;
    }
    public Player(float x, float y)
    {
        bottom = new Rectangle(0.0f, 0.0f,128.0f, 128.0f);
        texture = new Texture("libgdx.png");
        sprite = new Sprite(texture, 0, 0,128,128);
        this.setPosition(x, y);
        velocityY=0;

    }
    public int hits(Rectangle r) {
        if (bottom.overlaps(r)) {
            return 1;
        }
        return -1;
    }
    public void action(int type, float x, float y)
    {
        if(type==1)
        {
            this.setPosition(bottom.x, y);
        }
    }
    public void update(float delta)
    {
        velocityY-=50*delta;
        bottom.y+=velocityY;
        sprite.setPosition(bottom.x, bottom.y);
    }
    public void setPosition(float x, float y)
    {
        bottom.x=x;
        bottom.y=y;
        sprite.setPosition(x, y);

    }
    public void moveLeft(float delta)
    {
        bottom.x-=(100*delta);
        sprite.setPosition(bottom.x, bottom.y);
    }
    public void moveRight(float delta)
    {
        bottom.x+=(100*delta);
        sprite.setPosition(bottom.x, bottom.y);
    }
    public void jump()
    {
        velocityY=30;
    }
    public void draw(SpriteBatch batch)
    {
        sprite.draw(batch);
    }
}
