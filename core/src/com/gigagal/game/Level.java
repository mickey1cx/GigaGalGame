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
import com.gigagal.game.entities.ExitPortal;
import com.gigagal.game.entities.Explosion;
import com.gigagal.game.entities.GigaGal;
import com.gigagal.game.entities.Platform;
import com.gigagal.game.entities.PowerUp;
import com.gigagal.game.utils.Constants;
import com.gigagal.game.utils.Enums;
import com.gigagal.game.utils.Enums.Direction;

import org.json.simple.JSONArray;

/**
 * Created by mickey.1cx on 25.02.2018.
 */

public class Level {

    private Viewport viewport;

    private GigaGal gigaGal;
    private ExitPortal exitPortal;
    private Array<Platform> platforms;

    private DelayedRemovalArray<Enemy> enemies;
    DelayedRemovalArray<Bullet> bullets;
    private DelayedRemovalArray<Explosion> explosions;
    private DelayedRemovalArray<PowerUp> powerUps;

    public Level(Viewport viewport) {

        this.viewport = viewport;

        init();
//        initDebugLevel();

    }

    private void initDebugLevel() {

        init();

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

        powerUps.add(new PowerUp(new Vector2(30, 106)));

    }

    private void init() {

        platforms = new Array<Platform>();
        enemies = new DelayedRemovalArray<Enemy>();
        bullets = new DelayedRemovalArray<Bullet>();
        explosions = new DelayedRemovalArray<Explosion>();
        powerUps = new DelayedRemovalArray<PowerUp>();

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

        updateEnemies(delta, gigagalCollider);
        updatePowerUps(delta, gigagalCollider);

        explosions.begin();
        for (Explosion explosion :
                explosions) {
            if (explosion.isFinished()) {
                explosions.removeValue(explosion, false);
            }
        }
        explosions.end();

    }

    private void updatePowerUps(float delta, Rectangle gigagalCollider) {

        powerUps.begin();
        for (PowerUp powerup :
                powerUps) {

            if (gigagalCollider.overlaps(powerup.getCollider())) {

                gigaGal.increaseAmmo(Constants.POWERUP_AMMO);
                powerUps.removeValue(powerup, false);

            }

        }
        powerUps.end();
    }

    private void updateEnemies(float delta, Rectangle gigagalCollider) {

        enemies.begin();
        for (Enemy enemy: enemies) {

            if (!enemy.active) {
                enemies.removeValue(enemy, false);
                spawnExplosion(enemy.getPosition());
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

        for (PowerUp powerup :
                powerUps) {
            powerup.render(batch);

        }

        exitPortal.render(batch);
        gigaGal.render(batch, debugShapes);

        for (Bullet bullet: bullets) {
            bullet.render(batch);
        }

        for (Explosion explosion :
                explosions) {
            explosion.render(batch);
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

    public DelayedRemovalArray<PowerUp> getPowerUps() {
        return powerUps;
    }

    public Viewport getViewport() {
        return viewport;
    }

    public DelayedRemovalArray<Enemy> getEnemies() {
        return enemies;
    }

    public void spawnExplosion(Vector2 position) {
        explosions.add(new Explosion(position));
    }


    public void addEnemy(Enemy enemy) {
        
        enemies.add(enemy);
        
    }

    public void setPlatforms(Array<Platform> platforms) {
        this.platforms = platforms;
    }

    public void setGigagal(GigaGal gigagal) {

        this.gigaGal = gigagal;

    }

    public void setExitPortal(ExitPortal portal) {
        this.exitPortal = portal;
    }
}
