package com.kronosad.games.runaway.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.kronosad.games.runaway.Game;
import com.kronosad.games.runaway.entities.Entity;
import com.kronosad.games.runaway.entities.Player;
import com.kronosad.games.runaway.interfaces.IRenderable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by russjr08 on 8/19/14.
 */
public class Level implements IRenderable {

    protected TiledMap map;
    protected TiledMapRenderer mapRenderer;
    protected OrthographicCamera camera;
    protected Vector2 spawnpoint;

    private List<Entity> entities;

    private List<Rectangle> collisions = new ArrayList<>();

    protected ShapeRenderer shapeRenderer = new ShapeRenderer();

    private Level(TiledMap map, OrthographicCamera camera){
        this.map = map;
        mapRenderer = new OrthogonalTiledMapRenderer(this.map);
        this.camera = camera;
        entities = new ArrayList<>();

        loadCollisionBoxes();
    }

    public static Level load(String name, OrthographicCamera camera){
        Gdx.graphics.setTitle("Current Level: " + name);
        return new Level(new TmxMapLoader().load("assets/level/" + name + ".tmx"), camera);
    }

    private void loadCollisionBoxes(){
        MapLayer collisionLayer = map.getLayers().get("collisions");

        Game.debug("Inflating Collision Rectangles / Tile Data");

        for(RectangleMapObject object : collisionLayer.getObjects().getByType(RectangleMapObject.class)){
            if(object.getProperties().containsKey("spawnpoint")){
                spawnpoint = new Vector2(object.getRectangle().x, object.getRectangle().y);
                Game.debug("Set Spawn Point to: " + spawnpoint.toString());
            }
            if(!object.getProperties().containsKey("collision")) continue;
            Game.debug("Added Collision Rectangle at: " + object.getRectangle().toString());
            collisions.add(object.getRectangle());
        }
        Game.debug("Added " + collisions.size() + " Collision Rectangles");
    }

    @Override
    public void render(SpriteBatch batch){
        batch.end();
        mapRenderer.setView(camera);
        mapRenderer.render();
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLUE);
        for(Rectangle rect : collisions){
            shapeRenderer.rect(rect.x, rect.y, rect.getWidth(), rect.getHeight());
        }

        shapeRenderer.end();
        batch.begin();
        drawDebugInformation(batch);
        for(Entity entity : entities){
            entity.render(batch);
        }
    }

    public void drawDebugInformation(SpriteBatch batch){
        Game.drawString("Collisions: " + collisions.size(), batch, 0, Gdx.graphics.getHeight() - 15);
        Game.drawString("Player Pos: " + entities.get(0).getPos(), batch, 0, Gdx.graphics.getHeight() - 30);
    }

    public void update(){
        for(Entity entity : entities) {
            entity.update();
        }
    }

    public void addEntity(Entity entity){
        entities.add(entity);
        if(entity instanceof Player){
            entity.setPos(spawnpoint);
        }
    }

    public void removeEntity(Entity entity){
        entities.remove(entity);
    }

    public boolean collidesWithTile(Entity entity, float posX, float posY){
        Rectangle box = new Rectangle(posX, posY, entity.getBoundingBox().getWidth(), entity.getBoundingBox().getHeight());

        for(Rectangle rectangle : collisions){
            if(rectangle.overlaps(box)){
                return true;
            }
        }

        return false;
    }

}
