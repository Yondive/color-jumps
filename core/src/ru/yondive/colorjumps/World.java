package ru.yondive.colorjumps;

import java.util.ArrayList;
import java.util.Random;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class World {
	public static final Vector2 GRAVITY = new Vector2(0f, -46f);
	public static final float HEIGHT = 32f;
	public static final float WIDTH = 20f;

	public static final float FUND_HEIGHT = 3.2f;
	public static final float MAX_PLATFORM_SPACE = 11f;
	
	private Stage stage;
	private Jumper jumper;
	
	private ArrayList<Platform> platforms;
	private ArrayList<Enemy> enemies;
	
	private Random rand;
	private int score;
	private float lastMainPlatformY = HEIGHT;
	
	public World(Batch batch, ColorJumps game) {
		this.platforms = new ArrayList<Platform>();
		this.enemies = new ArrayList<Enemy>();
		this.rand = new Random();
		stage = new Stage(new StretchViewport(WIDTH, HEIGHT), batch);
		jumper = new Jumper(WIDTH / 2, FUND_HEIGHT);
		stage.addActor(jumper);
		score = 0;
		stage.addActor(new BackgroundObject(0, 0, WIDTH, FUND_HEIGHT, Assets.bgFundTRD));
		for (int i = 0; i < 10; i++) {
			addPlatform(MAX_PLATFORM_SPACE + i * HEIGHT / MAX_PLATFORM_SPACE, rand.nextInt(2), 0);
		}
	}
	
	public int getScore() {
		return score;
	}
	
	
	public int update(float delta, float accel, boolean isColorChanged) {
		jumper.update(delta, accel, isColorChanged, stage.getCamera().position.y);
		stage.act();
		for (Platform p : platforms) {
			p.update(delta);
		}
		
		score = Math.max(score, (int)jumper.getY());
		
		/* Check platforms collisions */
		float lowBoard = stage.getCamera().position.y - HEIGHT / 2;
		for (int i = 0; i < platforms.size(); i++) {
			if (platforms.get(i).position.y < lowBoard) {
				platforms.remove(i--);
			}
			else {
				if (platforms.get(i).checkCollision(jumper))
					jumper.hitPlatform();;
			}
		}
		
		for (int i = 0; i < enemies.size(); i++) {
			if (enemies.get(i).position.y < lowBoard) {
				enemies.remove(i--);
			}
			else {
				if (enemies.get(i).checkCollision(jumper)) {
					return -1;
				}
			}
		}
		
		/* Check game over */
		if (jumper.getY() < (stage.getCamera().position.y - HEIGHT / 2)) return -1;
		
		/* Update camera Y */
		if (jumper.getY() > stage.getCamera().position.y) {
			stage.getCamera().position.y = jumper.getY();
			generateObjects();
		}
		
		return score;
	}
	
	private void generateObjects() {
		//add main platform
		if (jumper.getY() - lastMainPlatformY + HEIGHT >= MAX_PLATFORM_SPACE) {
			lastMainPlatformY += MAX_PLATFORM_SPACE;
			addPlatform(lastMainPlatformY, rand.nextInt(2), rand.nextInt(2));
		}
		
		if (rand.nextFloat() > 0.98f) {
			addPlatform(lastMainPlatformY + rand.nextInt((int)MAX_PLATFORM_SPACE), rand.nextInt(2), rand.nextInt(2));
		}
		
		if (rand.nextFloat() > 0.99f) {
			addEnemy(lastMainPlatformY + rand.nextInt((int)MAX_PLATFORM_SPACE), rand.nextInt(3));
		}
	}

	private void addPlatform(float height, int color, int type) {
		Platform newPlatform = new Platform(rand.nextFloat() * 15f, height, color, type);
		platforms.add(newPlatform);
		stage.addActor(newPlatform);
	}
	
	private void addEnemy(float height, int color) {
		Enemy newEnemy = new Enemy(rand.nextFloat() * WIDTH - Enemy.WIDTH, height, color);
		enemies.add(newEnemy);
		stage.addActor(newEnemy);
	}
	
	public void draw() {
		stage.draw();
	}
}
