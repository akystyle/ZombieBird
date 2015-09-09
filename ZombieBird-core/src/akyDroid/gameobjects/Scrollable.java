package akyDroid.gameobjects;

import com.badlogic.gdx.math.Vector2;

public class Scrollable {

	protected Vector2 myPosition, myVelocity;
	protected int myWidth,myHeight;
	protected boolean isScrolledLeft;
	
	public Scrollable(float x,float y,int width,int height,float scrollSpeed){
		myPosition = new Vector2(x,y);
		myVelocity = new Vector2(scrollSpeed,0);
		myWidth = width;
		myHeight = height;
		isScrolledLeft = false;
	}
	
	public void update(float delta){
		myPosition.add(myVelocity.cpy().scl(delta));
		
		if(myPosition.x + myWidth < 0){
			isScrolledLeft = true;
		}
	}
	
	public void stop(){
		myVelocity.x = 0;
	}
	
	public void reset(float newX){
		myPosition.x = newX;
		isScrolledLeft = false;
	}
	
	public boolean isScrolledLeft(){
		return isScrolledLeft;
	}
	
	public float getTailX(){
		return myPosition.x + myWidth;
	}
	
	public float getX(){
		return myPosition.x;
	}
	
	public float getY(){
		return myPosition.y;
	}
	
	public int getWidth(){
		return myWidth;
	}

	public int getHeight(){
		return myHeight;
	}
}
