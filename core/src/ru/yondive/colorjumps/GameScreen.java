package ru.yondive.colorjumps;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class GameScreen implements Screen {
	
	private enum States {
		Running, Paused, GameOver, Wait
	}
	
	private InputListener pauseListener = new InputListener() {
	    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	    	state = States.Paused;
	    	Gdx.input.setInputProcessor(pauseStage);
	        return true;
	    }
	};
	
	private InputListener colorChangeListener = new InputListener() {
	    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	    	if (event.getListenerActor().hit(x, y, true) == null) {
	    		isColorChanged = true;
	    	}
	        return true;
	    }
	};
	
	private InputListener continueListener = new InputListener() {
	    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	    	state = States.Running;
	    	Gdx.input.setInputProcessor(staticStage);
	        return true;
	    }
	};
	
	private InputListener exitListener = new InputListener() {
	    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	    	game.setScreen(new MainMenuScreen(game));
	        return true;
	    }
	};
	
	private InputListener newGameListener = new InputListener() {
	    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	    	world = new World(batch, game);
	    	state = States.Running;
	    	Gdx.input.setInputProcessor(staticStage);
	        return true;
	    }
	};
	
	private ColorJumps game;

	private Stage staticStage;
	private Stage pauseStage;
	private Stage gameOverStage;
	private SpriteBatch batch;
	private States state;
	
	private World world;
	
	private Label scoreLabel;
	private Label menuScoreLabel;
	private boolean isColorChanged;
	
	public GameScreen(ColorJumps game) {
		this.game = game;
		this.isColorChanged = false;
	}
	
	@Override
	public void show() {
		batch = new SpriteBatch();
		
		/* Filling static stage */
		Label pause = new Label("P", new Label.LabelStyle(Assets.whiteFont, Color.BLUE));
		pause.addListener(pauseListener);
		pause.setPosition(ColorJumps.WIDTH - pause.getWidth(), ColorJumps.HEIGHT - pause.getHeight());
		
		scoreLabel = new Label("0", new Label.LabelStyle(Assets.whiteFont, Color.BLUE));
		scoreLabel.setPosition(scoreLabel.getWidth(), ColorJumps.HEIGHT - scoreLabel.getHeight());

		staticStage = new Stage(new StretchViewport(ColorJumps.WIDTH, ColorJumps.HEIGHT), batch);
		staticStage.addListener(colorChangeListener);
		staticStage.addActor(pause);
		staticStage.addActor(scoreLabel);
		Gdx.input.setInputProcessor(staticStage);
		
		/* Filling pause stage*/
		menuScoreLabel = new Label("0", new Label.LabelStyle(Assets.whiteFont, Color.BLACK));
		menuScoreLabel.setPosition(ColorJumps.WIDTH / 2 - menuScoreLabel.getWidth() / 2, ColorJumps.HEIGHT / 2 + 2 * menuScoreLabel.getHeight());
		
		Label go = new Label("Continue", new Label.LabelStyle(Assets.whiteFont,  Color.BLACK));
		go.addListener(continueListener);
		go.setPosition(ColorJumps.WIDTH / 2 - go.getWidth() / 2, ColorJumps.HEIGHT / 2 + go.getHeight());
		
		Label exit1 = new Label("Exit", new Label.LabelStyle(Assets.whiteFont, Color.BLACK));
		exit1.addListener(exitListener);
		exit1.setPosition(ColorJumps.WIDTH / 2 - exit1.getWidth() / 2, ColorJumps.HEIGHT / 2 - exit1.getHeight());
		
		pauseStage = new Stage(new StretchViewport(ColorJumps.WIDTH, ColorJumps.HEIGHT), batch);
		pauseStage.addActor(go);
		pauseStage.addActor(exit1);
		
		/* Filling game over stage */
		menuScoreLabel = new Label("You get 0", new Label.LabelStyle(Assets.whiteFont, Color.PURPLE));
		menuScoreLabel.setPosition(ColorJumps.WIDTH / 2 - menuScoreLabel.getWidth() / 2, ColorJumps.HEIGHT - 2 * menuScoreLabel.getHeight());
		
		Label exit2 = new Label("Exit", new Label.LabelStyle(Assets.whiteFont, Color.BLACK));
		exit2.addListener(exitListener);
		exit2.setPosition(ColorJumps.WIDTH / 2 - exit2.getWidth() / 2, ColorJumps.HEIGHT / 2 - exit2.getHeight());
		
		Label newGame= new Label("New Game", new Label.LabelStyle(Assets.whiteFont,  Color.BLACK));
		newGame.addListener(newGameListener);
		newGame.setPosition(ColorJumps.WIDTH / 2 - newGame.getWidth() / 2, ColorJumps.HEIGHT / 2 + newGame.getHeight());
		
		gameOverStage = new Stage(new StretchViewport(ColorJumps.WIDTH, ColorJumps.HEIGHT), batch);
		gameOverStage.addActor(menuScoreLabel);
		gameOverStage.addActor(newGame);
		gameOverStage.addActor(exit2);
		
		world = new World(batch, game);
		state = States.Running;
	}
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update(delta);
		draw();
	}

	
	@Override
	public void resize(int width, int height) {
		
	}
	
	private void update(float delta) {
		switch (state) {
		
			case Running:
				ApplicationType appType = Gdx.app.getType();
				float accel = 0f;
				if (appType == ApplicationType.Android || appType == ApplicationType.iOS) {
					accel = -Gdx.input.getAccelerometerX();
				} else {
					if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) accel = -5f;
					if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) accel = 5f;
				}
				
				boolean isGameOver = world.update(delta, accel, isColorChanged) == -1;
				scoreLabel.setText(Integer.toString(world.getScore()));
				isColorChanged = false;
				if (isGameOver) {
					state = States.GameOver;
					Gdx.input.setInputProcessor(gameOverStage);
					menuScoreLabel.setText("You get " + Integer.toString(world.getScore()));
				} 
				break;
			case Paused:
				
				break;
			case GameOver:
				
				break;
			default:
				break;
		}
	}
	private void draw() {
		switch (state) {
			case Running:
				world.draw();
				staticStage.draw();
				break;
			case Paused:
				world.draw();
				staticStage.draw();
				pauseStage.draw();
				break;
			case GameOver:
				world.draw();
				gameOverStage.draw();
				break;
			default:
				break;
		}
	}
	
	@Override
	public void hide() {
		if (state == States.Running) {
	    	state = States.Paused;
	    	Gdx.input.setInputProcessor(pauseStage);
		}
	}

	@Override
	public void pause() {	
		if (state == States.Running) {
	    	state = States.Paused;
	    	Gdx.input.setInputProcessor(pauseStage);
		}
	}

	@Override
	public void resume() {
	
	}

	@Override
	public void dispose() {
		staticStage.dispose();
	}	
}
