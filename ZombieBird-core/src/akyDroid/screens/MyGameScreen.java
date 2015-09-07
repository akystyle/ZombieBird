package akyDroid.screens;

import akyDroid.frameworkhelpers.MyInputHandler;
import akyDroid.gameworld.MyGameRenderer;
import akyDroid.gameworld.MyGameWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class MyGameScreen implements Screen{

	MyGameWorld gameWorld;
	MyGameRenderer gameRenderer;
	float myRunTime=0;
	
	public MyGameScreen(){
		float screenHeight = Gdx.graphics.getHeight();
		float screenWidth = Gdx.graphics.getWidth();
		float gameWidth = 136;
		float gameHeight = screenHeight / (screenWidth /gameWidth);
		
		int midPointY = (int) (gameHeight/2);
		
		//Gdx.app.log("MyGameScreen", "Constructor Called and screen attached");
		gameWorld = new MyGameWorld(midPointY);
		gameRenderer = new MyGameRenderer(gameWorld, (int)gameHeight, midPointY);
		
		Gdx.input.setInputProcessor(new MyInputHandler(gameWorld.getBird()));
	}
	
	@Override
	public void show() {
		//Gdx.app.log("MyGameScreen", "Show Called ");
	}

	@Override
	public void render(float delta) {
		// Sets a Color to Fill the Screen with (RGB = 10, 15, 230), Opacity of 1 (100%)
        //Gdx.gl.glClearColor(10/255.0f, 15/255.0f, 230/255.0f, 1f);
        // Fills the screen with the selected color
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        //Gdx.app.log("MyGameScreen FPS: ", (1/delta) + "");

		myRunTime += delta;
        gameWorld.update(delta);
        gameRenderer.render(myRunTime);
	}

	@Override
	public void resize(int width, int height) {
		//Gdx.app.log("MyGameScreen", "resizing");

	}

	@Override
	public void pause() {
		//Gdx.app.log("MyGameScreen", "Pause Called");
	}

	@Override
	public void resume() {
		//Gdx.app.log("MyGameScreen", "Resume Called ");
	}

	@Override
	public void hide() {
		//Gdx.app.log("MyGameScreen", "Hide Called ");
	}

	@Override
	public void dispose() {
		//Gdx.app.log("MyGameScreen", "Dispose Called ");
	}
}
