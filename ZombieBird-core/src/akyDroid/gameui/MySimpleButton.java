package akyDroid.gameui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class MySimpleButton {

	float x, y , width, height;
	
	TextureRegion buttonUp, buttonDown;
	
	Rectangle bounds;
	
	boolean isPressed = false;
	
	public MySimpleButton(float x, float y, float width, float height,TextureRegion buttonUp, TextureRegion buttonDown){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.buttonUp = buttonUp;
		this.buttonDown = buttonDown;
		
		bounds = new Rectangle(x, y, width, height);
	}
	
	public boolean isClicked(int screenX,int screenY){
		return bounds.contains(screenX, screenY);
	}
	
	public void draw(SpriteBatch mybatcher){
		if(isPressed){
			mybatcher.draw(buttonDown, x, y, width, height);
		}else{
			mybatcher.draw(buttonUp, x, y, width, height);
		}
	}
	
	public boolean isTouchDown(int screenX,int screenY){
		if(bounds.contains(screenX,screenY)){
			isPressed = true;
			return true;
		}
		return false;
	}
	
	public boolean isTouchUp(int screenX,int screenY){
		
		if(bounds.contains(screenX,screenY) && isPressed){
			isPressed = false;
			return true;
		}
		isPressed = false;
		return false;
	}
}
