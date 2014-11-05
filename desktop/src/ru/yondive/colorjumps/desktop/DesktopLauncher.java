package ru.yondive.colorjumps.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.yondive.colorjumps.ColorJumps;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Color Jumps";
		config.useGL30 = false;
		config.width = 240;
		config.height = 400;
		
		new LwjglApplication(new ColorJumps(), config);
	}
}
