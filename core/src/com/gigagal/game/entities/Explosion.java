package com.gigagal.game.entities;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.gigagal.game.utils.Assets;
import com.gigagal.game.utils.Constants;
import com.gigagal.game.utils.Utils;

/**
 * Created by mickey.1cx on 08.03.2018.
 */

public class Explosion {

    private Vector2 position;
    private long startTime;
    private Animation<TextureAtlas.AtlasRegion> animation;

    public Explosion(Vector2 position){

        this.position = position;
        startTime = TimeUtils.nanoTime();
        animation = Assets.instance.explosionAssets.explosion;

    }

    public void render(SpriteBatch batch){

        float renderTime = Utils.secondsSince(startTime);
        TextureRegion sprite = animation.getKeyFrame(renderTime);
        Utils.drawTextureRegion(batch, sprite, position, Constants.EXPLOSION_CENTER);

    }

    public boolean isFinished(){

        return animation.isAnimationFinished(Utils.secondsSince(startTime));

    }

}
