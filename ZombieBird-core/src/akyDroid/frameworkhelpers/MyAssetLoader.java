package akyDroid.frameworkhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyAssetLoader {

	public static Texture myTexture;
	public static TextureRegion myBg, myGrass;
	
	public static Animation myBirdAnimation;
	public static TextureRegion myBird,myBirdDown,myBirdUp;
	
	public static Sound dead; 
	
	public static TextureRegion mySkullUp, mySkullDown, myBar;
	
	public static void load(){
		myTexture = new Texture(Gdx.files.internal("data/texture.png"));
		myTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		myBg = new TextureRegion(myTexture,0, 0, 136,43);
		myBg.flip(false, true);
		
		myGrass = new TextureRegion(myTexture,0,43,143,11);
		myGrass.flip(false, true);
		
		myBirdDown = new TextureRegion(myTexture, 136, 0, 17, 12);
		myBirdDown.flip(false, true);
		
		myBird = new TextureRegion(myTexture, 153, 0, 17, 12);
		myBird.flip(false, true);
		
		myBirdUp = new TextureRegion(myTexture,170,0,17,12);
		myBirdUp.flip(false, true);
		
		TextureRegion[] myBirds = {myBirdDown,myBird,myBirdUp};
		myBirdAnimation = new Animation(0.06f,myBirds);
		myBirdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
		
		mySkullUp = new TextureRegion(myTexture, 192, 0, 24, 14);
		mySkullDown = new TextureRegion(mySkullUp);
		mySkullDown.flip(false, true);
		
		myBar = new TextureRegion(myTexture,136,16,22,3);
		myBar.flip(false, true);
		
		dead = Gdx.audio.newSound(Gdx.files.internal("data/dead.wav"));
	}
	
	public static void dispose(){
		myTexture.dispose();
	}
}
