package akyDroid.frameworkhelpers;

import java.util.ArrayList;
import java.util.List;

import akyDroid.gameobjects.MyBird;
import akyDroid.gameui.MySimpleButton;
import akyDroid.gameworld.MyGameWorld;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class MyInputHandler implements InputProcessor {

	MyBird myBird;
	MyGameWorld myWorld;
	
	List<MySimpleButton> myMenuButtons;
	MySimpleButton myPlayButton;
	
	float scaleFactorX;
	float scaleFactorY;

	public MyInputHandler(MyGameWorld tempWorld, float scaleFactorX, float scaleFactorY){
		myWorld = tempWorld;
		myBird = myWorld.getBird();
		
		int midPointY = myWorld.getMidPointY();
		
		this.scaleFactorX = scaleFactorX;
		this.scaleFactorY = scaleFactorY;
		
		myMenuButtons = new ArrayList<MySimpleButton>();
		myPlayButton = new MySimpleButton(136/2 - (MyAssetLoader.myPlayButtonUp.getRegionWidth() / 2),midPointY + 50, 29, 16, MyAssetLoader.myPlayButtonUp, MyAssetLoader.myPlayButtonDown);
		myMenuButtons.add(myPlayButton);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		
		if(keycode == Keys.SPACE){
			
			if(myWorld.isMenu()){
				myWorld.ready();
			} else if (myWorld.isReady()){
				myWorld.start();
			}
			
			myBird.onClick();
			
			if(myWorld.isGameOver() || myWorld.isHighScore()){
				myWorld.restart();
			}
		}
		
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);
		System.out.println(screenX + " " + screenY);
		if(myWorld.isMenu()){
			myPlayButton.isTouchDown(screenX, screenY);
		} else if (myWorld.isReady()){
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
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);
		
		if(myWorld.isMenu()){
			if(myPlayButton.isTouchUp(screenX, screenY)){
				myWorld.isReady();
				return true;
			}
		} 
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	private int scaleX(int screenX){
		return (int) (screenX/scaleFactorX);
	}
	
	private int scaleY(int screenY){
		return (int) (screenY/scaleFactorY);
	}
	
	public List<MySimpleButton> getMenuButtons(){
		return myMenuButtons;
	}
}