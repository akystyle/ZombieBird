package akyDroid.gameobjects;

public class ScrollHandler {

	Grass myFrontGrass, myBackGrass;
	Pipe myPipe1,myPipe2,myPipe3;
	
	public static final int SCROLL_SPEED = -59;
	public static final int PIPE_GAP = 49;
	
	public ScrollHandler(float yPos){
		myFrontGrass = new Grass(0, yPos, 143, 11, SCROLL_SPEED);
		myBackGrass = new Grass(myFrontGrass.getTailX(), yPos, 143, 11, SCROLL_SPEED);

		myPipe1 = new Pipe(210, 0, 22, 60, SCROLL_SPEED);
		myPipe2 = new Pipe(myPipe1.getTailX() + PIPE_GAP, 0, 22, 70, SCROLL_SPEED);
		myPipe3 = new Pipe(myPipe2.getTailX() + PIPE_GAP, 0, 22, 60, SCROLL_SPEED);
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
	
}
