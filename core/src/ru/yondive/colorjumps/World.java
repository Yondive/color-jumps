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
	
	public static final float LEVEL_HEIGHT = 36f;
	public static final float MAX_PLATFORM_SPACE = 12f;
	
	private Stage stage;
	private Jumper jumper;
	
	private List<List<Platform>> platforms;
	
	private ColorJumps game;
	private Random rand;
	private int level;
	private int score;
	
	public World(Batch batch, ColorJumps game) {
		this.platforms = new ArrayList<List<Platform>>();
		this.game = game;
		this.rand = new Random();
		stage = new Stage(new StretchViewport(WIDTH, HEIGHT), batch);
		jumper = new Jumper(WIDTH / 2, FUND_HEIGHT);
		stage.addActor(jumper);
		score = 0;
	}
	
	public int getScore() {
		return score;
	}
	
	public int update(float delta, float accel, boolean isColorChanged) {
		jumper.update(delta, accel, isColorChanged);
		
		score = Math.max(score, (int)jumper.getY());
		/* Check platforms collisions */
		for (int i = 0; i < Math.min(3, level + 1); i++) {
			platforms.get(i).size();
			for (Platform p : platforms.get(i)) {
				p.checkCollision(jumper);
			}
		}
		
		/* Check game over */
		if (jumper.getY() < (stage.getCamera().position.y - HEIGHT / 2)) game.setScreen(new MainMenuScreen(game));
		
		/* Update camera Y */
		if (jumper.getY() > stage.getCamera().position.y) {
			stage.getCamera().position.y = jumper.getY();
		}
		
		return score;
	}
	
	public void draw() {
		stage.draw();
	}
	
	private void generateNewPart(int level) {
		if (level >= 3) {
			for (Platform p :platforms.get(0)) {
				stage.getActors().removeValue(p, true);
			}
			platforms.remove(0);
		}
		
		platforms.add(new ArrayList<Platform>());
		for (int i = 4 * level; i < 4 * (level + 1); i++) {
			platforms.get(platforms.size()-1).add(new Platform(rand.nextFloat() * 15f, i * MAX_PLATFORM_SPACE, rand.nextInt(3)));
			if (rand.nextFloat() > 0.5f)
				platforms.get(platforms.size()-1).add(new Platform(rand.nextFloat() * 15f, (i - 0.5f) * MAX_PLATFORM_SPACE, rand.nextInt(3)));
		}
		
		for (Platform platform : platforms.get(platforms.size()-1)) {
			stage.addActor(platform);
		}
	}

}
