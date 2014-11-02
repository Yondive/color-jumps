package ru.yondive.colorjumps;

import com.badlogic.gdx.Game;


public class ColorJumps extends Game {
	public final static int HEIGHT = 1280;
	public final static int WIDTH = 800;
	
	
	@Override
	public void create() {
		Assets.load();
		setScreen(new MainMenuScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void pause() {

		super.pause();
	}

	@Override
	public void resume() {

		super.resume();
	}

	@Override
	public void render() {

		super.render();
	}

	@Override
	public void resize(int width, int height) {

		super.resize(width, height);
	}

	
}
