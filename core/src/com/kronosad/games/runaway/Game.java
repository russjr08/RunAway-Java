package com.kronosad.games.runaway;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kronosad.games.runaway.entities.Player;
import com.kronosad.games.runaway.gameplay.Level;

// TODO: Component system?
public class Game extends ApplicationAdapter {

    public static Boolean DEBUG = true;

    public static final int WIDTH = 800, HEIGHT = 600;


    private OrthographicCamera camera;

    private Level currentLevel;


	SpriteBatch batch;

    Texture sunTexture;
    Sprite sun;

    FPSLogger fps = new FPSLogger();


	@Override
	public void create () {
		batch = new SpriteBatch();

        sunTexture = new Texture("assets/props/sun.png");
        sun = new Sprite(sunTexture);
        sun.setPosition(WIDTH - sunTexture.getWidth(), HEIGHT - sunTexture.getHeight());

        camera = new OrthographicCamera();


        camera.setToOrtho(false, 800, 600);

        currentLevel = Level.load("level1", camera);

        currentLevel.addEntity(new Player(currentLevel));

	}

	@Override
	public void render () {
//        fps.log();

        Gdx.gl.glClearColor(0.196f, 0.6f, 0.8f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update();

        batch.setProjectionMatrix(camera.combined);

		batch.begin();

        currentLevel.render(batch);

        // Draw sunTexture
        sun.draw(batch);

        batch.end();
	}


    public void update(){
        currentLevel.update();
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            debug("Someone is closing me!");
            System.exit(0);
        }
    }

    public static void debug(String msg){
        if(!DEBUG) return;
        System.out.println("[DEBUG] " + msg);
    }

    @Override
    public void resize(int width, int height){
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }
}
