package com.gigagal.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.gigagal.game.entities.GigaGal;
import com.gigagal.game.entities.Platform;

/**
 * Created by mickey.1cx on 25.02.2018.
 */

public class Level {

    private GigaGal gigaGal;
    private Array<Platform> platforms;


    public Level() {

        gigaGal = new GigaGal(this);
        platforms = new Array<Platform>();

        platforms.add(new Platform(70, 30, 20, 20));
        platforms.add(new Platform(30, 60, 40, 20));
        platforms.add(new Platform(100, 40, 30, 11));

    }

    public void update(float delta) {

        gigaGal.update(delta);

    }

    public void render(SpriteBatch batch) {

        for (Platform platform : platforms) {
            platform.render(batch);
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
