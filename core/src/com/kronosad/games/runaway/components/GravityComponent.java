package com.kronosad.games.runaway.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.kronosad.games.runaway.entities.LivingEntity;
import com.kronosad.games.runaway.interfaces.IComponent;

/**
 * Created by russjr08 on 8/27/14.
 */
public class GravityComponent implements IComponent {

    private LivingEntity entity;

    public GravityComponent(LivingEntity entity){
        this.entity = entity;
    }

    @Override
    public void update() {
        if(!checkNextPosition()){
            entity.setGrounded(false);
            entity.setPos(new Vector2(entity.getPos().x, entity.getPos().y - (LivingEntity.MOVE_SPEED * Gdx.graphics.getDeltaTime())));

        }else{
            entity.setGrounded(true);
        }
    }

    private boolean checkNextPosition(){
        return entity.getLevel().collidesWithTile(entity, entity.getPos().x, entity.getPos().y - 1);
    }

    @Override
    public void render(SpriteBatch batch) {}

    @Override
    public void run() {

    }
}
