package com.gigagal.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gigagal.game.entities.GigaGal;

/**
 * Created by mickey.1cx on 25.02.2018.
 */

public class Level {

    private GigaGal gigaGal;

    public Level() {

        gigaGal = new GigaGal();


    }

    public void update(float delta) {

        gigaGal.update(delta);

    }

    public void render(SpriteBatch batch) {

        gigaGal.render(batch);

    }

}
