package com.kronosad.games.runaway.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.kronosad.games.runaway.entities.LivingEntity;
import com.kronosad.games.runaway.interfaces.IComponent;

/**
 * Created by Russell on 8/30/2014.
 */
public class HealthBarComponent implements IComponent {
    private LivingEntity entity;

    public HealthBarComponent(LivingEntity entity) {
        this.entity = entity;
    }

    @Override
    public void update() {}

    @Override
    public void render(SpriteBatch batch) {
        if(entity.shouldDisplayHealth()){
            ShapeRenderer renderer = new ShapeRenderer();

            // OUTLINE
            renderer.begin(ShapeRenderer.ShapeType.Line);
            renderer.setColor(getColor());
            renderer.rect(entity.getPos().x - (entity.getBoundingBox().getWidth() / 2) - (entity.getBoundingBox().getWidth() / 2) / 2, entity.getPos().y + entity.getBoundingBox().getHeight() + 4, 80, 8);
            renderer.end();

            // Shade in the health
            renderer.begin(ShapeRenderer.ShapeType.Filled);
            renderer.rect(entity.getPos().x - (entity.getBoundingBox().getWidth() / 2) - 8, entity.getPos().y + entity.getBoundingBox().getHeight() + 4, entity.getHealth() * 8, 8);
            renderer.end();
        }
    }

    private Color getColor(){
        if(entity.getHealth() >= 6) {
            return Color.GREEN;
        }

        if(entity.getHealth() >= 4) {
            return Color.ORANGE;
        }

        if(entity.getHealth() >= 1){
            return Color.RED;
        }

        return Color.WHITE;
    }

    @Override
    public void run() {}
}
