package ru.yondive.colorjumps;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
		this.colors = new Color[] { Color.RED, Color.GREEN, Color.BLUE,  Color.BLACK};
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

}
