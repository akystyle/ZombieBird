package akyDroid.frameworkhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyAssetLoader {

	public static Texture myTexture,myLogoTexture;
	public static TextureRegion myBg, myGrass;
	
	public static Animation myBirdAnimation;
	public static TextureRegion myLogo,myZbLogo;
	public static TextureRegion myBird,myBirdDown,myBirdUp;
	public static TextureRegion mySkullUp, mySkullDown, myBar;
	public static TextureRegion myPlayButtonUp, myPlayButtonDown;
	
	public static Sound dead,flap,coin; 
	
	public static BitmapFont myFont,myFontShadow;
	public static Preferences myGamePrefs;
	
	public static void load(){
		
		myGamePrefs = Gdx.app.getPreferences("ZombieBird");
		
		if(!myGamePrefs.contains("highScore")){
			myGamePrefs.putInteger("highScore", 0);
		}
		
		myLogoTexture = new Texture(Gdx.files.internal("data/logo.png"));
		myLogoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		myLogo = new TextureRegion(myLogoTexture,0,0, 512,114);
		
		myTexture = new Texture(Gdx.files.internal("data/texture.png"));
		myTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		myPlayButtonUp = new TextureRegion(myTexture,0,83,29,16);
		myPlayButtonDown = new TextureRegion(myTexture,29,83,29,16);
		myPlayButtonUp.flip(false, true);
		myPlayButtonDown.flip(false, true);
		
		myZbLogo = new TextureRegion(myTexture, 0, 55,135,24);
		myZbLogo.flip(false,true);
		
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
		flap = Gdx.audio.newSound(Gdx.files.internal("data/flap.wav"));
		coin = Gdx.audio.newSound(Gdx.files.internal("data/coin.wav"));
		
		myFont = new BitmapFont(Gdx.files.internal("data/text.fnt"));
		myFont.getData().setScale(.25f, -.25f);
		myFontShadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
		myFontShadow.getData().setScale(.25f,-.25f);
		
	}
	
	public static void dispose(){
		myTexture.dispose();
		
		dead.dispose();
		flap.dispose();
		coin.dispose();
		
		myFont.dispose();
		myFontShadow.dispose();
	}
	
	public static void setHighScore(int score){
		myGamePrefs.putInteger("highScore", score);
		myGamePrefs.flush();
	}
	
	public static int getHighScore(){
		return myGamePrefs.getInteger("highScore");
	}
}
