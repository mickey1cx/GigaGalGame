package com.gigagal.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gigagal.game.Level;
import com.gigagal.game.utils.Assets;
import com.gigagal.game.utils.Constants;
import com.gigagal.game.utils.Enums;
import com.gigagal.game.utils.Enums.Direction;
import com.gigagal.game.utils.Utils;

/**
 * Created by mickey.1cx on 07.03.2018.
 */

public class Bullet {

    private Vector2 position;
    private Direction direction;
    private TextureRegion sprite;

    private Level level;

    public boolean active;

    public Bullet(Level level, Vector2 position, Direction direction) {

        active = true;
        this.level = level;
        this.position = position;
        this.direction = direction;
        sprite = Assets.instance.bulletAssets.bullet;

    }

    public void update(float delta) {

        position.x += delta * ((direction == Direction.RIGHT) ? Constants.BULLET_SPEED : -Constants.BULLET_SPEED);

        for (Enemy enemy: level.getEnemies()) {

            Vector2 enemyPosition = enemy.getPosition();

            float dx = position.x - enemyPosition.x;
            float dy = position.y - enemyPosition.y;

            if (dx * dx + dy * dy < Constants.ENEMY_HIT_RADIUS * Constants.ENEMY_HIT_RADIUS) {
                active = false;
                enemy.hit();
            }

        }

        Viewport viewport = level.getViewport();

        float worldWidth = viewport.getWorldWidth();
        Vector3 cameraPosition = viewport.getCamera().position;

        if (Math.abs(position.x - cameraPosition.x) > worldWidth) {
            active = false;
        }

    }

    public void render(SpriteBatch batch) {

        Utils.drawTextureRegion(batch, sprite, position, Constants.BULLET_CENTER, direction == Direction.LEFT);

    }



}
