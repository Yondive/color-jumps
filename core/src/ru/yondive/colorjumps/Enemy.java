package ru.yondive.colorjumps;

public class Enemy  extends GameObject{
	public static final float HEIGHT = 2f;
	public static final float WIDTH = 2f;
	
	
	public Enemy(float x, float y, int color) {
		super(x, y, color, WIDTH, HEIGHT, Assets.enemyTRDes[color]);
	}
	
	public boolean checkCollision(Jumper jumper) {
		return (jumper.isSameColor(color) || color == 2) && bounds.overlaps(jumper.bounds);
	}
	

}
