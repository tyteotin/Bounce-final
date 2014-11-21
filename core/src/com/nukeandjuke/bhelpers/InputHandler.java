package com.nukeandjuke.bhelpers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.nukeandjuke.gameobjects.Ball;
import com.nukeandjuke.gameworld.GameWorld;
import com.nukeandjuke.ui.SimpleButton;

public class InputHandler implements InputProcessor {
	private Ball myBall;
	private GameWorld myWorld;
	
	private List<SimpleButton> menuButtons;
	
	private SimpleButton playButton, scoreButton, novaButton;
	private float scaleFactorX;
	private float scaleFactorY;
	
	private int gameWidth = 136;
	private double ratio = (double)Gdx.graphics.getHeight()/(double)Gdx.graphics.getWidth();
	private double ratioX = Gdx.graphics.getWidth()/gameWidth;
	private int gameHeight = (int)((double)gameWidth * ratio);
	private double ratioY = (double)Gdx.graphics.getHeight()/(double)gameHeight;
	private int buttonX = 0;
	private int buttonY = 0;
	private int buttonYscore = 0;
	private int buttonYnova = 0;
	class TouchInfo {
		public float touchX = 0;
		public float touchY = 0;
	}
	//private TouchInfo touchCoord;
	
	public InputHandler(GameWorld myWorld){
		this.myWorld = myWorld;
		myBall = myWorld.getBall();
		
		int midPointY = myWorld.getMidPointY();
		menuButtons = new ArrayList<SimpleButton>();
		buttonX = 136 / 2;
		buttonY = midPointY + 20 + 8;
		buttonYscore = midPointY + 50 + 8;
		buttonYnova = midPointY + 70 + 8;
		// clean up
		playButton = new SimpleButton(
				136 / 2 - (AssetLoader.playButtonUp.getRegionWidth() / 2),
				midPointY + 20, 29, 16, AssetLoader.playButtonUp,
				AssetLoader.playButtonDown);
		scoreButton = new SimpleButton(
				136/2 - (AssetLoader.scoreButtonUp.getRegionWidth() / 2),
				midPointY + 50, 29, 16, AssetLoader.scoreButtonUp,
				AssetLoader.scoreButtonDown);
		novaButton = new SimpleButton(
				136/2 - (AssetLoader.novaButtonUp.getRegionWidth() / 2),
				midPointY + 70, 29, 16, AssetLoader.novaButtonUp, 
				AssetLoader.novaButtonDown);
		
		menuButtons.add(playButton);
		menuButtons.add(scoreButton);
		menuButtons.add(novaButton);
		System.out.println(menuButtons);
	}
	
	private int scaleX(int screenX){
		return (int) (screenX / scaleFactorX);
	}
	
	private int scaleY(int screenY){
		return (int) (screenY / scaleFactorY);
	}
	
	public List<SimpleButton> getMenuButtons(){
		return menuButtons;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		
		if(keycode == Keys.SPACE){
			if(myWorld.isMenu()){
				myWorld.ready();
			} else if (myWorld.isReady()){
				myWorld.start();
			}
			
			//myBall.onClick(0, 0);
			if(myWorld.isGameOver() || myWorld.isHighScore()){
				myWorld.restart();
			}
		}
		
		return false;
		
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(myWorld.isMenu()){
			/*
			System.out.println(screenX/ratioX);
			System.out.println(screenY/ratioY);
			System.out.println(buttonX - 15);
			System.out.println(buttonX + 15);
			System.out.println(buttonY - 8);
			System.out.println(buttonY + 8);
			System.out.println(ratioY);
			System.out.println(gameHeight);
			*/
			if((float)screenX/ratioX >= buttonX - 15 && (float)screenX/ratioX <= buttonX + 15 &&
				(float)screenY/ratioY >= buttonY - 8 && (float)screenY/ratioY <= buttonY + 8)
				myWorld.ready();
			else if ((float)screenX/ratioX >= buttonX - 15 && (float)screenX/ratioX <= buttonX + 15 &&
					(float)screenY/ratioY >= buttonYscore - 8 && (float)screenY/ratioY <= buttonYscore + 8)
				myWorld.best();
			else if ((float)screenX/ratioX >= buttonX - 15 && (float)screenX/ratioX <= buttonX + 15 &&
					(float)screenY/ratioY >= buttonYnova - 8 && (float)screenY/ratioY <= buttonYnova + 8)
				myWorld.nova();
		}
		else if (myWorld.isBest() || myWorld.isNova()){
			myWorld.menu();
		}
		else if (myWorld.isReady()){
			myWorld.start();
		}
		//System.out.println(myBall.getVelocity());

	
	//	if((float)(screenX/ratio) >= myBall.getX() && (float)(screenX/ratio) <= myBall.getX() + myBall.getWidth() && 
	//		(float)(screenY/ratio) >= myBall.getY() && (float)(screenY/ratio) <= myBall.getY() + myBall.getHeight()) {
	//		System.out.println("hit");
		else if(myWorld.isRunning()){
			myBall.onClick(screenX, screenY);
		
		} else if (myWorld.isGameOver() || myWorld.isHighScore()){
			myWorld.restart();
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);
		
		if(myWorld.isMenu()){
			if(playButton.isTouchUp(screenX, screenY)){
				myWorld.ready();
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
