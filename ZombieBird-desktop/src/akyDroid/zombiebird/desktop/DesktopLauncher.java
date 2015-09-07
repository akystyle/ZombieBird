package akyDroid.zombiebird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import akyDroid.zombiebird.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "AkyDroid's app";
		config.height = 408;
		config.width = 272;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
