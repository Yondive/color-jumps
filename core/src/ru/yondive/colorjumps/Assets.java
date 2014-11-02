package ru.yondive.colorjumps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.*;

public class Assets {
	public static BitmapFont whiteFont;
	public static Skin skin;
	public static TextureRegion jumperTR;
	
	public static void load() {
		whiteFont = new BitmapFont(Gdx.files.internal("fonts/consbold.fnt"), false);
		
		TextureAtlas atlas = new TextureAtlas("mainmenu.pack");
		skin = new Skin(atlas);
		jumperTR = atlas.findRegion("button-about");
	}
}
