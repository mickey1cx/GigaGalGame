package com.gigagal.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.gigagal.game.utils.Assets;
import com.gigagal.game.utils.Constants;

/**
 * Created by mickey.1cx on 25.02.2018.
 */

public class GigaGal {

    public final static String TAG = GigaGal.class.getName();

    private Vector2 position, velocity;

    Facing facing;
    JumpState jumpState;
    WalkState walkState;
    Long jumpStartTime;

    public GigaGal() {

        position = new Vector2(20, Constants.GIGAGAL_EYE_HEIGHT);
        facing = Facing.RIGHT;
        jumpState = JumpState.FALLING;
        walkState = WalkState.STANDING;
        velocity = new Vector2(0, 0);

    }

    public void update(float delta) {

        velocity.y -= delta * Constants.GRAVITY;
        position.mulAdd(velocity, delta);

        if (jumpState != JumpState.JUMPING) {

            jumpState = JumpState.FALLING;

            if (position.y - Constants.GIGAGAL_EYE_HEIGHT < 0) {
                jumpState = JumpState.GROUNDED;
                position.y = Constants.GIGAGAL_EYE_HEIGHT;
                velocity.y = 0;
            }

        }

        Input input = Gdx.input;

        if (input.isKeyPressed(Keys.LEFT)) {
            moveLeft(delta);
        } else if (input.isKeyPressed(Keys.RIGHT)) {
            moveRight(delta);
        } else {
            walkState = WalkState.STANDING;
        }

        if (input.isKeyPressed(Keys.Z)) {

            switch (jumpState) {

                case GROUNDED:
                    startJump();
                    break;
                case JUMPING:
                    continueJump();

            }

        } else {
            endJump();
        }

    }

    private void endJump() {

        if (jumpState == JumpState.JUMPING) {
            jumpState = JumpState.FALLING;
        }

    }

    private void continueJump() {

        if (jumpState != JumpState.JUMPING) {
            return;
        }

        float jumpTime = MathUtils.nanoToSec * (TimeUtils.nanoTime() - jumpStartTime);
        if (jumpTime < Constants.GIGAGAL_JUMP_DURATION) {
            velocity.y = Constants.GIGAGAL_JUMP_SPEED;
        } else {
            endJump();
        }

    }

    private void startJump() {

        jumpState = JumpState.JUMPING;
        jumpStartTime = TimeUtils.nanoTime();
        continueJump();

    }

    private void moveLeft(float delta) {

        position.x -= delta * Constants.GIGAGAL_SPEED;
        facing = Facing.LEFT;
        walkState = walkState.WALKING;

    }

    private void moveRight(float delta) {

        position.x += delta * Constants.GIGAGAL_SPEED;
        facing = Facing.RIGHT;
        walkState = walkState.WALKING;

    }

    public void render(SpriteBatch batch) {

        TextureRegion player;
        if (facing == Facing.RIGHT) {
            player = (jumpState != JumpState.GROUNDED)
                    ? Assets.instance.gigaGalAssets.jumpingRight
                    : (walkState == WalkState.STANDING)
                    ? Assets.instance.gigaGalAssets.standingRight
                    : Assets.instance.gigaGalAssets.walkingRight;
        }
        else {
            player = (jumpState != JumpState.GROUNDED)
                    ? Assets.instance.gigaGalAssets.jumpingLeft
                    : (walkState == WalkState.STANDING)
                    ? Assets.instance.gigaGalAssets.standingLeft
                    : Assets.instance.gigaGalAssets.walkingLeft;
        }

        batch.draw(player.getTexture(),
                position.x - Constants.GIGAGAL_EYE_POSITION.x,
                position.y - Constants.GIGAGAL_EYE_POSITION.y,
                0, 0,
                player.getRegionWidth(),
                player.getRegionHeight(),
                1,1,0,
                player.getRegionX(),
                player.getRegionY(),
                player.getRegionWidth(),
                player.getRegionHeight(),
                false, false);

    }

    private enum Facing {LEFT, RIGHT};
    private enum JumpState {JUMPING, FALLING, GROUNDED};
    private enum WalkState {STANDING, WALKING};
}
