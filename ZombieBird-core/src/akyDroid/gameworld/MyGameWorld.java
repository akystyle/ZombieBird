package akyDroid.gameworld;

import akyDroid.frameworkhelpers.MyAssetLoader;
import akyDroid.gameobjects.MyBird;
import akyDroid.gameobjects.ScrollHandler;

public class MyGameWorld {

	MyBird myBird;
	ScrollHandler myScroller;
	boolean isAlive = true;
	
	public MyGameWorld(int midPointY){
		myBird = new MyBird(33,midPointY-5,17,12);
		myScroller = new ScrollHandler(midPointY+66);
	}
	
	public void update(float delta){
		//Gdx.app.log("MyGameWorld","Update called");
		myBird.update(delta);
		myScroller.update(delta);
		
		if(isAlive && myScroller.collides(myBird)){
			myScroller.stop();
			MyAssetLoader.dead.play();
			isAlive = false;
		}
	}
	
	public MyBird getBird(){
		return myBird;
	}
	
	public ScrollHandler getScrollHandler(){
		return myScroller;
	}
}