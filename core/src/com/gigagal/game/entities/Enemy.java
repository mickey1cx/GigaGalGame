package com.gigagal.game.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.gigagal.game.utils.Assets;
import com.gigagal.game.utils.Constants;

/**
 * Created by mickey.1cx on 01.03.2018.
 */

public class Enemy {

    Platform platform;
    Vector2 position;

    public Enemy(Platform platform) {

        this.platform = platform;
        position = new Vector2(platform.left, platform.top);

    }

    public void update(float delta) {

    }

    public void render(SpriteBatch batch) {

        TextureRegion sprite = Assets.instance.enemyAssets.enemy;

        batch.draw(sprite.getTexture(),
                position.x - Constants.ENEMY_CENTER.x,
                position.y,
                0, 0,
                sprite.getRegionWidth(),
                sprite.getRegionHeight(),
                1,1,0,
                sprite.getRegionX(),
                sprite.getRegionY(),
                sprite.getRegionWidth(),
                sprite.getRegionHeight(),
                false, false);

    }

}
