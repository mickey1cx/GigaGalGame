package com.gigagal.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.gigagal.game.entities.GigaGal;
import com.gigagal.game.entities.Platform;

/**
 * Created by mickey.1cx on 25.02.2018.
 */

public class Level {

    private GigaGal gigaGal;
    public Array<Platform> platforms;


    public Level() {

        gigaGal = new GigaGal();
        platforms = new Array<Platform>();

        platforms.add(new Platform(70, 30, 20, 20));
        platforms.add(new Platform(30, 70, 40, 20));

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

}
