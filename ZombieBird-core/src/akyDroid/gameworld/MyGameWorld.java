package akyDroid.gameworld;

import akyDroid.gameobjects.MyBird;
import akyDroid.gameobjects.ScrollHandler;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class MyGameWorld {

	MyBird myBird;
	ScrollHandler myScroller;
	Rectangle myGround;
	int score = 0;
	
	public MyGameWorld(int midPointY){
		myBird = new MyBird(33,midPointY-5,17,12);
		myScroller = new ScrollHandler(this,midPointY+66);
		myGround = new Rectangle(0,midPointY + 66, 136,11);
	}
	
	public void update(float delta){
		//Gdx.app.log("MyGameWorld","Update called");
		
		if(delta > .15f){
			delta = .15f;
		}
		
		myBird.update(delta);
		myScroller.update(delta);
		
		if(myBird.isAlive() && myScroller.collides(myBird)){
			myScroller.stop();
			myBird.die();
		}
		
		if(Intersector.overlaps(myBird.getMyBoundingCircle(), myGround)){
			myScroller.stop();
			myBird.die();
			myBird.decelerate();
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

}