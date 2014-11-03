package ru.yondive.colorjumps;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Jumper extends GameObject {
	public enum JumperStates {
		JUMP, FALL, HIT
	}
	
	public static final float HEIGHT = 2f;
	public static final float WIDTH = 1f;
	
	public static final float START_VELOCITY_X = 4.5f;
	public static final float START_VELOCITY_Y = 35f;
	
	private Vector2 velocity;
	
	private JumperStates state;
	
	
	public Jumper(float x, float y) {
		super(x, y, 0, WIDTH, HEIGHT);
		velocity = new Vector2();
		state = JumperStates.FALL;
	}
	
	public boolean isSameColor(int color) {
		return this.color == color;
	}
	
	public boolean isOverlaps(Rectangle bounds) {
		return this.bounds.overlaps(bounds);
	}
	
	public void update(float delta, float accel, boolean isColorChanged) {
		if (isColorChanged) color = (color + 1) % 3;
		
		if (state != JumperStates.HIT && position.y <= World.FUND_HEIGHT) hitPlatform();
		if (state != JumperStates.HIT) velocity.x = accel * START_VELOCITY_X;
		
		velocity.add(World.GRAVITY.x * delta, World.GRAVITY.y * delta);
		position.add(velocity.x * delta, velocity.y * delta);
		
		if (state == JumperStates.JUMP && velocity.y < 0) {
			state = JumperStates.FALL;
		}
		
		if (position.x < - WIDTH / 2) position.x = World.WIDTH;
		if (position.x > World.WIDTH) position.x = 0;

		updateBounds();
	}
	
	public void hitPlatform() {
		if (state == JumperStates.FALL) {
			velocity.y = START_VELOCITY_Y;
			state = JumperStates.JUMP;
		}
	}
}
