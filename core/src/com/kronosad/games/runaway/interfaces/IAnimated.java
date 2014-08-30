package com.kronosad.games.runaway.interfaces;

/**
 * Created by russjr08 on 8/20/14.
 */
public interface IAnimated {

    public enum AnimationState {
        IDLE, WALKING, JUMPING, RUNNING
    }

    public enum AnimationDirection {
        NORTH, SOUTH, EAST, WEST
    }

    public AnimationState getAnimationState();

    public AnimationDirection getAnimationDirection();

}
