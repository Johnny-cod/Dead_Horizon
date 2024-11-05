package com.DH.gdx;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Floor
{
    Texture texture;
    Sprite sprite;
    Rectangle hitbox;
    public Floor()
    {
        Rectangle hitbox =new Rectangle(0,0,0,800);
        texture = new Texture("libgdx.png");
        sprite = new Sprite(texture, 0, 0,0,10);
    }
    public void draw(SpriteBatch batch)
    {
        sprite.draw(batch);
    }
    public void dispose() {
        texture.dispose();
    }
}
