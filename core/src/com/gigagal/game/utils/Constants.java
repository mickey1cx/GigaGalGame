package com.gigagal.game.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by mickey.1cx on 25.02.2018.
 */

public class Constants {

    public static final Color BACKGROUND_COLOR = Color.SKY;

    public static final float WORLD_SIZE = 128.0f;

    public static final float KILL_HEIGHT = -100.0f;

    public static final String TEXTURE_ATLAS = "images/gigagal.pack.atlas";

    public static final String STANDING_RIGHT = "standing-right";
    public static final String STANDING_LEFT = "standing-left";
    public static final String JUMPING_RIGHT = "jumping-right";
    public static final String JUMPING_LEFT = "jumping-left";

    public static final String WALK_1_RIGHT = "walk-1-right";
    public static final String WALK_2_RIGHT = "walk-2-right";
    public static final String WALK_3_RIGHT = "walk-3-right";
    public static final String WALK_1_LEFT = "walk-1-left";
    public static final String WALK_2_LEFT = "walk-2-left";
    public static final String WALK_3_LEFT = "walk-3-left";
    public static final float GIGAGAL_WALK_DURATION = 0.25f;

    public static final Vector2 GIGAGAL_EYE_POSITION = new Vector2(16, 24);
    public static final float GIGAGAL_EYE_HEIGHT = 15.0f;

    public static final float GIGAGAL_SPEED = 64;
    public static final float GIGAGAL_JUMP_SPEED = 128;
    public static final float GIGAGAL_JUMP_DURATION = 0.3f;
    public static final float GIGAGAL_HEIGHT = 23.0f;
    public static final float GIGAGAL_STANCE_WIDTH = 6.0f;

    public static final float GRAVITY = 1000.0f;

    public static final String PLATFORM = "platform";
    public static final int PLATFORM_EDGE = 8;

    public static final float CHASE_CAM_SPEED = 100.0f;

    public static final String ENEMY = "enemy";
    public static final Vector2 ENEMY_CENTER = new Vector2(14, 22);
    public static final float ENEMY_MOVEMENT_SPEED = 10.0f;

    public static final float ENEMY_BOB_AMPLITUDE = 2.0f;
    public static final float ENEMY_BOB_PERIOD = 3.0f;
    public static final float ENEMY_RADIUS = 15.0f;

    public static Vector2 KNOCK_BACK_VELOCITY = new Vector2(200, 200);

}
