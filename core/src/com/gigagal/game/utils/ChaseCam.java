package com.gigagal.game.utils;

import com.badlogic.gdx.graphics.Camera;
import com.gigagal.game.entities.GigaGal;

/**
 * Created by mickey.1cx on 27.02.2018.
 */

public class ChaseCam {

    private Camera camera;
    private GigaGal target;

    public ChaseCam(Camera camera, GigaGal target) {

        this.camera = camera;
        this.target = target;

    }

    public void update(){

        camera.position.set(target.getPosition(), 0);

    }


}
