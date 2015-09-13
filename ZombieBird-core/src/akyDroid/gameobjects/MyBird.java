package akyDroid.gameobjects;


import akyDroid.frameworkhelpers.MyAssetLoader;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;


public class MyBird {

	Vector2 position;
	Vector2 velocity;
	Vector2 acceleration;
	
	boolean isAlive;
	
	float rotation;
	int height,width;
	float originalY;
	
	Circle myBoundingCircle;
	
	public MyBird(float x, float y, int width, int height){
		this.width = width;
		this.height = height;
		originalY = y;
		position = new Vector2(x,y);
		velocity = new Vector2(0,0);
		acceleration = new Vector2(0,460);
		myBoundingCircle = new Circle();
		isAlive = true; 
	}
	
	public void update(float delta){
		velocity.add(acceleration.cpy().scl(delta));
		if(velocity.y > 200){
			velocity.y = 200;
		}
		
		if(position.y < -2){
			position.y = -2;
			velocity.y = 0;
		}
		
		position.add(velocity.cpy().scl(delta));
		myBoundingCircle.set(position.x + 9,position.y+6,6.5f);
		
		if(velocity.y<0){
			rotation -= 600* delta;
			
			if (rotation < -20){
				rotation = -20;
			}
		}
		
		if(isFalling() || !isAlive){
			rotation += 480 * delta;
			if(rotation > 90){
				rotation = 90;
			}
		}
	}
	
	public boolean isFalling(){
		return velocity.y>110;
	}
	
	public boolean shouldntFlap(){
		return velocity.y>70 || !isAlive;
	}
	
	public void onClick(){
		if(isAlive){
			MyAssetLoader.flap.play();
			velocity.y = -140;	
		}
	}
	
	public float getX(){
		return position.x;
	}
	
	public float getY(){
		return position.y;
	}
	
	public float getWidth(){
		return width;
	}
	
	public float getHeight(){
		return height;
	}
	
	public float getRotation(){
		return rotation;
	}
	
	public Circle getMyBoundingCircle(){
		return myBoundingCircle;
	}

	public void die() {
		isAlive = false;
		velocity.y = 0;
	}

	public void decelerate() {
		acceleration.y = 0;
	}
	
	public boolean isAlive(){
		return isAlive;
	}

	public void onRestart(int y){
		rotation = 0;
		position.y = y;
		velocity.x = 0;
		velocity.y = 0;
		acceleration.x = 0;
		acceleration.y = 460;
		isAlive = true;
	}
	
	public void updateReady(float myRunTime){
		position.y = 2 * (float) Math.sin(7 * myRunTime) + originalY;
	}
}
