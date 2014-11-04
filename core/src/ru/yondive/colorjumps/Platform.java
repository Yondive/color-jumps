package ru.yondive.colorjumps;

public class Platform extends GameObject {
	public static final float HEIGHT = 0.5f;
	public static final float WIDTH = 5f;
	
	
	public Platform(float x, float y, int color) {
		super(x, y, color, WIDTH, HEIGHT);
	}
	
	public void checkCollision(Jumper jumper) {
		if (jumper.isSameColor(color) && bounds.overlaps(jumper.bounds)) {
			jumper.hitPlatform();
		}
	}
}
