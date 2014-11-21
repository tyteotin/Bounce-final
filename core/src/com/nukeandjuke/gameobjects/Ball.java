package com.nukeandjuke.gameobjects;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Ball {
	private Vector2 position;
	
	private Vector2 vertical;
	private Vector2 horizontal;
	Random randomInt = new Random();
	Random randomFloat = new Random();
	float randFloatUnit;
	float randFloat;
	int randInt;

	private float rotation; // For handling bird rotation
	private int width;
	private int height;
	private int moveUpBool = 0;
	private int moveLeftBool = 0;
	private int gameWidth = 136;
	private double scaleVelo = 0;
	private double scaleVelo_X = 0;
	private double scaleVelo_Y = 0;

	private float screenRatio = (float) Gdx.graphics.getHeight()
			/ (float) Gdx.graphics.getWidth();
	private float xRatio = (float) Gdx.graphics.getWidth() / (float) gameWidth;
	private float yRatio;
	private boolean miss = false;
	private boolean hitCheck = false;
	private int gameHeight;
	private int hitCount = 0;
	private int randNum = -1;
	private int centerScreenX;
	private int centerScreenY;
	private int centerRangeX;
	private int centerRangeY;
	
	private BlackHole hole1, hole2, hole3;
	private int ballCenterX, ballCenterY;
	
	private int hole1CenterX;
	private int hole1CenterY;
	
	private int hole2CenterX;
	private int hole2CenterY;
	
	private int hole3CenterX;
	private int hole3CenterY;
	
	private float originalY;
	
	public Ball(float x, float y, int width, int height) {
		this.width = width;
		this.height = height;
		this.originalY = y;
		
		gameHeight = (int) (gameWidth * screenRatio);

		centerScreenX = gameWidth / 2;
		centerScreenY = gameHeight / 2;
		centerRangeX = (gameWidth * (2 / 3)) / 2;
		centerRangeY = (gameHeight * (2 / 3)) / 2;
		position = new Vector2(centerScreenX, centerScreenY);
		yRatio = (float) Gdx.graphics.getHeight() / (float) gameHeight;
		horizontal = new Vector2(0, gameWidth);
		// gameHeight = screenHeight / (screenWidth / gameWidth);
		vertical = new Vector2(0, gameHeight);
	}

	private void normMovement() {
		if (position.y < vertical.y - (height / 2) && moveUpBool == 0) {
			position.y += scaleVelo_Y;
			if (position.y >= vertical.y - (height))
				moveUpBool = 1;
		} else if (position.y > 0 && moveUpBool == 1) {
			position.y -= scaleVelo_Y;
			if (position.y <= 0)
				moveUpBool = 0;
		}

		if (position.x < 137 - (width / 2) && moveLeftBool == 0) {
			position.x += scaleVelo_X;
			if (position.x >= 137 - (width))
				moveLeftBool = 1;
		} else if (position.x > 0 && moveLeftBool == 1) {
			position.x -= scaleVelo_X;
			if (position.x <= 0)
				moveLeftBool = 0;
		}
	}

	public void update(float delta) {

		if (hitCount >= 0 && hitCount < 40) {
			normMovement();
			// Gdx.app.log("randNum ", randNum + "");
		}
		else if (hitCount >= 40 && hitCount < 60) {
			if (randNum > 0 && randNum <= 2) {
				scaleVelo_X = scaleVelo_Y / 1.2;
				
			} else if (randNum > 2 && randNum <= 6) {
				scaleVelo_Y = scaleVelo_X / 1.2;
				
			} else if (randNum > 6 && randNum <= 8) {
				scaleVelo_X = scaleVelo;
				scaleVelo_Y = scaleVelo;
			}
			normMovement();
		}
		else if (hitCount >= 60 && hitCount < 80) {
			if (randNum > 0 && randNum <= 2) {
				randInt = randomInt.nextInt(1);
				randFloat = randomFloat.nextFloat();
				if (randInt == 0) {
					randFloatUnit = (float)2.2;
				} else
					randFloatUnit = (float)3.2;

				scaleVelo_X = randFloatUnit + randFloat;
				
			} else if (randNum > 2 && randNum <= 5) {
				randInt = randomInt.nextInt(1);
				randFloat = randomFloat.nextFloat();
				if (randInt == 0) {
					randFloatUnit = (float)2.2;
				} else
					randFloatUnit = (float)3.2;

				scaleVelo_Y = randFloatUnit + randFloat;
				
			} else if (randNum > 5 && randNum <= 8) {
				scaleVelo_X = scaleVelo;
				scaleVelo_Y = scaleVelo;
			}
			normMovement();
		}
		else if (hitCount >= 80 && hitCount < 120) {
			/*
			if (randNum > 0 && randNum <= 2) {
				randInt = randomInt.nextInt(1);
				randFloat = randomFloat.nextFloat();
				if (randInt == 0) {
					randFloatUnit = (float)2.2;
				} else
					randFloatUnit = (float)3.2;

				scaleVelo_X = scaleVelo / (randFloat + randFloatUnit);
				scaleVelo_Y = scaleVelo + randFloat;
				
			} else if (randNum > 2 && randNum <= 5) {
				randInt = randomInt.nextInt(1);
				randFloat = randomFloat.nextFloat();
				if (randInt == 0) {
					randFloatUnit = (float)2.2;
				} else
					randFloatUnit = (float)3.2;

				scaleVelo_Y = scaleVelo / (randFloatUnit + randFloat);
				scaleVelo_X = scaleVelo + randFloat;
				
			} else if (randNum > 5 && randNum <= 8) {
				scaleVelo_X = scaleVelo;
				scaleVelo_Y = scaleVelo;
			}
			*/
			randomMovement();
			normMovement();
		}
		else if(hitCount >= 120){
			randomMovement();
			normMovement();
			ballCenterX = (int)position.x + width/2;
			ballCenterY = (int)position.y + width/2;
			if(hole1CenterX >= ballCenterX - 20 && hole1CenterX <= ballCenterX + 20 &&
				hole1CenterY >= ballCenterY - 20 && hole1CenterY <= ballCenterY + 20){
				position.x = randNum*2 + gameWidth/2;
				position.y = randNum*2 + gameHeight/2;
			}
			else if(hole2CenterX >= ballCenterX - 40 && hole2CenterX <= ballCenterX + 40 &&
					gameHeight - 8 <= ballCenterY){
					position.x = randNum*2 + gameWidth/2;
					position.y = randNum*2 + gameHeight/2;
			}
			/*
			else if(gameWidth - 8 <= ballCenterX &&
					hole3CenterY >= ballCenterY - 30 && hole3CenterY <= ballCenterY + 30){
					position.x = randNum*2 + gameWidth/2;
					position.y = randNum*2 + gameHeight/2;
					
			}*/
		//Gdx.app.log("h1X ", hole1CenterX + "");
		//Gdx.app.log("h2X ", hole2CenterX + "");
		//Gdx.app.log("h3X ", hole3CenterX + "");
		}
	}
	
	private void randomMovement(){
		if (randNum > 0 && randNum <= 2) {
			randInt = randomInt.nextInt(1);
			randFloat = randomFloat.nextFloat();
			if (randInt == 0) {
				randFloatUnit = (float)2.2;
			} else
				randFloatUnit = (float)3.2;

			scaleVelo_X = scaleVelo / (randFloat + randFloatUnit);
			scaleVelo_Y = scaleVelo + randFloat;
			
		} else if (randNum > 2 && randNum <= 5) {
			randInt = randomInt.nextInt(1);
			randFloat = randomFloat.nextFloat();
			if (randInt == 0) {
				randFloatUnit = (float)2.2;
			} else
				randFloatUnit = (float)3.2;

			scaleVelo_Y = scaleVelo / (randFloatUnit + randFloat);
			scaleVelo_X = scaleVelo + randFloat;
			
		} else if (randNum > 5 && randNum <= 8) {
			scaleVelo_X = scaleVelo;
			scaleVelo_Y = scaleVelo;
		}
	}
	
	private boolean centerScreenCheck() {
		if (position.x > centerScreenX - centerRangeX
				&& position.x < centerScreenX + centerRangeX
				&& position.y > centerScreenY - centerRangeY
				&& position.y < centerScreenY + centerRangeY) {
			return true;
		}
		return false;
	}

	private void movement() {
		// Random randNum = new Random();
		// int randNum = random
	}
	
	//changed day 12
	public void updateReady(float runTime){
		//position.y = 2 * (float) Math.sin(7 * runTime) + originalY;
		position.x = centerScreenX - 8;
		position.y = centerScreenY;
	}
	
	public void onClick(int screenX, int screenY) {
		if ((float) (screenX / xRatio) >= position.x - 5
				&& (float) (screenX / xRatio) <= position.x + width + 5
				&& (float) (screenY / yRatio) >= position.y - 5
				&& (float) (screenY / yRatio) <= position.y + height + 5) {
			//System.out.println("hit");
			hit();
			setHit();
			//Gdx.app.log("randNum ", randNum + "");
		} else {
			miss();
			/*
			 * Gdx.app.log("Screen Resolution", "w " +
			 * Integer.toString(Gdx.graphics.getHeight())
			 * 
			 * + " x h " + Integer.toString(Gdx.graphics.getWidth()));
			 * Gdx.app.log("Game height", "game h" +
			 * Integer.toString(gameHeight));
			 */
			// insert log for output check
			/*
			 * Gdx.app.log("hit x", "x " + Float.toString(screenX/xRatio));
			 * Gdx.app.log("hit y", "y " + Float.toString(screenY/yRatio));
			 * Gdx.app.log("ball x", "x " + Float.toString(position.x - 5));
			 * Gdx.app.log("adjust ball x", " " + Float.toString(position.x +
			 * width + 5)); Gdx.app.log("ball y", "y " +
			 * Float.toString(position.y - 5)); Gdx.app.log("adjust ball y", " "
			 * + Float.toString(position.y + width + 5));
			 * Gdx.app.log("Game height", "game h" +
			 * Integer.toString(gameHeight)); Gdx.app.log("xRatio", " " +
			 * Float.toString(xRatio)); Gdx.app.log("yRatio", " " +
			 * Float.toString(yRatio)); Gdx.app.log("screenRatio", " " +
			 * Float.toString(screenRatio));
			 */
			//Gdx.app.log("rand", " " + Integer.toString(randNum));
		}
	}

	public void onRestart() {
		moveUpBool = 0;
		moveLeftBool = 0;
		scaleVelo = 0;
		scaleVelo_X = 0;
		scaleVelo_Y = 0;
		miss = false;
		hitCheck = false;
		position.x = centerScreenX;
		position.y = centerScreenY;
		hitCount = 0;
	}

	public float getX() {
		return position.x;
	}

	public float getY() {
		return position.y;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}
	
	public String getCredit(){
		return ("bn");
	}
	
	public float getRotation() {
		return rotation;
	}

	public float getBallRangeX() {
		return position.x + width;
	}

	public float getBallRangeY() {
		return position.y + height;
	}

	public double getVelocity() {
		return scaleVelo;
	}

	public void hit() {
		hitCount++;

		if (scaleVelo <= 1.0) {
			scaleVelo = scaleVelo + 0.5;
			scaleVelo_X = scaleVelo_X + 0.5;
			scaleVelo_Y = scaleVelo_Y + 0.5;
		} else if (scaleVelo >= 2.2) {
			scaleVelo = 2.2;
			scaleVelo_X = 2.2;
			scaleVelo_Y = 2.2;
		} else {
			scaleVelo = scaleVelo + 0.2;
			scaleVelo_X = scaleVelo_X + 0.2;
			scaleVelo_Y = scaleVelo_Y + 0.2;
		}

		// uncomment for hit sound
		// AssetLoader.hit.play();
	}

	public void miss() {
		System.out.println("miss");
		miss = true;
	}

	public boolean isMissed() {
		return miss;
	}

	public boolean isHit() {
		return hitCheck;
	}

	public void setHit() {
		hitCheck = true;
	}

	public void clearHit() {
		hitCheck = false;
	}
	
	public int getHitCount(){
		return hitCount;
	}
	
	public void setRand(int num) {
		randNum = num;
	}
	
	public void setHole(BlackHole hole, int number){
		if(number == 1){
			hole1 = hole;
			hole1CenterX = hole1.getX() + hole1.getRad();
			hole1CenterY = hole1.getY() + hole1.getRad();
		}
		else if (number == 2){
			hole2 = hole;
			hole2CenterX = hole2.getX() + hole2.getRad();
			hole2CenterY = hole2.getY() + hole2.getRad();
		}
		else{
			hole3 = hole;
			hole3CenterX = hole3.getX() + hole3.getRad();
			hole3CenterY = hole3.getY() + hole3.getRad();
		}
	}
}
