package com.kronosad.games.runaway.entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.kronosad.games.runaway.gameplay.Level;
import com.kronosad.games.runaway.interfaces.IComponent;
import com.kronosad.games.runaway.interfaces.IRenderable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by russjr08 on 8/18/14.
 */
public abstract class Entity implements IRenderable {

    protected Vector2 pos = new Vector2(0, 0);

    protected boolean isGrounded = false;

    protected Level level;

    protected List<IComponent> components = new ArrayList<>();

    public Entity(Level level){
        this.level = level;
    }

    public abstract void update();

    public Vector2 getPos() {
        return pos;
    }

    public void setPos(Vector2 pos) { this.pos = pos; }

    public boolean isOnGround(){
        return isGrounded;
    }

    public void setGrounded(boolean grounded) { this.isGrounded = grounded; }

    public abstract Rectangle getBoundingBox();

    public Level getLevel() { return level; }

}
