package com.gigagal.game.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by mickey.1cx on 25.02.2018.
 */

public class Constants {

    public static final Color BACKGROUND_COLOR = Color.SKY;

    public static final float WORLD_SIZE = 160.0f;

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
    public static final Vector2 GIGAGAL_CANNON = new Vector2(12, -7);

    public static final float GRAVITY = 1000.0f;

    public static final String PLATFORM = "platform";
    public static final int PLATFORM_EDGE = 8;

    public static final float CHASE_CAM_SPEED = 100.0f;

    //enemy
    public static final String ENEMY = "enemy";
    public static final int ENEMY_HEALTH = 5;
    public static final float ENEMY_HIT_RADIUS = 13.0f;
    public static final Vector2 ENEMY_CENTER = new Vector2(14, 22);
    public static final float ENEMY_MOVEMENT_SPEED = 10.0f;

    public static final float ENEMY_BOB_AMPLITUDE = 2.0f;
    public static final float ENEMY_BOB_PERIOD = 3.0f;
    public static final float ENEMY_RADIUS = 15.0f;

    public static Vector2 KNOCK_BACK_VELOCITY = new Vector2(200, 200);

    //bullets
    public static final String BULLET_SPRITE = "bullet";
    public static final Vector2 BULLET_CENTER = new Vector2(3, 2);
    public static final float BULLET_SPEED = 150.0f;

    public static final String EXPLOSION_LARGE = "explosion-large";
    public static final String EXPLOSION_MEDIUM = "explosion-medium";
    public static final String EXPLOSION_SMALL = "explosion-small";
    public static final Vector2 EXPLOSION_CENTER = new Vector2(8, 8);
    public static final float EXPLOSION_DURATION = 0.1f;

    public static final String POWERUP_SPRITE = "powerup";
    public static final Vector2 POWERUP_CENTER = new Vector2(7, 5);

    public static final int GIGAGAL_START_AMMO = 10;
    public static final int POWERUP_AMMO = 10;

    // Level Loading
    public static final String LEVEL_DIR = "levels";
    public static final String LEVEL_FILE_EXTENSION = "json";
    public static final String LEVEL_COMPOSITE = "composite";
    public static final String LEVEL_9PATCHES = "sImage9patchs";
    public static final String LEVEL_IMAGES = "sImages";
    public static final String LEVEL_ERROR_MESSAGE = "There was a problem loading the level.";
    public static final String LEVEL_IMAGENAME_KEY = "imageName";
    public static final String LEVEL_X_KEY = "x";
    public static final String LEVEL_Y_KEY = "y";
    public static final String LEVEL_WIDTH_KEY = "width";
    public static final String LEVEL_HEIGHT_KEY = "height";
    public static final String LEVEL_IDENTIFIER_KEY = "itemIdentifier";
    public static final String LEVEL_ENEMY_TAG = "Enemy";

    public static final String LEVEL_GIGAGAL_TAG = "standing-right";

    // Exit Portal
    public static final String EXIT_PORTAL_SPRITE_1 = "exit-portal-1";
    public static final String EXIT_PORTAL_SPRITE_2 = "exit-portal-2";
    public static final String EXIT_PORTAL_SPRITE_3 = "exit-portal-3";
    public static final String EXIT_PORTAL_SPRITE_4 = "exit-portal-4";
    public static final String EXIT_PORTAL_SPRITE_5 = "exit-portal-5";
    public static final String EXIT_PORTAL_SPRITE_6 = "exit-portal-6";
    public static final Vector2 EXIT_PORTAL_CENTER = new Vector2(31, 31);
    public static final String EXIT_PORTAL_TAG = "exit-portal";
    public static final float EXIT_PORTAL_FRAME_DURATION = 0.25f;

}
