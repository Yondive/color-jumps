package ru.yondive.colorjumps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;


public class MainMenu implements Screen {
	private Stage stage;
	private TextureAtlas atlas;
	private Skin skin;
	private Table table;
	private Button buttonScore, buttonPlay, buttonAbout;
	private BitmapFont whiteFont;
	private Label heading;
	
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		
		
	}

	@Override
	public void show() {
		whiteFont = new BitmapFont(Gdx.files.internal("fonts/consbold.fnt"), false);
		
		stage = new Stage(new StretchViewport(ColorJumps.WIDTH, ColorJumps.HEIGHT));
		atlas = new TextureAtlas("mainmenu.pack");
		skin = new Skin(atlas);
		table = new Table(skin);
		table.setBounds(0, 0,  ColorJumps.WIDTH,  ColorJumps.HEIGHT);
		
		Label.LabelStyle headingStyle = new Label.LabelStyle(whiteFont, Color.WHITE);
		heading = new Label("COLOR JUMPS", headingStyle);


		
		buttonPlay = new Button(skin.getDrawable("button-start"));
		buttonScore = new Button(skin.getDrawable("button-leaderboard"));
		buttonAbout = new Button(skin.getDrawable("button-about"));
		
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
		
		
	}

}
