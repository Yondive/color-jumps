package ru.yondive.colorjumps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MainMenuScreen implements Screen {
	
	private ColorJumps game;
	
	private Stage stage;
	private Table table;
	private Button buttonScore, buttonPlay, buttonAbout;
	private Label heading;

	public MainMenuScreen(ColorJumps game) {
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.getBatch().begin();
		stage.getBatch().draw(Assets.bgTR, 0, 0);
		stage.getBatch().end();
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		stage = new Stage(new StretchViewport(ColorJumps.WIDTH, ColorJumps.HEIGHT));
		Gdx.input.setInputProcessor(stage);
		
		table = new Table(Assets.skin);
		table.setBounds(0, 0,  ColorJumps.WIDTH,  ColorJumps.HEIGHT);
		
		heading = new Label("Color Jumps", new Label.LabelStyle(Assets.whiteFont, Color.WHITE));

		buttonPlay = new Button(Assets.skin.getDrawable("button-start"));
		buttonScore = new Button(Assets.skin.getDrawable("button-leaderboard"));
		buttonAbout = new Button(Assets.skin.getDrawable("button-about"));
		
		buttonPlay.addListener(new InputListener() {
		    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
		    	game.setScreen(new GameScreen(game));
		        return true;
		    }
		});
		
		
		table.add(heading).colspan(2).padTop(50);
		table.row().padTop(250);
		table.add(buttonPlay).colspan(2);
		table.row().padTop(300).padBottom(50);
		table.add(buttonScore).padRight(100);
		table.add(buttonAbout).padLeft(100);
		//table.debug(); //TODO delete
		stage.addActor(table);
	}

	@Override
	public void hide() {
		
		
	}

	@Override
	public void pause() {
		
		
	}

	@Override
	public void resume() {
		
		
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}
