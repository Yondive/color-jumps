package ru.yondive.colorjumps;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class World {
	private Stage stage;
	private Jumper jumper;
	private Batch batch;
	
	
	public World(Batch batch) {
		this.batch = batch;
		stage = new Stage(new StretchViewport(80, 128), batch);
		jumper = new Jumper(40, 64);
		stage.addActor(jumper);
	}
	
	public void update(float delta, float accel, boolean isColorChanged) {
		jumper.update(delta, accel, isColorChanged);
	}
	
	public void draw() {
		
		stage.draw();
	}

}
