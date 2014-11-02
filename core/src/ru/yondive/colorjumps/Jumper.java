package ru.yondive.colorjumps;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Jumper extends Actor{
	private ShapeRenderer renderer = new ShapeRenderer();
	private Vector2 position;
	
	public Jumper(float x, float y) {
		position = new Vector2(x, y);
	}
	
	public void update(float delta, float accel, boolean isColorChanged) {
		position.x += accel / 10;
	}
	
	@Override
	public void draw (Batch batch, float parentAlpha) {
	    batch.end();

	    renderer.setProjectionMatrix(batch.getProjectionMatrix());
	    renderer.setTransformMatrix(batch.getTransformMatrix());
	    renderer.translate(position.x, position.y, 0);

	    renderer.begin(ShapeType.Filled);
	    renderer.setColor(Color.BLUE);
	    renderer.rect(0, 0, 5, 5);
	    renderer.end();

	    batch.begin();
	}
}
