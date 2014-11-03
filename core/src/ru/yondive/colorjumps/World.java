package ru.yondive.colorjumps;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class World {
	public static final Vector2 GRAVITY = new Vector2(0f, -45f);
	public static final float HEIGHT = 32f;
	public static final float WIDTH = 20f;
	
	public static final float FUND_HEIGHT = 2f;
	
	private Stage stage;
	private Jumper jumper;
	
	private List<Platform> platforms;
	
	private ColorJumps game;
	Random rand;
	
	
	public World(Batch batch, ColorJumps game) {
		this.platforms = new ArrayList<Platform>();
		this.game = game;
		this.rand = new Random();
		stage = new Stage(new StretchViewport(WIDTH, HEIGHT), batch);
		jumper = new Jumper(WIDTH / 2, FUND_HEIGHT);
		generate();
		stage.addActor(jumper);
	}
	
	public int update(float delta, float accel, boolean isColorChanged) {
		jumper.update(delta, accel, isColorChanged);
		
		/* Check platforms collisions */
		for (Platform platform : platforms) {
			platform.checkCollision(jumper);
		}
		
		/* Check game over */
		if (jumper.getY() < (stage.getCamera().position.y - HEIGHT / 2)) game.setScreen(new MainMenuScreen(game));
		
		/* Update camera Y */
		if (jumper.getY() > stage.getCamera().position.y) stage.getCamera().position.y = jumper.getY();
		
		return (int)jumper.getY();
	}
	
	public void draw() {
		stage.draw();
	}
	
	private void generate() {
		for (int i = 1; i < 100; i++) {
			platforms.add(new Platform(rand.nextFloat() * 15f, i * 10, rand.nextInt(3)));
		}
		
		for (Platform platform : platforms) {
			stage.addActor(platform);
		}
		
	}

}
