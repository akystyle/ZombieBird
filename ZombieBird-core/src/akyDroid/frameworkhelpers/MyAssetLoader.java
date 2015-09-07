package akyDroid.frameworkhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MyAssetLoader {

	public static Texture myTexture;
	public static TextureRegion myBg, myGrass;
	
	public static Animation myBirdAnimation;
	public static TextureRegion myBird,myBirdDown,myBirdUp;
	
	public static TextureRegion mySkullUp, mySkullDown, myBar;
	
	public static void load(){
		myTexture = new Texture(Gdx.files.internal("data/texture.png"));
		myTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		myBg = new TextureRegion(myTexture,0, 0, 136,43);
		myBg.flip(false, true);
		
		
	}
}
