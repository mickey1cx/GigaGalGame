package com.gigagal.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.DelayedRemovalArray;
import com.gigagal.game.entities.Enemy;
import com.gigagal.game.entities.GigaGal;
import com.gigagal.game.entities.Platform;
import com.gigagal.game.utils.Assets;

/**
 * Created by mickey.1cx on 25.02.2018.
 */

public class Level {

    private GigaGal gigaGal;
    private Array<Platform> platforms;

    private DelayedRemovalArray<Enemy> enemies;

    public Level() {

        initDebugLevel();

    }

    private void initDebugLevel() {

        platforms = new Array<Platform>();
        enemies = new DelayedRemovalArray<Enemy>();

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

        for (Enemy enemy: enemies) {
            enemy.update(delta);
        }

    }

    public void render(SpriteBatch batch) {

        for (Platform platform : platforms) {
            platform.render(batch);
        }

        for (Enemy enemy: enemies) {
            enemy.render(batch);
        }

        gigaGal.render(batch);

    }

    public GigaGal getGigaGal() {
        return gigaGal;
    }

    public Array<Platform> getPlatforms() {
        return platforms;
    }

}
