package com.gigagal.game.utils;

/**
 * Created by mickey.1cx on 27.02.2018.
 */

public class Enums {

    public enum Direction {
        LEFT, RIGHT
    }

    public enum JumpState {
        JUMPING,
        FALLING,
        RECOILING,
        GROUNDED
    }

    public enum WalkState {
        STANDING,
        WALKING
    }

}
