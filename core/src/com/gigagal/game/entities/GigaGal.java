package com.gigagal.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.gigagal.game.utils.Assets;
import com.gigagal.game.utils.Constants;

/**
 * Created by mickey.1cx on 25.02.2018.
 */

public class GigaGal {

    public final static String TAG = GigaGal.class.getName();

    private Vector2 position;

    public GigaGal() {

        position = new Vector2(20, Constants.GIGAGAL_EYE_HEIGHT);

    }

    public void update(float delta) {

        Input input = Gdx.input;

        if (input.isKeyPressed(Keys.LEFT)) {
            moveLeft(delta);
        } else if (input.isKeyPressed(Keys.RIGHT)) {
            moveRight(delta);
        }

    }

    private void moveLeft(float delta) {

        position.x -= delta * Constants.GIGAGAL_SPEED;

    }

    private void moveRight(float delta) {

        position.x += delta * Constants.GIGAGAL_SPEED;

    }

    public void render(SpriteBatch batch) {

        TextureRegion standingRight = Assets.instance.gigaGalAssets.standingRight;

        batch.draw(standingRight.getTexture(),
                position.x - Constants.GIGAGAL_EYE_POSITION.x,
                position.y - Constants.GIGAGAL_EYE_POSITION.y,
                0, 0,
                standingRight.getRegionWidth(),
                standingRight.getRegionHeight(),
                1,1,0,
                standingRight.getRegionX(),
                standingRight.getRegionY(),
                standingRight.getRegionWidth(),
                standingRight.getRegionHeight(),
                false, false);

    }


}
