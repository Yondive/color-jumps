package ru.yondive.colorjumps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Assets {
	public static BitmapFont whiteFont;
	public static Skin skin;
	public static TextureRegion jumperTR;
	public static TextureRegion bgTR;
	public static TextureRegionDrawable bgFundTRD;
	public static TextureRegionDrawable[] jumperTRDes;
	public static TextureRegionDrawable[] platformTRDes;
	public static TextureRegionDrawable[] enemyTRDes;
	
	public static void load() {
		whiteFont = new BitmapFont(Gdx.files.internal("fonts/consbold.fnt"), false);
		
		TextureAtlas atlas = new TextureAtlas("mainmenu.pack");
		skin = new Skin(atlas);
		
		TextureAtlas atlasGame = new TextureAtlas("game.pack");
		bgTR = atlasGame.findRegion("background");
		bgFundTRD = new TextureRegionDrawable(atlasGame.findRegion("background_fund"));
		jumperTRDes = new TextureRegionDrawable[] {
				new TextureRegionDrawable(atlasGame.findRegion("jumpergreen")),	
				new TextureRegionDrawable(atlasGame.findRegion("jumperred")),
				new TextureRegionDrawable(atlasGame.findRegion("jumperblue"))
		};
		
		platformTRDes = new TextureRegionDrawable[] {
				new TextureRegionDrawable(atlasGame.findRegion("platformgreen")),	
				new TextureRegionDrawable(atlasGame.findRegion("platformred")),
				new TextureRegionDrawable(atlasGame.findRegion("platformblue")),
				new TextureRegionDrawable(atlasGame.findRegion("platformblack"))
		};
		
		enemyTRDes = new TextureRegionDrawable[] {
				new TextureRegionDrawable(atlasGame.findRegion("enemygreen")),	
				new TextureRegionDrawable(atlasGame.findRegion("enemyred")),
				//new TextureRegionDrawable(atlasGame.findRegion("enemyblue")),
				new TextureRegionDrawable(atlasGame.findRegion("enemyblack"))
		};
	}
}
