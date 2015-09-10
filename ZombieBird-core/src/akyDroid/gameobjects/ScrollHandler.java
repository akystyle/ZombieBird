package akyDroid.gameobjects;

import akyDroid.frameworkhelpers.MyAssetLoader;
import akyDroid.gameworld.MyGameWorld;

public class ScrollHandler {

	Grass myFrontGrass, myBackGrass;
	Pipe myPipe1,myPipe2,myPipe3;
	MyGameWorld myWorld;
	int score = 0;
	
	public static final int SCROLL_SPEED = -59;
	public static final int PIPE_GAP = 49;
	
	public ScrollHandler(MyGameWorld myWorld, float yPos){
		this.myWorld = myWorld;
		myFrontGrass = new Grass(0, yPos, 143, 11, SCROLL_SPEED);
		myBackGrass = new Grass(myFrontGrass.getTailX(), yPos, 143, 11, SCROLL_SPEED);

		myPipe1 = new Pipe(210, 0, 22, 60, SCROLL_SPEED,yPos);
		myPipe2 = new Pipe(myPipe1.getTailX() + PIPE_GAP, 0, 22, 70, SCROLL_SPEED,yPos);
		myPipe3 = new Pipe(myPipe2.getTailX() + PIPE_GAP, 0, 22, 60, SCROLL_SPEED,yPos);
	}
	
	public void update(float delta){
		
		myFrontGrass.update(delta);
		myBackGrass.update(delta);
		myPipe1.update(delta);
		myPipe2.update(delta);
		myPipe3.update(delta);
		
		if(myPipe1.isScrolledLeft())
			myPipe1.reset(myPipe3.getTailX() + PIPE_GAP);
		if(myPipe2.isScrolledLeft())
			myPipe2.reset(myPipe1.getTailX() + PIPE_GAP);		
		if(myPipe3.isScrolledLeft())
			myPipe3.reset(myPipe2.getTailX() + PIPE_GAP);
		
		if(myFrontGrass.isScrolledLeft())
			myFrontGrass.reset(myBackGrass.getTailX());
		if(myBackGrass.isScrolledLeft())
			myBackGrass.reset(myFrontGrass.getTailX());
	}
	
	public void stop(){
		myPipe1.stop();
		myPipe2.stop();
		myPipe3.stop();
		myFrontGrass.stop();
		myBackGrass.stop();
	}
	
	public boolean collides(MyBird myBird){
		
		if(!myPipe1.isScored() && myPipe1.getX() + (myPipe1.getWidth()/2) < myBird.getX() + myBird.getWidth()){
			addScore(1);
			myPipe1.setScored(true);
			MyAssetLoader.coin.play();
		} else if(!myPipe2.isScored() && myPipe2.getX() + (myPipe2.getWidth()/2) < myBird.getX() + myBird.getWidth()){
			addScore(1);
			myPipe2.setScored(true);
			MyAssetLoader.coin.play();
		} else if(!myPipe3.isScored() && myPipe3.getX() + (myPipe3.getWidth()/2) < myBird.getX() + myBird.getWidth()){
			addScore(1);
			myPipe3.setScored(true);
			MyAssetLoader.coin.play();
		}
		
		return (myPipe1.collides(myBird) || myPipe2.collides(myBird) || myPipe3.collides(myBird));
	}

	private void addScore(int i) {
		myWorld.addScore(i);
	}

	public Grass getMyFrontGrass() {
		return myFrontGrass;
	}

	public Grass getMyBackGrass() {
		return myBackGrass;
	}

	public Pipe getMyPipe1() {
		return myPipe1;
	}

	public Pipe getMyPipe2() {
		return myPipe2;
	}

	public Pipe getMyPipe3() {
		return myPipe3;
	}

	public void onRestart() {
		myFrontGrass.onRestart(0,SCROLL_SPEED);
		myBackGrass.onRestart( myFrontGrass.getTailX(),SCROLL_SPEED);
		myPipe1.onRestart(210,SCROLL_SPEED);
		myPipe2.onRestart(myPipe1.getTailX(),SCROLL_SPEED);
		myPipe3.onRestart(myPipe2.getTailX(),SCROLL_SPEED);
	}
}
