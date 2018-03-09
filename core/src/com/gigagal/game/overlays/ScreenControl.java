package com.gigagal.game.overlays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gigagal.game.entities.GigaGal;
import com.gigagal.game.utils.Assets;
import com.gigagal.game.utils.Constants;
import com.gigagal.game.utils.Utils;

/**
 * Created by mickey.1cx on 09.03.2018.
 */

public class ScreenControl extends InputAdapter {

    public static final String TAG = ScreenControl.class.getName();

    public final Viewport viewport;
    public GigaGal gigaGal;
    private Vector2 moveLeftCenter = new Vector2();
    private Vector2 moveRightCenter = new Vector2();
    private Vector2 shootCenter = new Vector2();
    private Vector2 jumpCenter = new Vector2();

    private int moveLeftPointer;
    private int moveRightPointer;
    private int jumpPointer;
    
    
    public ScreenControl(GigaGal gigaGal) {

        this.gigaGal = gigaGal;
        this.viewport = new ExtendViewport(
                Constants.ONSCREEN_CONTROLS_VIEWPORT_SIZE,
                Constants.ONSCREEN_CONTROLS_VIEWPORT_SIZE);

        recalculateButtonPositions();        
        
    }

    public void render(SpriteBatch batch) {

        if (!Gdx.input.isTouched(jumpPointer)) {
            gigaGal.jumpButtonPressed = false;
            jumpPointer = 0;
        }
        if (!Gdx.input.isTouched(moveLeftPointer)) {
            gigaGal.leftButtonPressed = false;
            moveLeftPointer = 0;
        }
        if (!Gdx.input.isTouched(moveRightPointer)) {
            gigaGal.rightButtonPressed = false;
            moveRightPointer = 0;
        }



        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        Utils.drawTextureRegion(
                batch,
                Assets.instance.touchTargets.buttonLeft,
                moveLeftCenter,
                Constants.BUTTON_CENTER
        );

        Utils.drawTextureRegion(
                batch,
                Assets.instance.touchTargets.buttonRight,
                moveRightCenter,
                Constants.BUTTON_CENTER
        );

        // TODO: Render the shoot and jump buttons, using the shootCenter and jumpCenter defined below

        Utils.drawTextureRegion(
                batch,
                Assets.instance.touchTargets.buttonFire,
                shootCenter,
                Constants.BUTTON_CENTER
        );

        Utils.drawTextureRegion(
                batch,
                Assets.instance.touchTargets.buttonJump,
                jumpCenter,
                Constants.BUTTON_CENTER
        );

        batch.end();
    }

    public void recalculateButtonPositions() {

        moveLeftCenter.set(Constants.BUTTON_RADIUS * 3 / 4, Constants.BUTTON_RADIUS);
        moveRightCenter.set(Constants.BUTTON_RADIUS * 2, Constants.BUTTON_RADIUS * 3 / 4);


        // TODO: Set shootCenter and jumpCenter, mirroring the positions of the move buttons
        jumpCenter.set(viewport.getWorldWidth() - Constants.BUTTON_RADIUS * 3 / 4, Constants.BUTTON_RADIUS);
        shootCenter.set(viewport.getWorldWidth() - Constants.BUTTON_RADIUS * 2, Constants.BUTTON_RADIUS * 3 / 4);

    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Vector2 viewportPosition = viewport.unproject(new Vector2(screenX, screenY));

        if (viewportPosition.dst(shootCenter) < Constants.BUTTON_RADIUS) {

            // TODO: Call shoot() on GigaGal
            gigaGal.shoot();

        } else if (viewportPosition.dst(jumpCenter) < Constants.BUTTON_RADIUS) {

            // TODO: Save the jumpPointer and set gigaGal.jumpButtonPressed = true
            jumpPointer = pointer;
            gigaGal.jumpButtonPressed = true;

        } else if (viewportPosition.dst(moveLeftCenter) < Constants.BUTTON_RADIUS) {

            // TODO: Save the moveLeftPointer, and set gigaGal.leftButtonPressed = true
            moveLeftPointer = pointer;
            gigaGal.leftButtonPressed = true;

        } else if (viewportPosition.dst(moveRightCenter) < Constants.BUTTON_RADIUS) {

            // TODO: Save the moveRightPointer, and set gigaGal.rightButtonPressed = true
            moveRightPointer = pointer;
            gigaGal.rightButtonPressed = true;


        }

        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        Vector2 viewportPosition = viewport.unproject(new Vector2(screenX, screenY));

        if (pointer == moveLeftPointer && viewportPosition.dst(moveRightCenter) < Constants.BUTTON_RADIUS) {

            gigaGal.leftButtonPressed = false;
            gigaGal.rightButtonPressed = true;
            moveLeftPointer = 0;
            moveRightPointer = pointer;
        }

        if (pointer == moveRightPointer && viewportPosition.dst(moveLeftCenter) < Constants.BUTTON_RADIUS) {

            gigaGal.leftButtonPressed = true;
            gigaGal.rightButtonPressed = false;
            moveLeftPointer = pointer;
            moveRightPointer = 0;

        }

        return super.touchDragged(screenX, screenY, pointer);
    }


}
