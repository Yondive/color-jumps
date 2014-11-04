package ru.yondive.colorjumps;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class Enemy  extends GameObject{
	public static final float HEIGHT = 0.5f;
	public static final float WIDTH = 5f;
	
	
	public Enemy(float x, float y, int color) {
		super(x, y, color, WIDTH, HEIGHT);
	}
	
	public void checkCollision(Jumper jumper) {
		if (jumper.isSameColor(color) && bounds.overlaps(jumper.bounds)) {
			jumper.hitPlatform();
		}
	}
	
	@Override
	public void draw (Batch batch, float parentAlpha) {


	    renderer.setProjectionMatrix(batch.getProjectionMatrix());
	    renderer.setTransformMatrix(batch.getTransformMatrix());
	    renderer.translate(position.x, position.y, 0);

	    renderer.begin(ShapeType.Filled);
	    renderer.setColor(colors[color]);
	    renderer.circle(position.x, position.y, WIDTH);
	    renderer.end();
	    

	}
}
