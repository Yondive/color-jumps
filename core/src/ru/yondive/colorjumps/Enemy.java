package ru.yondive.colorjumps;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Enemy  extends GameObject{
	public static final float HEIGHT = 1f;
	public static final float WIDTH = 1f;
	
	
	public Enemy(float x, float y, int color) {
		super(x, y, color, WIDTH, HEIGHT);
	}
	
	public boolean checkCollision(Jumper jumper) {
		return (jumper.isSameColor(color) || colors[color] == Color.BLACK) && bounds.overlaps(jumper.bounds);
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
