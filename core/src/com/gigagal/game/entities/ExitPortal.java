package com.gigagal.game.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.gigagal.game.utils.Assets;
import com.gigagal.game.utils.Constants;
import com.gigagal.game.utils.Utils;

/**
 * Created by mickey.1cx on 09.03.2018.
 */

public class ExitPortal {

    Vector2 position;
    long startTime;
    Animation<AtlasRegion> animation;

    public ExitPortal(Vector2 position) {

        this.position = position;
        startTime = TimeUtils.nanoTime();
        animation = Assets.instance.portalAssets.exitPortal;

    }

    public void render(SpriteBatch batch){

        float renderTime = Utils.secondsSince(startTime);

        TextureRegion sprite = animation.getKeyFrame(renderTime);
        Utils.drawTextureRegion(batch, sprite, position, Constants.EXIT_PORTAL_CENTER);

    }


}
