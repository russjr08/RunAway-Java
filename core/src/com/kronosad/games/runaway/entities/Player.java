package com.kronosad.games.runaway.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.kronosad.games.runaway.components.GravityComponent;
import com.kronosad.games.runaway.gameplay.Level;
import com.kronosad.games.runaway.interfaces.IComponent;

/**
 * Created by russjr08 on 8/20/14.
 */
public class Player extends LivingEntity /** implements IAnimated **/ {

    private Sprite sprite;
    private boolean jumping = false;
    private float speed = 0f;

    private ShapeRenderer shapeRenderer = new ShapeRenderer();


    public Player(Level level) {
        super(level);
        Texture texture = new Texture("assets/character/player-right.png");
        sprite = new Sprite(texture);

        components.add(new GravityComponent(this));
    }

    @Override
    public boolean shouldDisplayHealth() {
        return true;
    }

    @Override
    public void update() {
        if(pos == null) return;

        sprite.setPosition(pos.x, pos.y);

        for(IComponent component : components) {
            component.update();
        }

        if(jumping && speed != 0f){
            if(level.collidesWithTile(this, pos.x, pos.y + MOVE_SPEED * speed * Gdx.graphics.getDeltaTime())){
                speed = 0f;
                return;
            }
            pos.y = pos.y + MOVE_SPEED * speed * Gdx.graphics.getDeltaTime();
            speed -= 0.5f;
        }

        // Polling Input
        if (Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if(level.collidesWithTile(this, pos.x - 1, pos.y)) return;
            pos.x = pos.x - (MOVE_SPEED * Gdx.graphics.getDeltaTime());
            sprite.setTexture(new Texture("assets/character/player-left.png"));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if(level.collidesWithTile(this, pos.x + 1, pos.y)) return;
            pos.x = pos.x + (MOVE_SPEED * Gdx.graphics.getDeltaTime());
            sprite.setTexture(new Texture("assets/character/player-right.png"));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            jump();
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.end();

        for(IComponent component : components) {
            component.render(batch);
        }

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(getBoundingBox().x, getBoundingBox().y, getBoundingBox().getWidth(), getBoundingBox().getHeight());
        shapeRenderer.end();
        batch.begin();
        sprite.draw(batch);

    }

    public void jump(){
        if(isOnGround()){
            pos.y = pos.y + (MOVE_SPEED * JUMP_SPEED * Gdx.graphics.getDeltaTime());
            jumping = true;
            speed = 8.5f;
        }
    }

    @Override
    public Rectangle getBoundingBox() {
        return sprite.getBoundingRectangle();
    }



}

