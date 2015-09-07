package akyDroid.gameworld;

import akyDroid.gameobjects.MyBird;

import com.badlogic.gdx.Gdx;

public class MyGameWorld {

	MyBird myBird;
	
	public MyGameWorld(int midPointY){
		myBird = new MyBird(33,midPointY-5,17,12);
	}
	
	public void update(float delta){
		Gdx.app.log("MyGameWorld","Update called");
		myBird.update(delta);
	}
	
	public MyBird getBird(){
		return myBird;
	}
}