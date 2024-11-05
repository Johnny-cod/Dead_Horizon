package com.DH.gdx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class Player {
    public Rectangle hitbox;
    private Texture textureSheet;
    private Animation<TextureRegion> idleAnimation, walkAnimation, jumpAnimation, attackAnimation;
    private Animation<TextureRegion> currentAnimation;
    private float stateTime;
    private float velocityY;
    private int health;
    private boolean isJumping = false;

    private final float scale = 3.0f; // Scale factor for all frames

    public Player(float x, float y) {
        hitbox = new Rectangle(x, y, 32.0f * scale, 32.0f * scale);
        textureSheet = new Texture("playerowlet/Main character animations.png");
        stateTime = 0f;

        // Split sprite sheet into frames
        TextureRegion[][] tmpFrames = TextureRegion.split(textureSheet, 32, 32);

        // Set up animations with correct frame counts
        jumpAnimation = new Animation<>(0.1f, tmpFrames[0][0], tmpFrames[0][1], tmpFrames[0][2], tmpFrames[0][3], tmpFrames[0][4], tmpFrames[0][5], tmpFrames[0][6], tmpFrames[0][7], tmpFrames[0][8]); // 9 frames
        walkAnimation = new Animation<>(0.1f, tmpFrames[2][0], tmpFrames[2][1], tmpFrames[2][2], tmpFrames[2][3], tmpFrames[2][4], tmpFrames[2][5]); // 6 frames
        attackAnimation = new Animation<>(0.075f, tmpFrames[3][0], tmpFrames[3][1], tmpFrames[3][2], tmpFrames[3][3]);//, tmpFrames[1][4], tmpFrames[1][5], tmpFrames[1][6]); // 7 frames
        idleAnimation = new Animation<>(0.1f, tmpFrames[4][0], tmpFrames[4][1], tmpFrames[4][2], tmpFrames[4][3]); // 4 frames

        // Set the initial animation to idle
        currentAnimation = idleAnimation;

        setPosition(x, y);
        velocityY = 0;
        health = 100;
    }

    public void update(float delta) {
        stateTime += delta;

        if (isJumping) {
            currentAnimation = jumpAnimation;
            if (hitbox.y <= 30) {
                isJumping = false;
                currentAnimation = idleAnimation;
            }
        } else {
            currentAnimation = idleAnimation;
        }

        float maxVelocity = 600;
        if (velocityY < -maxVelocity) velocityY = -maxVelocity;
        else if (velocityY > maxVelocity) velocityY = maxVelocity;

        velocityY -= 200 * delta;
        hitbox.y += velocityY;

        if (hitbox.y < 30) {
            hitbox.y = 30;
            velocityY = 0;
        }

        setPosition(hitbox.x, hitbox.y);
    }

    public void setPosition(float x, float y) {
        hitbox.x = x;
        hitbox.y = y;
    }

    public void moveLeft(float delta) {

        if (hitbox.x > 0) {
            hitbox.x -= (400 * delta);
            setPosition(hitbox.x, hitbox.y);
            currentAnimation = walkAnimation;
        }
    }

    public void moveRight(float delta) {
        if (hitbox.x + 32 * scale < Gdx.graphics.getWidth()) {
            hitbox.x += (400 * delta);
            setPosition(hitbox.x, hitbox.y);
            currentAnimation = walkAnimation;
        }
    }

    public void jump() {
        if (!isJumping) {
            velocityY += 40;
            isJumping = true;
            currentAnimation = jumpAnimation;
        }
    }

    public void attack() {
        currentAnimation = attackAnimation;
        //stateTime = 0;
    }

    public void draw(SpriteBatch batch) {
        TextureRegion currentFrame = currentAnimation.getKeyFrame(stateTime, true);
        batch.draw(currentFrame, hitbox.x, hitbox.y, currentFrame.getRegionWidth() * scale, currentFrame.getRegionHeight() * scale);
    }

    public void dispose() {
        textureSheet.dispose();
    }
}
