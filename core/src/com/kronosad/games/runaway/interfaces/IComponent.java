package com.kronosad.games.runaway.interfaces;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by russjr08 on 8/27/14.
 */
public interface IComponent {

    public void update(); // Called at implementing class' update (if any) method.
    public void render(SpriteBatch batch); // Called at implementing class' render (if any) method.

    // For one time operations, will be called most likely at implementing class' construction.
    public void run();

}
