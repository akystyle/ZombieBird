package akyDroid.screens;

import akyDroid.frameworkhelpers.MyAssetLoader;
import akyDroid.tweenaccessors.SpriteAccessor;
import akyDroid.zombiebird.MyGdxGame;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MySplashScreen implements Screen{

	TweenManager myTweenManager;
	SpriteBatch mySpriteBatcher;
	Sprite mySprite;
	MyGdxGame myGame;
	
	public MySplashScreen(MyGdxGame myTempGame) {
		myGame = myTempGame;
	}
	
	@Override
	public void show() {
		mySprite = new Sprite(MyAssetLoader.myLogo);
		mySprite.setColor(1, 1, 1, 0);
		
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		float desiredWidth = width * .7f;
		float scale = desiredWidth / mySprite.getWidth();
		
		mySprite.setSize(mySprite.getWidth() * scale,mySprite.getHeight() * scale);
		mySprite.setPosition((width / 2) - (mySprite.getWidth()/2), (height / 2) - (mySprite.getHeight()/2));
		setupTween();
		mySpriteBatcher = new SpriteBatch();
	}

	private void setupTween(){
		Tween.registerAccessor(Sprite.class, new SpriteAccessor());
		myTweenManager = new TweenManager();
		
		TweenCallback myTweenCallBack = new TweenCallback() {
			
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				myGame.setScreen(new MyGameScreen());
				
			}
		};
		
		Tween.to(mySprite, SpriteAccessor.ALPHA, .8f).target(1)
		.ease(TweenEquations.easeInOutQuad).repeatYoyo(1, .4f)
		.setCallback(myTweenCallBack).setCallbackTriggers(TweenCallback.COMPLETE)
		.start(myTweenManager);
	}
	
	@Override
	public void render(float delta) {
		myTweenManager.update(delta);
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		mySpriteBatcher.begin();
		mySprite.draw(mySpriteBatcher);
		mySpriteBatcher.end();
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void dispose() {
		
	}
}
