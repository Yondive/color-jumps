package ru.yondive.colorjumps;

public class Platform extends GameObject {
	public static final float HEIGHT = 1f;
	public static final float WIDTH = 5f;
	
	
	public Platform(float x, float y, int color) {
		super(x, y, color, WIDTH, HEIGHT);
	}
	
	public void checkCollision(Jumper jumper) {
		if (jumper.isSameColor(color) && jumper.isOverlaps(bounds)) {
			jumper.hitPlatform();
		}
	}
}
