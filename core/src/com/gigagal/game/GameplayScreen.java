package com.gigagal.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.gigagal.game.utils.Assets;
import com.gigagal.game.utils.Constants;

/**
 * Created by mickey.1cx on 25.02.2018.
 */

public class GameplayScreen extends ScreenAdapter {

    public static final String TAG = GameplayScreen.class.getName();

    SpriteBatch batch;
    ExtendViewport viewport;

    @Override
    public void show() {

        Assets.instance.init();

        batch = new SpriteBatch();

        viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);

    }

    @Override
    public void resize(int width, int height) {

        viewport.update(width, height, true);

    }

    @Override
    public void dispose() {

        Assets.instance.dispose();
        batch.dispose();

    }

    @Override
    public void render(float delta) {

        viewport.apply();

        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, Constants.BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();

        TextureRegion standingRight = Assets.instance.gigaGalAssets.standingRight;

        batch.draw(standingRight.getTexture(), 0,0, 0, 0, standingRight.getRegionWidth(), standingRight.getRegionHeight(),1,1,0,
                standingRight.getRegionX(), standingRight.getRegionY(), standingRight.getRegionWidth(), standingRight.getRegionHeight(), false, false);

        batch.end();


    }


}
