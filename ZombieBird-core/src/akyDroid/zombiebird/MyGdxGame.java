package akyDroid.zombiebird;

import akyDroid.frameworkhelpers.MyAssetLoader;
import akyDroid.screens.MySplashScreen;

import com.badlogic.gdx.Game;

public class MyGdxGame extends Game{

	@Override
	public void create() {
		//Gdx.app.log("MyGdxGame", "AkyDroid's myGdxGame class created as ApplicationInterface");
		MyAssetLoader.load();
		setScreen(new MySplashScreen(this));
	}
	
	public void dispose(){
		super.dispose();
		MyAssetLoader.dispose();
	}
}
