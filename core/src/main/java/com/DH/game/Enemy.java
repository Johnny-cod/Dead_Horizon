package com.DH.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Enemy {
    Rectangle bottom, left, right, top;
    Sprite sprite;
    SpriteBatch batch;
    Texture texture;
    int action;

    float velocityY;

    public Enemy(float x, float y) {
        bottom = new Rectangle(0.0f, 0.0f, 192.0f, 220.0f);
        //assuming that the picture is 128x128 pixels
        texture = new Texture("libgdx.png");
        sprite = new Sprite(texture, 0, 0, 192, 220);
        this.setPosition(x, y);
    }

    public int hits(Rectangle r) {
        if (bottom.overlaps(r)) {
            return 1;
        }
        return -1;
    }

    public void moveLeft(float delta)
    {
        bottom.x -= (10 * delta);
        sprite.setPosition(bottom.x, bottom.y);
    }
    public void moveRight(float delta)
    {
        bottom.x += (10 * delta);
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
    public void dispose()
    {
        batch.end();
    }

}
