package akyDroid.gameworld;

import akyDroid.frameworkhelpers.MyAssetLoader;
import akyDroid.gameobjects.MyBird;

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

	TextureRegion myBG, myGrass, myBirdMid, myBirdUp, myBirdDown, mySkullUp,
			mySkullDown, myBar;
	Animation myBirdAnimation;

	int midPointY, gameHeight;

	public MyGameRenderer(MyGameWorld gameWorld, int gameHeight, int midPointY) {

		this.gameHeight = gameHeight;
		this.midPointY = midPointY;

		myGameWorld = gameWorld;
		myCam = new OrthographicCamera();
		myCam.setToOrtho(true, 136, gameHeight);

		mySpriteBatch = new SpriteBatch();
		myShapeRenderer = new ShapeRenderer();

		mySpriteBatch.setProjectionMatrix(myCam.combined);
		myShapeRenderer.setProjectionMatrix(myCam.combined);

		initGameObjects();
		initAssets();
	}

	private void initGameObjects() {
		myBird = myGameWorld.getBird();
	}

	private void initAssets() {
		myBG = MyAssetLoader.myBg;
		myGrass = MyAssetLoader.myGrass;
		myBirdAnimation = MyAssetLoader.myBirdAnimation;
		myBirdMid = MyAssetLoader.myBird;
		myBirdUp = MyAssetLoader.myBirdUp;
		myBirdDown = MyAssetLoader.myBirdDown;
		mySkullDown = MyAssetLoader.mySkullDown;
		mySkullUp = MyAssetLoader.mySkullUp;
		myBar = MyAssetLoader.myBar;
	}

	public void render(float runTime) {

		// 1. We draw a black background. This prevents flickering.
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		myShapeRenderer.begin(ShapeType.Filled);
		myShapeRenderer.setColor(55 / 255.0f, 80 / 255.0f, 100 / 255.0f, 1);
		myShapeRenderer.rect(0, 0, 136, midPointY + 66);
		myShapeRenderer.setColor(111 / 255.0f, 186 / 255.0f, 45 / 255.0f, 1);
		myShapeRenderer.rect(0, midPointY + 66, 136, 11);
		myShapeRenderer.setColor(147 / 255.0f, 80 / 255.0f, 27 / 255.0f, 1);
		myShapeRenderer.rect(0, midPointY + 77, 136, 52);
		myShapeRenderer.end();

		mySpriteBatch.begin();
		mySpriteBatch.disableBlending();
		mySpriteBatch.draw(myBG, 0, midPointY + 23, 136, 43);

		mySpriteBatch.enableBlending();

		if (myBird.shouldntFlap()) {
			mySpriteBatch.draw(myBirdMid, myBird.getX(), myBird.getY(),
					myBird.getWidth() / 2.0f, myBird.getHeight() / 2.0f,
					myBird.getWidth(), myBird.getHeight(), 1, 1,
					myBird.getRotation());
		} else {
			mySpriteBatch.draw(myBirdAnimation.getKeyFrame(runTime), myBird.getX(), myBird.getY(),
					myBird.getWidth() / 2.0f, myBird.getHeight() / 2.0f,
					myBird.getWidth(), myBird.getHeight(), 1, 1,
					myBird.getRotation());
		}
		mySpriteBatch.end();
	}
}
