package com.gigagal.game.overlays;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gigagal.game.Level;
import com.gigagal.game.utils.Constants;

/**
 * Created by mickey.1cx on 09.03.2018.
 */

public class GigaGalHud {

    public Viewport viewport;
    BitmapFont font;
    Level level;

    public GigaGalHud(Level level) {

        viewport = new ExtendViewport(Constants.HUD_VIEWPORT_SIZE, Constants.HUD_VIEWPORT_SIZE);
        font = new BitmapFont();
        this.level = level;

    }

    public void render(SpriteBatch batch){

        viewport.apply();

        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();

//      font.draw(batch, "same text", 10, 10);
        font.draw(batch, Constants.HUD_AMMO_LABEL + level.getGigaGal().getAmmoCounter(),
                Constants.HUD_MARGIN, viewport.getWorldHeight() - Constants.HUD_MARGIN);
        font.draw(batch, Constants.HUD_SCORE_LABEL + level.getLevelScore(),
                Constants.HUD_MARGIN, viewport.getWorldHeight() - Constants.HUD_MARGIN * 2);

        batch.end();

    }


}
