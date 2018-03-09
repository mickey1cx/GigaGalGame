package com.gigagal.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.gigagal.game.Level;
import com.gigagal.game.utils.Assets;
import com.gigagal.game.utils.Constants;
import com.gigagal.game.utils.Enums;
import com.gigagal.game.utils.Enums.Direction;
import com.gigagal.game.utils.Enums.JumpState;
import com.gigagal.game.utils.Enums.WalkState;
import com.gigagal.game.utils.Utils;

/**
 * Created by mickey.1cx on 25.02.2018.
 */

public class GigaGal {

    public final static String TAG = GigaGal.class.getName();

    private Vector2 position, velocity, lastPosition;

    private Direction direction;
    private JumpState jumpState;
    private WalkState walkState;
    private long jumpStartTime;
    private long walkStartTime;

    private Level level;

    private Vector2 startPosition;
    private int ammoCounter;

    public GigaGal(Level level, Vector2 startPosition) {

        this.level = level;
        this.startPosition = startPosition;

        init();

    }

    private void init() {

        position = new Vector2(startPosition);
        direction = Direction.RIGHT;
        jumpState = JumpState.FALLING;
        walkState = WalkState.STANDING;
        velocity = new Vector2();
        lastPosition = new Vector2(position);
        ammoCounter = Constants.GIGAGAL_START_AMMO;

    }

    public void update(float delta) {

        lastPosition.set(position);

        if (position.y < Constants.KILL_HEIGHT) {
            init();
        }

        velocity.y -= delta * Constants.GRAVITY;
        position.mulAdd(velocity, delta);

        if (jumpState != JumpState.JUMPING) {

            if (jumpState != JumpState.RECOILING) {
                jumpState = JumpState.FALLING;
            }

            if (position.y - Constants.GIGAGAL_EYE_HEIGHT < 0) {
//                jumpState = JumpState.GROUNDED;
//                position.y = Constants.GIGAGAL_EYE_HEIGHT;
//                velocity.y = 0;
            } else {

                for (Platform platform : level.getPlatforms()) {
                    if (landedOnPlatform(platform)) {
                        jumpState = JumpState.GROUNDED;
                        velocity.y = 0;
                        velocity.x = 0;
                        position.y = platform.top + Constants.GIGAGAL_EYE_HEIGHT;
                        break;
                    }
                }

            }

        }

        Input input = Gdx.input;

        if (jumpState != JumpState.RECOILING) {

            if (input.isKeyPressed(Keys.LEFT)) {
                moveLeft(delta);
            } else if (input.isKeyPressed(Keys.RIGHT)) {
                moveRight(delta);
            } else {
                walkState = WalkState.STANDING;
            }
        }

        if (input.isKeyJustPressed(Keys.X)) {
            makeShoot();
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

    private void makeShoot() {

        if (ammoCounter == 0) {
            return;
        }

        Vector2 bulletPosition = new Vector2(position);
        bulletPosition.x += (direction == Direction.RIGHT) ? Constants.GIGAGAL_CANNON.x : -Constants.GIGAGAL_CANNON.x;
        bulletPosition.y += Constants.GIGAGAL_CANNON.y;
        level.addBullet(bulletPosition, direction);
        ammoCounter--;

    }

    private boolean landedOnPlatform(Platform platform) {

        boolean leftFootIn = false;
        boolean rightFootIn = false;
        boolean straddle = false;

        if (lastPosition.y - Constants.GIGAGAL_EYE_HEIGHT >= platform.top
                && position.y - Constants.GIGAGAL_EYE_HEIGHT < platform.top) {

            float leftFoot = position.x - Constants.GIGAGAL_STANCE_WIDTH / 2;
            float rightFoot = position.x + Constants.GIGAGAL_STANCE_WIDTH / 2;

            leftFootIn = (platform.left < leftFoot && platform.right > leftFoot);
            rightFootIn = (platform.left < rightFoot && platform.right > rightFoot);

            straddle = (platform.left > leftFoot && platform.right < rightFoot);
        }

        return leftFootIn || rightFootIn || straddle;

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

        if (jumpState == JumpState.GROUNDED && walkState != WalkState.WALKING) walkStartTime = TimeUtils.nanoTime();

        position.x -= delta * Constants.GIGAGAL_SPEED;
        direction = Direction.LEFT;
        walkState = WalkState.WALKING;

    }

    private void moveRight(float delta) {

        if (jumpState == JumpState.GROUNDED && walkState != WalkState.WALKING) walkStartTime = TimeUtils.nanoTime();

        position.x += delta * Constants.GIGAGAL_SPEED;
        direction = Direction.RIGHT;
        walkState = WalkState.WALKING;

    }

    public void render(SpriteBatch batch, ShapeRenderer debugShapes) {

        TextureRegion player;

        float walkTime = 0;

        if (jumpState == JumpState.GROUNDED && walkState == WalkState.WALKING) {
            //walkTime = MathUtils.nanoToSec * (TimeUtils.nanoTime() - walkStartTime);
            walkTime = Utils.secondsSince(walkStartTime);
        }

        if (direction == Direction.RIGHT) {
            player = (jumpState != JumpState.GROUNDED)
                    ? Assets.instance.gigaGalAssets.jumpingRight
                    : (walkState == WalkState.STANDING)
                    ? Assets.instance.gigaGalAssets.standingRight
                    : Assets.instance.gigaGalAssets.walkingRightAnimation.getKeyFrame(walkTime);
        }
        else {
            player = (jumpState != JumpState.GROUNDED)
                    ? Assets.instance.gigaGalAssets.jumpingLeft
                    : (walkState == WalkState.STANDING)
                    ? Assets.instance.gigaGalAssets.standingLeft
                    : Assets.instance.gigaGalAssets.walkingLeftAnimation.getKeyFrame(walkTime);
        }

        Utils.drawTextureRegion(batch, player,
                position.x - Constants.GIGAGAL_EYE_POSITION.x,
                position.y - Constants.GIGAGAL_EYE_POSITION.y);

//        debugShapes.rect(
//                position.x  - Constants.GIGAGAL_STANCE_WIDTH / 2,
//                position.y - Constants.GIGAGAL_HEIGHT / 2,
//                Constants.GIGAGAL_STANCE_WIDTH, Constants.GIGAGAL_HEIGHT);

    }

    public Vector2 getPosition() {
        return position;
    }

    public Rectangle getCollider() {

        return new Rectangle(
                position.x  - Constants.GIGAGAL_STANCE_WIDTH / 2,
                position.y - Constants.GIGAGAL_HEIGHT / 2,
                Constants.GIGAGAL_STANCE_WIDTH, Constants.GIGAGAL_HEIGHT);
    }

    public void applyKnockback(Direction knockbackDirection) {

        Vector2 knockbackVelocity = Constants.KNOCK_BACK_VELOCITY;
        velocity.y += knockbackVelocity.y;
        velocity.x += (knockbackDirection == Direction.RIGHT) ? knockbackVelocity.x : -knockbackVelocity.x;
        jumpState = JumpState.RECOILING;
    }

    public void increaseAmmo(int powerupAmmo) {

        ammoCounter += powerupAmmo;

    }
}
