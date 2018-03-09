package com.gigagal.game.entities;

import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.gigagal.game.utils.Assets;

/**
 * Created by mickey.1cx on 25.02.2018.
 */

public class Platform {

    float top, bottom, left, right;
    private NinePatch platform;

    public Platform(float left, float top, float width, float height) {

        this.top = top;
        this.bottom = top - height;
        this.left = left;
        this.right = left + width;

        platform = Assets.instance.platformAssets.platform;

    }

    public void render(SpriteBatch batch) {

        float width = right - left;
        float height = top - bottom;

        platform.draw(batch, left - 1, bottom - 1, width + 2, height + 2);

    }

    public float getTop() {
        return top;
    }

}
