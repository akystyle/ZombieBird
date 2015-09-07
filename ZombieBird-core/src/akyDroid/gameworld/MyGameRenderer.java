package akyDroid.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MyGameRenderer {

	MyGameWorld myGameWorld;
	OrthographicCamera myCam;
	ShapeRenderer myShapeRenderer;
	
	public MyGameRenderer(MyGameWorld gameWorld){
		myGameWorld = gameWorld;
		myCam = new OrthographicCamera();
		myCam.setToOrtho(true, 136, 204);
		myShapeRenderer = new ShapeRenderer();
		myShapeRenderer.setProjectionMatrix(myCam.combined);
	}
	
	public void render(){
		Gdx.app.log("MyGameRenderer", "Render called");

		// 1. We draw a black background. This prevents flickering.
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


	}
}
