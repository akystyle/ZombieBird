package akyDroid.gameworld;

import akyDroid.frameworkhelpers.MyAssetLoader;
import akyDroid.gameobjects.MyBird;
import akyDroid.gameobjects.ScrollHandler;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class MyGameWorld {

	MyBird myBird;
	ScrollHandler myScroller;
	Rectangle myGround;
	int score = 0;
	myGameState currentState;
	public int midPointY;
	
	public enum myGameState{
		READY,RUNNING,GAMEOVER,HIGHSCORE
	}
	
	public MyGameWorld(int midPointY){
		myBird = new MyBird(33,midPointY-5,17,12);
		myScroller = new ScrollHandler(this,midPointY+66);
		myGround = new Rectangle(0,midPointY + 66, 136,11);
		currentState = myGameState.READY;
		this.midPointY = midPointY;
	}
	
	public void update(float delta){
		switch(currentState){
		case READY:
			updateReady(delta);
			break;
			
		case RUNNING:
			updateRunning(delta);
			break;
			
		default:
			break;
		}
	}
	
	private void updateReady(float delta) {
		
	}

	public void updateRunning(float delta){
		//Gdx.app.log("MyGameWorld","Update called");
		
		if(delta > .15f){
			delta = .15f;
		}
		
		myBird.update(delta);
		myScroller.update(delta);
		
		if(myBird.isAlive() && myScroller.collides(myBird)){
			myScroller.stop();
			myBird.die();
			MyAssetLoader.dead.play();
		}
		
		if(Intersector.overlaps(myBird.getMyBoundingCircle(), myGround)){
			myScroller.stop();
			myBird.die();
			myBird.decelerate();
			currentState = myGameState.GAMEOVER;
			
			if(score > MyAssetLoader.getHighScore()){
				MyAssetLoader.setHighScore(score);
				currentState = myGameState.HIGHSCORE;
			}
		}
	}
	
	public MyBird getBird(){
		return myBird;
	}
	
	public ScrollHandler getScrollHandler(){
		return myScroller;
	}
	

	public int getScore(){
		return score;
	}
	
	public void addScore(int increment){
		score += increment;
	}

	public void restart(){
		currentState = myGameState.READY;
		score = 0;
		myBird.onRestart(midPointY - 5);
		myScroller.onRestart();
	}
	
	public boolean isReady(){
		return currentState == myGameState.READY;
	}
	
	public void start(){
		currentState = myGameState.RUNNING;
	}
	
	public boolean isGameOver(){
		return currentState == myGameState.GAMEOVER;
	}
	
	public boolean isHighScore(){
		return currentState == myGameState.HIGHSCORE;
	}
}