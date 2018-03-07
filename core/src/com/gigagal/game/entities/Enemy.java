package com.gigagal.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.gigagal.game.utils.Assets;
import com.gigagal.game.utils.Constants;
import com.gigagal.game.utils.Enums;
import com.gigagal.game.utils.Enums.Direction;
import com.gigagal.game.utils.Utils;

/**
 * Created by mickey.1cx on 01.03.2018.
 */

public class Enemy {

    Platform platform;
    Vector2 position;
    Direction direction;

    private int health;

    final long startTime;

    public boolean active;

    public Enemy(Platform platform) {

        this.platform = platform;
        position = new Vector2(platform.left, platform.top + Constants.ENEMY_CENTER.y);
        direction = Direction.RIGHT;
        startTime = TimeUtils.nanoTime();

        health = Constants.ENEMY_HEALTH;
        active = true;

    }

    public void update(float delta) {

        position.x += delta * (
                (direction == Direction.RIGHT)
                        ? Constants.ENEMY_MOVEMENT_SPEED : -Constants.ENEMY_MOVEMENT_SPEED);

        if (direction == Direction.LEFT && position.x < platform.left) {
            position.x = platform.left;
            direction = Direction.RIGHT;
        } else if (direction == Direction.RIGHT && position.x > platform.right) {
            position.x = platform.right;
            direction = Direction.LEFT;
        }

        float elapsed = Utils.secondsSince(startTime);
        float bobAmplitude = 1 + MathUtils.sin(MathUtils.PI2 * elapsed / Constants.ENEMY_BOB_PERIOD);
        position.y = platform.top + Constants.ENEMY_CENTER.y + bobAmplitude * Constants.ENEMY_BOB_AMPLITUDE;

    }

    public void render(SpriteBatch batch, ShapeRenderer debugShapes) {

        TextureRegion sprite = Assets.instance.enemyAssets.enemy;

        Utils.drawTextureRegion(batch, sprite,position, Constants.ENEMY_CENTER);

        debugShapes.rect(
                position.x - Constants.ENEMY_CENTER.x,
                position.y - Constants.ENEMY_CENTER.y,
                Constants.ENEMY_RADIUS * 2, Constants.ENEMY_RADIUS * 2);

    }

    public Rectangle getCollider() {

        return new Rectangle(
                position.x - Constants.ENEMY_CENTER.x,
                position.y - Constants.ENEMY_CENTER.y,
                Constants.ENEMY_RADIUS * 2, Constants.ENEMY_RADIUS * 2);

    }

    public Vector2 getPosition() {
        return position;
    }

    public void hit() {

        health--;
        if (health == 0) {
            active = false;
        }

    }
}
