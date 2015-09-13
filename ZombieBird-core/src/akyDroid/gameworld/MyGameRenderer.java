package akyDroid.gameworld;

import java.util.List;

import akyDroid.frameworkhelpers.*;
import akyDroid.gameobjects.Grass;
import akyDroid.gameobjects.MyBird;
import akyDroid.gameobjects.Pipe;
import akyDroid.gameobjects.ScrollHandler;
import akyDroid.gameui.MySimpleButton;
import akyDroid.tweenaccessors.Value;
import akyDroid.tweenaccessors.ValueAccessor;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class MyGameRenderer {

	MyGameWorld myGameWorld;
	OrthographicCamera myCam;
	SpriteBatch mySpriteBatch;
	ShapeRenderer myShapeRenderer;
	
	MyBird myBird;
	ScrollHandler myScroller;
	Grass myFrontGrass, myBackGrass;
	Pipe myPipe1, myPipe2, myPipe3;

	TextureRegion myBG, myGrass, myBirdMid, myBirdUp, myBirdDown, mySkullUp,
			mySkullDown, myBar;
	Animation myBirdAnimation;

	TweenManager myTweenManager;
	Value alpha = new Value();
	
	List<MySimpleButton> myMenuButtons;
	
	int midPointY, gameHeight;

	public MyGameRenderer(MyGameWorld gameWorld, int gameHeight, int midPointY) {
		myGameWorld = gameWorld;
		this.midPointY = midPointY;
		this.myMenuButtons = ((MyInputHandler) Gdx.input.getInputProcessor()).getMenuButtons();

		myCam = new OrthographicCamera();
		myCam.setToOrtho(true, 136, gameHeight);

		mySpriteBatch = new SpriteBatch();
		myShapeRenderer = new ShapeRenderer();

		mySpriteBatch.setProjectionMatrix(myCam.combined);
		myShapeRenderer.setProjectionMatrix(myCam.combined);

		initGameObjects();
		initAssets();
		setupTweens();
	}

	private void setupTweens() {
		Tween.registerAccessor(Value.class, new ValueAccessor());
		myTweenManager = new TweenManager();
		Tween.to(alpha, -1, .5f).target(0).ease(TweenEquations.easeOutQuad).start(myTweenManager);
	}

	private void initGameObjects() {
		myBird = myGameWorld.getBird();
		myScroller = myGameWorld.getScrollHandler();
		myFrontGrass = myScroller.getMyFrontGrass();
		myBackGrass = myScroller.getMyBackGrass();
		myPipe1 = myScroller.getMyPipe1();
		myPipe2 = myScroller.getMyPipe2();
		myPipe3 = myScroller.getMyPipe3();
	}

	private void initAssets() {
		myBG = MyAssetLoader.myBg;
		myGrass = MyAssetLoader.myGrass;
		myBirdAnimation = MyAssetLoader.myBirdAnimation;
		myBirdMid = MyAssetLoader.myBird;
		//myBirdUp = MyAssetLoader.myBirdUp;
		//myBirdDown = MyAssetLoader.myBirdDown;
		mySkullDown = MyAssetLoader.mySkullDown;
		mySkullUp = MyAssetLoader.mySkullUp;
		myBar = MyAssetLoader.myBar;
	}

	public void render(float delta, float myRunTime) {

		// 1. We draw a black background. This prevents flickering.
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		myShapeRenderer.begin(ShapeType.Filled);
		myShapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
		myShapeRenderer.rect(0, 0, 136, midPointY + 66);
		myShapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
		myShapeRenderer.rect(0, midPointY + 66, 136, 11);
		myShapeRenderer.end();

		mySpriteBatch.begin();
		mySpriteBatch.disableBlending();
		mySpriteBatch.draw(myBG, 0, midPointY + 23, 136, 43);

		drawPipes();
		drawGrass();
		mySpriteBatch.end();

		myShapeRenderer.begin(ShapeType.Filled);
		myShapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
		myShapeRenderer.rect(0, midPointY + 77, 136, 52);
		myShapeRenderer.end();

		mySpriteBatch.begin();
		mySpriteBatch.enableBlending();

		drawSkulls();
		
		if(myGameWorld.isRunning()){
			drawBird(myRunTime);
			drawScore();
		} else if (myGameWorld.isReady()) {
			drawBird(myRunTime);
			drawScore();
		} else if (myGameWorld.isMenu()){
			drawBirdCentered(myRunTime);
			drawMenuUI();
		} else if(myGameWorld.isGameOver()){
			drawBird(myRunTime);
			drawScore();
		} else if(myGameWorld.isHighScore()){
			drawBird(myRunTime);
			drawScore();
		} 		
		
		mySpriteBatch.end();
		drawTransition(delta);
	}

	public void drawGrass() {
		mySpriteBatch.draw(myGrass, myFrontGrass.getX(), myFrontGrass.getY(),
				myFrontGrass.getWidth(), myFrontGrass.getHeight());
		mySpriteBatch.draw(myGrass, myBackGrass.getX(), myBackGrass.getY(),
				myBackGrass.getWidth(), myBackGrass.getHeight());
	}

	public void drawSkulls() {

		mySpriteBatch.draw(mySkullUp, myPipe1.getX() - 1, myPipe1.getY()
				+ myPipe1.getHeight() - 14, 24, 14);
		mySpriteBatch.draw(mySkullDown, myPipe1.getX() - 1, myPipe1.getY()
				+ myPipe1.getHeight() + 45, 24, 14);

		mySpriteBatch.draw(mySkullUp, myPipe2.getX() - 1, myPipe2.getY()
				+ myPipe2.getHeight() - 14, 24, 14);
		mySpriteBatch.draw(mySkullDown, myPipe2.getX() - 1, myPipe2.getY()
				+ myPipe2.getHeight() + 45, 24, 14);

		mySpriteBatch.draw(mySkullUp, myPipe3.getX() - 1, myPipe3.getY()
				+ myPipe3.getHeight() - 14, 24, 14);
		mySpriteBatch.draw(mySkullDown, myPipe3.getX() - 1, myPipe3.getY()
				+ myPipe3.getHeight() + 45, 24, 14);
	}

	public void drawPipes() {

		mySpriteBatch.draw(myBar, myPipe1.getX(), myPipe1.getY(),
				myPipe1.getWidth(), myPipe1.getHeight());
		mySpriteBatch.draw(myBar, myPipe1.getX(),
				myPipe1.getY() + myPipe1.getHeight() + 45, myPipe1.getWidth(),
				midPointY + 66 - (myPipe1.getHeight() + 45));

		mySpriteBatch.draw(myBar, myPipe2.getX(), myPipe2.getY(),
				myPipe2.getWidth(), myPipe2.getHeight());
		mySpriteBatch.draw(myBar, myPipe2.getX(),
				myPipe2.getY() + myPipe2.getHeight() + 45, myPipe2.getWidth(),
				midPointY + 66 - (myPipe2.getHeight() + 45));

		mySpriteBatch.draw(myBar, myPipe3.getX(), myPipe3.getY(),
				myPipe3.getWidth(), myPipe3.getHeight());
		mySpriteBatch.draw(myBar, myPipe3.getX(),
				myPipe3.getY() + myPipe3.getHeight() + 45, myPipe3.getWidth(),
				midPointY + 66 - (myPipe3.getHeight() + 45));
	}
	
	private void drawBirdCentered(float myRunTime){
		mySpriteBatch.draw(myBirdAnimation.getKeyFrame(myRunTime), 59, myBird.getY() -15, myBird.getWidth() / 2.0f, myBird.getHeight() / 2.0f, myBird.getWidth(), myBird.getHeight(), 1, 1, myBird.getRotation());
	}
	
	private void drawBird(float myRunTime){
		   if (myBird.shouldntFlap()) {
	            mySpriteBatch.draw(myBirdMid, myBird.getX(), myBird.getY(),
	                    myBird.getWidth() / 2.0f, myBird.getHeight() / 2.0f,
	                    myBird.getWidth(), myBird.getHeight(), 1, 1, myBird.getRotation());

	        } else {
	        	mySpriteBatch.draw(myBirdAnimation.getKeyFrame(myRunTime), myBird.getX(),
	                    myBird.getY(), myBird.getWidth() / 2.0f,
	                    myBird.getHeight() / 2.0f, myBird.getWidth(), myBird.getHeight(),
	                    1, 1, myBird.getRotation());
	        }
	}
	
	private void drawMenuUI(){
		mySpriteBatch.draw(MyAssetLoader.myZbLogo, 136 / 2 -56, midPointY - 50, MyAssetLoader.myZbLogo.getRegionWidth() / 1.2f, MyAssetLoader.myZbLogo.getRegionHeight() / 1.2f);
		
		for (MySimpleButton button : myMenuButtons){
			button.draw(mySpriteBatch);
		}
	}
	
	private void drawScore(){
		int length = ("" + myGameWorld.getScore()).length();
		MyAssetLoader.myFontShadow.draw(mySpriteBatch, "" + myGameWorld.getScore(), 68 - (3 * length),midPointY - 82);
		MyAssetLoader.myFont.draw(mySpriteBatch, "" + myGameWorld.getScore(), 68 - (3 * length),midPointY - 83);
	}
	
	private void drawTransition(float delta){
		if(alpha.getValue() > 0){
			myTweenManager.update(delta);
			Gdx.gl.glEnable(GL20.GL_BLEND);
			Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			myShapeRenderer.begin(ShapeType.Filled);
			myShapeRenderer.setColor(1,1,1,alpha.getValue());
			myShapeRenderer.rect(0, 0, 136, 300);
			myShapeRenderer.end();
			Gdx.gl.glDisable(GL20.GL_BLEND);
		}
	}
	
}
