package com.nukeandjuke.gameworld;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.nukeandjuke.bhelpers.AssetLoader;
import com.nukeandjuke.gameobjects.Ball;
import com.nukeandjuke.gameobjects.BlackHole;
import com.nukeandjuke.gameobjects.Meteor;

public class GameWorld {
	
	// used variables
	// Must take into account radius of circle into calculations
	
	private int moveLeftBool = 0;
	private int moveUpBool = 0;
	private int midPointY;
	private Ball ball;
	private int score = 0;
	private float runTime = 0;
	private GameState currentState;
	private BlackHole[] holeArray = new BlackHole[3];
	private Meteor[] meteorArr = new Meteor[8];
	private int randNum;
	private int gameWidth = 136;
	private float screenRatio = (float) Gdx.graphics.getHeight()
			/ (float) Gdx.graphics.getWidth();
	private int gameHeight = (int) (gameWidth * screenRatio);
	
	private BlackHole hole1 = new BlackHole(12, 12, 12);
	private BlackHole hole2 = new BlackHole(14, gameHeight - 14, 16);
	private BlackHole hole3 = new BlackHole(136-15, (gameHeight/2) + 5, 14);
	/*
	private BlackHole hole4 = new BlackHole(136-15, (gameHeight/2) + 5, 14);
	private BlackHole hole5 = new BlackHole(136-15, (gameHeight/2) + 5, 14);
	private BlackHole hole6 = new BlackHole(136-15, (gameHeight/2) + 5, 14);
	*/
	private Random rnd = new Random();
	private int rndNum;
	
	private GameRenderer renderer;

	private Meteor meteor1 = new Meteor(50, 0, 40);
	private Meteor meteor2 = new Meteor(55, 0, 40);
	private Meteor meteor3 = new Meteor(136, 120, 40);
	private Meteor meteor4 = new Meteor(136, 125, 40);
	
	private Meteor fire = new Meteor(40, gameHeight/2 + 67, 40);
	private Meteor moon = new Meteor(136 - 30, gameHeight/2 - 35, 20);
	private Meteor light = new Meteor(0 + 20, gameHeight/2 - 35, 20);
	private Meteor flux = new Meteor(100, gameHeight/2 + gameHeight/3 +10 + 30, 40);
	
	public enum GameState {
		MENU, READY, RUNNING, GAMEOVER, HIGHSCORE, BEST, NOVA
	}

	public GameWorld() {
		//this.midPointY = midPointY;
		currentState = GameState.MENU;
		ball = new Ball(0, 0, 16, 16);

		meteorArr[0] = meteor1; 
		meteorArr[1] = meteor2;
		meteorArr[2] = meteor3;
		meteorArr[3] = meteor4;
		meteorArr[4] = fire;
		meteorArr[5] = moon;
		meteorArr[6] = light;
		meteorArr[7] = flux;
		
		holeArray[0]=hole1;
		holeArray[1]=hole2;
		holeArray[2]=hole3;
	}

	public void update(float delta) {
		runTime += delta;
		
		switch (currentState) {
		case READY:
		case MENU:
		//case MENU:
			updateReady(delta);
			break;
		case RUNNING:
			updateRunning(delta);
			break;
		default:
			break;
		}
	}
	
	
	private void updateReady(float delta) {
		ball.updateReady(runTime);
		
	}
	
	public void updateRunning(float delta) {
		// up = negative; down = positive
		if(delta > 0.15f){
			delta = 0.15f;
		}
		
		if (ball.isMissed() == false) {
			meteor1.updateMovement(1);
			meteor2.updateMovement(2);
			meteor3.updateMovement(3);
			
			ball.update(delta);
			if (ball.isHit() == true) {
				addScore(1);
				ball.clearHit();
			}
			
			if(ball.getHitCount() >= 2 && ball.getHitCount() % 5 == 0){
				rndNum = 0;
				if(rndNum == 0){
					hole1.setLoc(12, 12);
					hole2.setLoc(5, gameHeight-17);
					hole3.setLoc(136-17, gameHeight/2);
					rndNum = 1;
					ball.setHole(hole1, 1);
					ball.setHole(hole2, 2);
					ball.setHole(hole3, 3);
				}
				else {
					hole1.setLoc(14, 10);
					hole2.setLoc(136-10, 14);
					hole3.setLoc(12, gameHeight-14);
					rndNum = 0;
					ball.setHole(hole1, 1);
					ball.setHole(hole2, 2);
					ball.setHole(hole3, 3);
				}
				
			}
			
		} else {
			// freeze up screen, call score, display msgs
			currentState = GameState.GAMEOVER;
			
			if (score > AssetLoader.getHighScore()) {
				AssetLoader.setHighScore(score);
				currentState = GameState.HIGHSCORE;
			}
		}

	}
	
	public Ball getBall() {
		return ball;
	}
	
	public int getMidPointY(){
		return gameHeight/2;
	}
	
	public int getScore() {
		return score;
	}
	
	public void addScore(int increment) {
		score += increment;
	}
	
	public void start(){
		currentState = GameState.RUNNING;
	}
	
	public void ready(){
		currentState = GameState.READY;
		
		// crashed
		//renderer.prepareTransition(0, 0, 0, 1f);
	}
	
	public void menu(){
		currentState = GameState.MENU;
	}
	
	public void restart(){
		//currentState = GameState.READY;
		score = 0;
		
		moveLeftBool = 0;
		moveUpBool = 0;
		ball.onRestart();
		menu();
	}
	
	public void best(){
		currentState = GameState.BEST;
	}
	
	public boolean isReady(){
		 return currentState == GameState.READY;
	}
	
	public boolean isGameOver(){
		return currentState == GameState.GAMEOVER;
	}
	
	public boolean isHighScore() {
		return currentState == GameState.HIGHSCORE;
	}
	
	public boolean isMenu(){
		return currentState == GameState.MENU;
		//return false;
	}
	
	public boolean isRunning() {
		return currentState == GameState.RUNNING;
	}
	
	public boolean isBest(){
		return currentState == GameState.BEST;
	}

	public void setRenderer(GameRenderer renderer){
		this.renderer = renderer;
	}
	
	public BlackHole[] getHoleArray(){
		return holeArray;
	}
	
	public Meteor[] getMeteorArray(){
		return meteorArr;
	}
	
	public void setRand(int num){
		randNum = num;
	}
	
	public void nova(){
		currentState = GameState.NOVA;
	}
	
	public boolean isNova(){
		return currentState == GameState.NOVA;
	}
	
	public int getGameH(){
		return gameHeight;
	}
	
	public int getGameW(){
		return gameWidth;
	}
}
