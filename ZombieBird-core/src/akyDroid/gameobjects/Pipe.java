package akyDroid.gameobjects;

import java.util.Random;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

public class Pipe extends Scrollable{

	Random r;
	Rectangle skullUp, skullDown,barUp, barDown;
	float groundY;
	
	public static final int VERTICAL_GAP = 45;
	public static final int SKULL_WIDTH = 24;
	public static final int SKULL_HEIGHT = 11;
	
	public Pipe(float x, float y, int width, int height, float scrollSpeed, float groundY) {
		super(x, y, width, height, scrollSpeed);
		r = new Random(); 
		skullUp = new Rectangle();
		skullDown = new Rectangle();
		barUp = new Rectangle();
		barDown = new Rectangle();
		this.groundY = groundY;
	}

	public boolean collides(MyBird myBird){
		if (myPosition.x < myBird.getX() + myBird.getWidth()) {
            return (Intersector.overlaps(myBird.getMyBoundingCircle(), barUp)
                    || Intersector.overlaps(myBird.getMyBoundingCircle(), barDown)
                    || Intersector.overlaps(myBird.getMyBoundingCircle(), skullUp) || Intersector
                        .overlaps(myBird.getMyBoundingCircle(), skullDown));
        }
        return false;
	}
	
	@Override
	public void update(float delta){
		super.update(delta);
		barUp.set(myPosition.x,myPosition.y,myWidth,myHeight);
		barDown.set(myPosition.x,myPosition.y + myHeight + VERTICAL_GAP,myWidth,groundY - (myPosition.y + myHeight + VERTICAL_GAP));
		
		skullUp.set(myPosition.x - (SKULL_WIDTH - myWidth) / 2, myPosition.y + myHeight - SKULL_HEIGHT, SKULL_WIDTH,SKULL_HEIGHT);
		skullDown.set(myPosition.x - (SKULL_WIDTH - myWidth) / 2, myPosition.y + myHeight + VERTICAL_GAP,SKULL_WIDTH,SKULL_HEIGHT);
	}
	
	@Override
	public void reset(float newX){
		super.reset(newX);
		myHeight = r.nextInt(90) + 15;
	}

	public Rectangle getSkullUp() {
		return skullUp;
	}

	public Rectangle getSkullDown() {
		return skullDown;
	}

	public Rectangle getBarUp() {
		return barUp;
	}

	public Rectangle getBarDown() {
		return barDown;
	}
}
