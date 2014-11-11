package ru.yondive.colorjumps;

import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
	
public class Spring extends GameObject{

	public Spring(float x, float y, int color, float width, float height,
			TextureRegionDrawable trd) {
		super(x, y, color, width, height, trd);
		
	}
	
	public boolean checkCollision(Jumper jumper) {
		return (jumper.isSameColor(color) || color == 3) && bounds.overlaps(jumper.bounds) && (jumper.bounds.y >= bounds.y);
	}
}
