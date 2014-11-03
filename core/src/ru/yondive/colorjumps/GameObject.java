package ru.yondive.colorjumps;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameObject extends Actor {
	protected  ShapeRenderer renderer = new ShapeRenderer();
	protected  Vector2 position;
	
	protected  int color;
	protected  Color[] colors;
	
	protected Rectangle bounds;
	
	public GameObject(float x, float y, int color, float width, float height) {
		this.position = new Vector2(x, y);
		this.color = color;
		this.colors = new Color[] { Color.RED, Color.GREEN, Color.BLUE };
		bounds = new Rectangle();
		setWidth(width);
		setHeight(height);
		updateBounds();
	}
	
	public int getColorNumber() {
		return color;
	}
	
	public void updateBounds() {
		setBounds(position.x, position.y, getWidth(), getHeight());
	    bounds.set(getX(), getY(), getWidth(), getHeight());
	}
	
	@Override
	public void draw (Batch batch, float parentAlpha) {
	    batch.end();

	    renderer.setProjectionMatrix(batch.getProjectionMatrix());
	    renderer.setTransformMatrix(batch.getTransformMatrix());
	    renderer.translate(position.x, position.y, 0);

	    renderer.begin(ShapeType.Filled);
	    renderer.setColor(colors[color]);
	    renderer.rect(0, 0, getWidth(), getHeight());
	    renderer.end();
	    
	    batch.begin();
	}
}
