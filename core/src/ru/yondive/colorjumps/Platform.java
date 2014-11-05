package ru.yondive.colorjumps;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Platform extends GameObject {
	
	private enum PlatformType {
		Moving, Static
	}
	
	public static final float HEIGHT = 0.5f;
	public static final float WIDTH = 5f;
	public static final float PLATFORM_VELOCITY_X = 6f;
	
	private PlatformType platformType;
	private int dir;
	
	public Platform(float x, float y, int color, int type) {
		super(x, y, color, WIDTH, HEIGHT);
		switch (type) {
			case 0:
				platformType = PlatformType.Static;
				break;
			case 1:
				platformType = PlatformType.Moving;
				break;
		}
		if (Math.random() > 0.5f) dir = -1;
		else dir = 1;
	}
	
	public boolean checkCollision(Jumper jumper) {
		return (jumper.isSameColor(color) || colors[color] == Color.BLACK) && bounds.overlaps(jumper.bounds);
	}
	
	public void update(float delta) {
		switch (platformType) {
			case Static:
				
				break;
			case Moving:
				position.add(PLATFORM_VELOCITY_X * delta * dir, 0);
				if (position.x < -0.5f || (position.x + WIDTH - 0.5f) > World.WIDTH) dir = -dir; 
				updateBounds();
				break;
			default:
				break;
		}
	}
	
	@Override
	public void draw (Batch batch, float parentAlpha) {


	    renderer.setProjectionMatrix(batch.getProjectionMatrix());
	    renderer.setTransformMatrix(batch.getTransformMatrix());
	    renderer.translate(position.x, position.y, 0);

	    renderer.begin(ShapeType.Filled);
	    renderer.setColor(colors[color]);
	    renderer.rect(0, 0, getWidth(), getHeight());
	    renderer.end();
	    

	}
}
