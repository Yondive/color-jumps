package ru.yondive.colorjumps;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class GameObject extends Image {
	protected  ShapeRenderer renderer = new ShapeRenderer();
	protected  Vector2 position;
	
	protected  int color;
	
	protected Rectangle bounds;
	
	public GameObject(float x, float y, int color, float width, float height, TextureRegionDrawable trd) {
		super(trd);
		this.position = new Vector2(x, y);
		this.color = color;
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
