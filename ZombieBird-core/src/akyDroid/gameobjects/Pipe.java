package akyDroid.gameobjects;

import java.util.Random;

public class Pipe extends Scrollable{

	Random r;
	
	public Pipe(float x, float y, int width, int height, float scrollSpeed) {
		super(x, y, width, height, scrollSpeed);
		r = new Random(); 
	}

	@Override
	public void reset(float newX){
		super.reset(newX);
		myHeight = r.nextInt(90) + 15;
	}
}
