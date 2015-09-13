package akyDroid.frameworkhelpers;

import akyDroid.gameobjects.MyBird;
import akyDroid.gameworld.MyGameWorld;

import com.badlogic.gdx.InputProcessor;

public class MyInputHandler implements InputProcessor {

	MyBird myBird;
	MyGameWorld myWorld;

	public MyInputHandler(MyGameWorld tempWorld){
		myWorld = tempWorld;
		myBird = myWorld.getBird();
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		if(myWorld.isReady()){
			
			myWorld.start();
		}
		
		myBird.onClick();
		
		if(myWorld.isGameOver() || myWorld.isHighScore()){
			myWorld.restart();
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
