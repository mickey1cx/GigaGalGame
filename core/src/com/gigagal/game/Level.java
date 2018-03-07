package com.gigagal.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gigagal.game.entities.Bullet;
import com.gigagal.game.entities.Enemy;
import com.gigagal.game.entities.GigaGal;
import com.gigagal.game.entities.Platform;
import com.gigagal.game.utils.Enums.Direction;

/**
 * Created by mickey.1cx on 25.02.2018.
 */

public class Level {

    private Viewport viewport;

    private GigaGal gigaGal;
    private Array<Platform> platforms;

    private DelayedRemovalArray<Enemy> enemies;
    DelayedRemovalArray<Bullet> bullets;

    public Level(Viewport viewport) {

        this.viewport = viewport;
        initDebugLevel();

    }

    private void initDebugLevel() {

        platforms = new Array<Platform>();
        enemies = new DelayedRemovalArray<Enemy>();
        bullets = new DelayedRemovalArray<Bullet>();

        platforms.add(new Platform(15, 100, 30, 20));

        Platform enemyPlatform = new Platform(75, 90, 100, 65);
        platforms.add(enemyPlatform);

        platforms.add(new Platform(35, 55, 50, 20));
        platforms.add(new Platform(10, 20, 20, 9));
        platforms.add(new Platform(100, 110, 30, 9));
        platforms.add(new Platform(200, 130, 30, 40));
        platforms.add(new Platform(150, 150, 30, 9));
        platforms.add(new Platform(150, 180, 30, 9));
        platforms.add(new Platform(200, 200, 9, 9));
        platforms.add(new Platform(280, 100, 30, 9));

        enemies.add(new Enemy(enemyPlatform));

        gigaGal = new GigaGal(this, new Vector2(15, 40));

    }

    public void update(float delta) {

        gigaGal.update(delta);
        Rectangle gigagalCollider = gigaGal.getCollider();

        bullets.begin();
        for (Bullet bullet : bullets) {
            bullet.update(delta);
            if (!bullet.active) {
                bullets.removeValue(bullet, false);
            }
        }
        bullets.end();

        enemies.begin();
        for (Enemy enemy: enemies) {

            if (!enemy.active) {
                enemies.removeValue(enemy, false);
                continue;
            }

            enemy.update(delta);

            if (gigagalCollider.overlaps(enemy.getCollider())) {

                Direction knockbackDirection = (gigaGal.getPosition().x > enemy.getPosition().x) ?
                        Direction.RIGHT : Direction.LEFT;

                gigaGal.applyKnockback(knockbackDirection);
            }

        }
        enemies.end();


    }


    public void render(SpriteBatch batch, ShapeRenderer debugShapes) {

        for (Platform platform : platforms) {
            platform.render(batch);
        }

        for (Enemy enemy: enemies) {
            enemy.render(batch, debugShapes);
        }

        gigaGal.render(batch, debugShapes);

        for (Bullet bullet: bullets) {
            bullet.render(batch);
        }

    }

    public GigaGal getGigaGal() {
        return gigaGal;
    }

    public Array<Platform> getPlatforms() {
        return platforms;
    }

    public void addBullet(Vector2 bulletPosition, Direction direction) {

        bullets.add(new Bullet(this, bulletPosition, direction));

    }

    public Viewport getViewport() {
        return viewport;
    }

    public DelayedRemovalArray<Enemy> getEnemies() {
        return enemies;
    }
}
