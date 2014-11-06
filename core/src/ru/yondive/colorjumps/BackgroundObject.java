package ru.yondive.colorjumps;


import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class BackgroundObject extends Image {
	protected  ShapeRenderer renderer = new ShapeRenderer();
	protected  Vector2 position;
	
	
	protected Rectangle bounds;
	
	public BackgroundObject(float x, float y, float width, float height, TextureRegionDrawable trd) {
		super(trd);
		this.position = new Vector2(x, y);
		bounds = new Rectangle();
		setWidth(width);
		setHeight(height);
		updateBounds();
	}
	
	
	public void updateBounds() {
		setBounds(position.x, position.y, getWidth(), getHeight());
	    bounds.set(getX(), getY(), getWidth(), getHeight());
	}

}
