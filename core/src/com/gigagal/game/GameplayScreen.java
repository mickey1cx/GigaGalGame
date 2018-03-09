package com.gigagal.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.gigagal.game.overlays.GigaGalHud;
import com.gigagal.game.overlays.ScreenControl;
import com.gigagal.game.utils.Assets;
import com.gigagal.game.utils.ChaseCam;
import com.gigagal.game.utils.Constants;
import com.gigagal.game.utils.LevelLoader;

import java.awt.Container;

import javax.swing.OverlayLayout;

/**
 * Created by mickey.1cx on 25.02.2018.
 */

public class GameplayScreen extends ScreenAdapter {

    public static final String TAG = GameplayScreen.class.getName();

    private SpriteBatch batch;
    private ExtendViewport viewport;

    private ShapeRenderer debugShapes;

    private Level level;

    private ChaseCam cam;

    private GigaGalHud gigaHud;
    private ScreenControl screenControl;

    @Override
    public void show() {

        Assets.instance.init();

        batch = new SpriteBatch();
        debugShapes = new ShapeRenderer();

        viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);

        level = LevelLoader.load("level_01", viewport);

        cam = new ChaseCam(viewport.getCamera(), level.getGigaGal());

        gigaHud = new GigaGalHud(level);

        if (onMobile()) {
            screenControl = new ScreenControl(level.getGigaGal());
            Gdx.input.setInputProcessor(screenControl);
        }

    }

    @Override
    public void resize(int width, int height) {

        if (onMobile()) {
            screenControl.viewport.update(width, height, true);
            screenControl.recalculateButtonPositions();
        }

        gigaHud.viewport.update(width, height, true);
        viewport.update(width, height, true);

    }

    @Override
    public void dispose() {

        Assets.instance.dispose();
        batch.dispose();

    }

    @Override
    public void render(float delta) {

        level.update(delta);

        cam.update(delta);

        viewport.apply();

        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r, Constants.BACKGROUND_COLOR.g, Constants.BACKGROUND_COLOR.b, Constants.BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(viewport.getCamera().combined);
        debugShapes.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();
        debugShapes.begin(ShapeRenderer.ShapeType.Line);

        level.render(batch, debugShapes);

        batch.end();
        debugShapes.end();

        gigaHud.render(batch);

        if (onMobile()) {
            screenControl.render(batch);
        }

    }

    private boolean onMobile() {
        return true || Gdx.app.getType() == ApplicationType.Android || Gdx.app.getType() == ApplicationType.iOS;
    }


}
