package akyDroid.zombiebird;

import akyDroid.screens.MyGameScreen;

import com.badlogic.gdx.*;

public class MyGdxGame extends Game{

	@Override
	public void create() {
		Gdx.app.log("MyGdxGame", "AkyDroid's myGdxGame class created as ApplicationInterface");
		setScreen(new MyGameScreen());
	}
}
