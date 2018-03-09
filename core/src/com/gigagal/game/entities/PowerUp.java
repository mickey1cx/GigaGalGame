package com.gigagal.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.gigagal.game.utils.Assets;
import com.gigagal.game.utils.Constants;
import com.gigagal.game.utils.Utils;

/**
 * Created by mickey.1cx on 09.03.2018.
 */

public class PowerUp {

    private Vector2 position;

    public PowerUp(Vector2 position) {

        this.position = position;

    }

    public void render(SpriteBatch batch){

        Utils.drawTextureRegion(batch,
                Assets.instance.powerupAssets.powerUp,
                position, Constants.POWERUP_CENTER);

    }

    public Rectangle getCollider() {
        return new Rectangle(position.x, position.y, Constants.POWERUP_CENTER.x * 2, Constants.POWERUP_CENTER.y * 2);
    }
}
