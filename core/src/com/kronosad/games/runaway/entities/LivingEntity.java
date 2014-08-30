package com.kronosad.games.runaway.entities;

import com.kronosad.games.runaway.components.HealthBarComponent;
import com.kronosad.games.runaway.gameplay.Level;

/**
 * Created by russjr08 on 8/20/14.
 */
public abstract class LivingEntity extends Entity {
    protected float health = 10.0f;

    public static final float MOVE_SPEED = 128;
    public static final int JUMP_SPEED = 16;

    public LivingEntity(Level level) {
        super(level);
        components.add(new HealthBarComponent(this));
    }

    public float getHealth() {
        return health;
    }

    // Override this method if you don't want your LivingEntity to show a heatlh bar.
    public abstract boolean shouldDisplayHealth();


}
