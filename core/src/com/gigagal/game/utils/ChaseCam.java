package com.gigagal.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.gigagal.game.entities.GigaGal;

/**
 * Created by mickey.1cx on 27.02.2018.
 */

public class ChaseCam {

    private Camera camera;
    private GigaGal target;
    private boolean followTarget;

    public ChaseCam(Camera camera, GigaGal target) {

        this.camera = camera;
        this.target = target;
        followTarget = true;

    }

    public void update(float delta){

        if (Gdx.input.isKeyPressed(Keys.SPACE)) {
            followTarget = !followTarget;
        }

        if (followTarget) {
            camera.position.set(target.getPosition(), 0);
        } else {

            if (Gdx.input.isKeyPressed(Keys.A)) {
                camera.position.x -= delta * Constants.CHASE_CAM_SPEED;
            } else if (Gdx.input.isKeyPressed(Keys.D)) {
                camera.position.x += delta * Constants.CHASE_CAM_SPEED;
            }

            if (Gdx.input.isKeyPressed(Keys.S)) {
                camera.position.y -= delta * Constants.CHASE_CAM_SPEED;
            } else if (Gdx.input.isKeyPressed(Keys.W)) {
                camera.position.y += delta * Constants.CHASE_CAM_SPEED;
            }

        }

    }


}
