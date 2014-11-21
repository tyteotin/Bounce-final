package com.nukeandjuke.gameworld;

import java.util.List;
import java.util.Random;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.nukeandjuke.bhelpers.AssetLoader;
import com.nukeandjuke.bhelpers.InputHandler;
import com.nukeandjuke.gameobjects.Ball;
import com.nukeandjuke.gameobjects.BlackHole;
import com.nukeandjuke.gameobjects.Meteor;
import com.nukeandjuke.tweenAccessors.Value;
import com.nukeandjuke.tweenAccessors.ValueAccessor;
import com.nukeandjuke.ui.SimpleButton;

public class GameRenderer {
	private GameWorld myWorld;
	private OrthographicCamera cam;
	private ShapeRenderer shapeRenderer;
	private SpriteBatch batcher;

	private int gameHeight;
	private float runTime;
	private int midPointY;
	// Objects
	private Ball ball;
	private BlackHole hole1, hole2, hole3;
	private Meteor meteor1, meteor2, meteor3, meteor4, fire, moon, light;
	private Meteor flux;
	// Assets
	private TextureRegion bg, grass, bckgrnd, scoreboard, star, noStar;
	private Animation ballAnimation;
	private Animation explosionAnimation;
	private Animation explosionAnimation1;
	private Animation bHole1Animation;
	private Animation fireAnimation;
	private Animation moonAnimation;
	private Animation lightAnimation;
	private Animation fluxAnimation;

	private TextureRegion ball1, ball2, ball3, ball4, ball5, ball6, ball7,
			ball8, retry, ready, gameOver, highScore, gameLogo;
	private TextureRegion skullUp, skullDown, bar;
	private TextureRegion blkHole;
	private TextureRegion fish;
	private boolean explosionCheck = true;
	private float stateTime = 0;
	private int blinkTimes = 0;
	private int randNum;
	private float deltaTime;
	private float countDown = 5;
	Random random = new Random();
	private int timeCountFrom1Sec = 0;

	// Tween engine related
	private TweenManager manager;
	private Value alpha = new Value();

	// Buttons
	private List<SimpleButton> menuButtons;

	private Color transitionColor;

	private TextureRegion meteorTxtr1, meteorTxtr2, meteorTxtr3;
	float time2 = 0;
	float time3 = 0;
	
	public enum renderMode {
		PLAY, NOVA
	}
	public void setDeltaTime(float delta) {
		deltaTime = delta;
	}
	
	private void initGameObjects() {
		ball = myWorld.getBall();
		hole1 = (myWorld.getHoleArray())[0];
		hole2 = (myWorld.getHoleArray())[1];
		hole3 = (myWorld.getHoleArray())[2];
		meteor1 = (myWorld.getMeteorArray())[0];
		meteor2 = (myWorld.getMeteorArray())[1];
		meteor3 = (myWorld.getMeteorArray())[2];
		meteor4 = (myWorld.getMeteorArray())[3];
		fire = (myWorld.getMeteorArray())[4];
		moon = (myWorld.getMeteorArray())[5];
		light = (myWorld.getMeteorArray())[6];
		flux = (myWorld.getMeteorArray())[7];
	}

	private void initAssets() {
		bg = AssetLoader.bg;
		bckgrnd = AssetLoader.bckgrnd;
		ballAnimation = AssetLoader.ballAnimation;
		explosionAnimation = AssetLoader.explosionAnimation;
		explosionAnimation1 = AssetLoader.explosionAnimation1;
		bHole1Animation = AssetLoader.bHole1Animation;
		fireAnimation = AssetLoader.fireAnimation;
		moonAnimation = AssetLoader.moonAnimation;
		lightAnimation = AssetLoader.lightAnimation;
		fluxAnimation = AssetLoader.fluxAnimation;
		ball1 = AssetLoader.ball1;
		ball2 = AssetLoader.ball2;
		ball3 = AssetLoader.ball3;
		ball4 = AssetLoader.ball4;
		ball5 = AssetLoader.ball5;
		ball6 = AssetLoader.ball6;
		ball7 = AssetLoader.ball7;
		ball8 = AssetLoader.ball8;
		skullUp = AssetLoader.skullUp;
		skullDown = AssetLoader.skullDown;
		bar = AssetLoader.bar;
		blkHole = AssetLoader.blkHole1;
		retry = AssetLoader.retry;
		ready = AssetLoader.ready;
		gameOver = AssetLoader.gameOver;
		gameLogo = AssetLoader.zbLogo;
		scoreboard = AssetLoader.scoreboard;
		noStar = AssetLoader.noStar;
		star = AssetLoader.star;
		meteorTxtr1 = AssetLoader.meteor1;
		meteorTxtr2 = AssetLoader.meteor2;
		meteorTxtr3 = AssetLoader.meteor3;
		fish = AssetLoader.fish;
	}

	public GameRenderer(GameWorld world, int gameHeight, int midPointY) {
		myWorld = world;
		this.midPointY = midPointY;
		cam = new OrthographicCamera();
		cam.setToOrtho(true, 136, gameHeight);
		this.gameHeight = gameHeight;
		this.menuButtons = ((InputHandler) Gdx.input.getInputProcessor())
				.getMenuButtons();
		shapeRenderer = new ShapeRenderer();
		shapeRenderer.setProjectionMatrix(cam.combined);
		batcher = new SpriteBatch();
		batcher.setProjectionMatrix(cam.combined);

		initGameObjects();
		initAssets();

		transitionColor = new Color();
		prepareTransition(255, 255, 255, 5);
		setupTweens();
	}

	private void drawBall(float runTime) {
		batcher.draw(ballAnimation.getKeyFrame(runTime), ball.getX(),
				ball.getY(), ball.getWidth() / 2.0f, ball.getHeight() / 2.0f,
				ball.getWidth(), ball.getHeight(), 1, 1, 0f);
	}

	private void drawMenuUI() {

		AssetLoader.shadow.draw(batcher, "Bouncebn", (136 / 2) - 42,
				midPointY / 2 + 10);
		AssetLoader.font.draw(batcher, "Bouncebn", (136 / 2) - (42 - 1),
				midPointY / 2 + 10);
		for (SimpleButton button : menuButtons)
			button.draw(batcher);
	}

	private void drawScoreboard() {
		batcher.draw(scoreboard, 22, midPointY - 30, 97, 37);
	
		batcher.draw(noStar, 25, midPointY - 15, 10, 10);
		batcher.draw(noStar, 37, midPointY - 15, 10, 10);
		batcher.draw(noStar, 49, midPointY - 15, 10, 10);
		batcher.draw(noStar, 61, midPointY - 15, 10, 10);
		batcher.draw(noStar, 73, midPointY - 15, 10, 10);
		
		if (myWorld.getScore() > 2) {
			batcher.draw(star, 73, midPointY - 15, 10, 10);
		}
		if (myWorld.getScore() > 17) {
			batcher.draw(star, 61, midPointY - 15, 10, 10);
		}
		if (myWorld.getScore() > 50) {
			batcher.draw(star, 49, midPointY - 15, 10, 10);
		}
		if (myWorld.getScore() > 80) {
			batcher.draw(star, 37, midPointY - 15, 10, 10);
		}
		if (myWorld.getScore() > 120) {
			batcher.draw(star, 25, midPointY - 15, 10, 10);
		}

		int length = ("" + myWorld.getScore()).length();

	}

	private void drawRetry() {
		batcher.draw(retry, 36, midPointY + 10, 66, 14);
	}

	private void drawReady() {
		batcher.draw(ready, 36, midPointY - 50, 68, 14);
	}

	private void drawGameOver() {
		batcher.draw(gameOver, 24, midPointY - 50, 92, 14);
	}

	private void drawScore() {
		String score = myWorld.getScore() + "";
		AssetLoader.shadow.draw(batcher, "" + myWorld.getScore(), (136 / 2)
				- (3 * score.length()), 12);
		AssetLoader.font.draw(batcher, "" + myWorld.getScore(), (136 / 2)
				- (3 * score.length() - 1), 11);
	}

	private void drawHighScore() {
		batcher.draw(highScore, 22, midPointY - 50, 96, 14);
	}

	private void setupTweens() {
		Tween.registerAccessor(Value.class, new ValueAccessor());
		manager = new TweenManager();
		Tween.to(alpha, -1, 0.5f).target(0).ease(TweenEquations.easeOutQuad)
				.start(manager);
	}

	public void render(float delta, float runTime) {
		// Gdx.app.log("GameScreen FPS", (deltaTime) + "");
		countDown -= deltaTime;
		// Gdx.app.log("countDown ", (countDown) + "");
		if (countDown <= 0) {

			randNum = random.nextInt(9);
			countDown = 1;
			ball.setRand(randNum);
			timeCountFrom1Sec++;
			if (timeCountFrom1Sec >= 5) {
				myWorld.setRand(randNum);
				timeCountFrom1Sec = 0;
			}
		}

		Gdx.gl.glClearColor(255, 255, 255, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batcher.begin();
		// Disable transparency
		// This is good for performance when drawing images that do not require
		// transparency.
		// The bird needs transparency, so we enable that again.
		batcher.disableBlending();
		batcher.draw(bckgrnd, 0, 0, 136, gameHeight);
		batcher.enableBlending();
		if (myWorld.isMenu()) {
			drawMenuUI();

		} else if (myWorld.isNova()) {
			drawUnlockedWorld(runTime, renderMode.NOVA);

		} else if (myWorld.isBest()) {
			// Gdx.app.log("GameScreen FPS", "");
			AssetLoader.shadow.draw(batcher, "Best Score:", 23, 106);
			AssetLoader.font.draw(batcher, "Best Score:", 22, 105);

			String highScore = AssetLoader.getHighScore() + "";

			AssetLoader.shadow.draw(batcher, highScore, (136 / 2)
					- (3 * highScore.length()), 128);
			AssetLoader.font.draw(batcher, highScore, (136 / 2)
					- (3 * highScore.length() - 1), 127);

		} else if (myWorld.isReady()) {
			explosionCheck = true;

			drawBall(0);

			AssetLoader.shadow.draw(batcher, "Double tap", (136 / 2) - 42,
					midPointY - 40);
			AssetLoader.font.draw(batcher, "Double tap", (136 / 2) - (42 - 1),
					75);

			AssetLoader.shadow
					.draw(batcher, ">", (136 / 2) - 22, midPointY + 1);
			AssetLoader.font.draw(batcher, ">", (136 / 2) - 21, midPointY);

			AssetLoader.shadow
					.draw(batcher, "<", (136 / 2) + 12, midPointY + 1);
			AssetLoader.font.draw(batcher, "<", (136 / 2) + 11, midPointY);

		} else if (myWorld.isRunning()) {
			
			drawUnlockedWorld(runTime, renderMode.PLAY);
			drawBall(runTime);
			drawScore();

		} else {
		
			if (myWorld.isGameOver()) {
				if (explosionCheck) {
					stateTime += Gdx.graphics.getDeltaTime();
				
					batcher.draw(explosionAnimation.getKeyFrame(stateTime),
							ball.getX(), ball.getY(), ball.getWidth() / 2.0f,
							ball.getHeight() / 2.0f, ball.getWidth(),
							ball.getHeight(), 1, 1, 0f);

					batcher.draw(explosionAnimation1.getKeyFrame(stateTime),
							ball.getX() - 7, ball.getY() - 5,
							ball.getWidth() / 2.0f, ball.getHeight() / 2.0f,
							27, 27, 1, 1, 0f);
		

					if (explosionAnimation.isAnimationFinished(stateTime)) {
						blinkTimes++;
						if (blinkTimes > 1) {
							explosionCheck = false;
							stateTime = 0;
						}

					}
				}

				AssetLoader.shadow.draw(batcher, "Game Over", 25, 56);
				AssetLoader.font.draw(batcher, "Game Over", 24, 55);

				AssetLoader.shadow.draw(batcher, "Best Score:", 23, 106);
				AssetLoader.font.draw(batcher, "Best Score:", 22, 105);

				String highScore = AssetLoader.getHighScore() + "";

				AssetLoader.shadow.draw(batcher, highScore, (136 / 2)
						- (3 * highScore.length()), 128);
				AssetLoader.font.draw(batcher, highScore, (136 / 2)
						- (3 * highScore.length() - 1), 127);

			} else if (myWorld.isHighScore()) {
				if (explosionCheck) {
					stateTime += Gdx.graphics.getDeltaTime();
			
					batcher.draw(explosionAnimation.getKeyFrame(stateTime),
							ball.getX(), ball.getY(), ball.getWidth() / 2.0f,
							ball.getHeight() / 2.0f, ball.getWidth(),
							ball.getHeight(), 1, 1, 0f);

					batcher.draw(explosionAnimation1.getKeyFrame(stateTime),
							ball.getX() - 7, ball.getY() - 5,
							ball.getWidth() / 2.0f, ball.getHeight() / 2.0f,
							27, 27, 1, 1, 0f);

					if (explosionAnimation.isAnimationFinished(stateTime)) {
						blinkTimes++;
						if (blinkTimes > 1) {
							explosionCheck = false;
							stateTime = 0;
						}

					}
				}
				AssetLoader.shadow.draw(batcher, "High Score!", 19, 56);
				AssetLoader.font.draw(batcher, "High Score!", 18, 55);
			}

			AssetLoader.shadow.draw(batcher, "Try again?", 23, 76);
			AssetLoader.font.draw(batcher, "Try again?", 24, 75);

			drawScore();
			// }

		}
		// drawScore();
		// End SpriteBatch
		batcher.end();
		drawTransition(delta);
	}

	public void prepareTransition(int r, int g, int b, float duration) {
		transitionColor.set(r / 255.0f, g / 255.0f, b / 255.0f, 1);
		alpha.setValue(1);
		Tween.registerAccessor(Value.class, new ValueAccessor());
		manager = new TweenManager();
		Tween.to(alpha, -1, duration).target(0)
				.ease(TweenEquations.easeOutQuad).start(manager);
	}

	private void drawTransition(float delta) {
		if (alpha.getValue() > 0) {
			manager.update(delta);
			Gdx.gl.glEnable(GL20.GL_BLEND);
			Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
			shapeRenderer.begin(ShapeType.Filled);
			shapeRenderer.setColor(transitionColor.r, transitionColor.g,
					transitionColor.b, alpha.getValue());
			shapeRenderer.rect(0, 0, 136, 300);
			shapeRenderer.end();
			Gdx.gl.glDisable(GL20.GL_BLEND);

		}
	}

	private void drawMeteors(float runTime, int clearance) {
		// float time = 0;
		if (clearance >= 1) {
			batcher.draw(meteorTxtr1, meteor1.getX(), meteor1.getY(),
					meteor1.getWidth() / 2.0f, meteor1.getWidth() / 2.0f,
					meteor1.getWidth(), meteor1.getWidth(), 1, 1, 0f);
			meteor1.updateMovement((float) 0.2);
			if (meteor1.getX() + meteor1.getWidth() <= 0
					|| meteor1.getY() + meteor1.getWidth() >= myWorld
							.getGameH()) {
				meteor1.reset();
			}
			// time += Gdx.graphics.getDeltaTime();
		} 
		if (clearance >= 2) {
			batcher.draw(meteorTxtr2, meteor2.getX(), meteor2.getY(),
					meteor2.getWidth() / 2.0f, meteor2.getWidth() / 2.0f,
					meteor2.getWidth(), meteor2.getWidth(), 1, 1, 0f);
			meteor2.updateMovement((float) 0.2);

			if (meteor2.getX() + meteor2.getWidth() <= 0
					|| meteor2.getY() + meteor2.getWidth() >= myWorld
							.getGameH()) {
				time2 += Gdx.graphics.getDeltaTime();
				// Gdx.app.log("time", time + "");
				if (time2 >= 3) {

					meteor2.reset();
					time2 = 0;
				}
			}
		} 
		if (clearance >= 3) {
			batcher.draw(meteorTxtr2, meteor4.getX(), meteor4.getY(),
					meteor4.getWidth() / 2.0f, meteor4.getWidth() / 2.0f,
					meteor4.getWidth(), meteor4.getWidth(), 1, 1, 0f);
			meteor4.updateMovement((float) 0.4);

			batcher.draw(meteorTxtr3, meteor3.getX(), meteor3.getY(),
					meteor3.getWidth() / 2.0f, meteor3.getWidth() / 2.0f,
					meteor3.getWidth(), meteor3.getWidth(), 1, 1, 0f);
			meteor3.updateMovement((float) 0.2);

			if (meteor3.getX() + meteor3.getWidth() <= 0
					|| meteor3.getY() + meteor3.getWidth() >= myWorld
							.getGameH()) {
				time3 += Gdx.graphics.getDeltaTime();
				// Gdx.app.log("time", time3 + "");
				if (time3 >= 10) {

					meteor3.reset();
					if (meteor4.getX() + meteor4.getWidth() <= 0
							|| meteor4.getY() + meteor4.getWidth() >= myWorld
									.getGameH()) {
						// time3 += Gdx.graphics.getDeltaTime();
						meteor4.reset();
					}

					time3 = 0;
				}
			}
		}
		if(clearance >= 4) {
		batcher.draw(fireAnimation.getKeyFrame(runTime), fire.getX(),
				fire.getY(), fire.getWidth() / 2.0f, fire.getWidth() / 2.0f,
				fire.getWidth(), fire.getWidth(), 1, 1, 0f);
		}
		if(clearance >= 5) {
		batcher.draw(moonAnimation.getKeyFrame(runTime), moon.getX(),
				moon.getY(), moon.getWidth() / 2.0f, moon.getWidth() / 2.0f,
				moon.getWidth(), moon.getWidth(), 1, 1, 0f);
		}
		if(clearance >= 6) {
		batcher.draw(lightAnimation.getKeyFrame(runTime), light.getX(),
				light.getY(), light.getWidth() / 2.0f, light.getWidth() / 2.0f,
				light.getWidth(), light.getWidth(), 1, 1, 0f);
		}
		if(clearance >= 7) {
		batcher.draw(fluxAnimation.getKeyFrame(runTime), flux.getX(),
				flux.getY(), flux.getWidth() / 2.0f, flux.getWidth() / 2.0f,
				flux.getWidth(), flux.getWidth(), 1, 1, 0f);
		}
	}

	private void drawUnlockedWorld(float runTime, renderMode mode) {
		int hiScore;
		//batcher.draw(fish, 3, gameHeight/2 - (1/1)*(gameHeight/4), 40, (int)(40/0.78));
		if(mode == renderMode.NOVA){
			hiScore = AssetLoader.getHighScore();
		}
		else {
			hiScore = ball.getHitCount();
		}
		//drawMeteors(runTime, 5);
		if(hiScore >= 5 && hiScore < 10){
			drawMeteors(runTime, 1);
		}
		else if(hiScore >= 10 && hiScore < 13){
			drawMeteors(runTime, 2);
		}
		else if(hiScore >= 13 && hiScore < 24){
			drawMeteors(runTime, 3);
		}
		else if(hiScore >= 24 && hiScore < 37){
			drawMeteors(runTime, 4);
		}
		else if(hiScore >= 37 && hiScore < 42){
			drawMeteors(runTime, 5);
		}
		else if(hiScore >= 42 && hiScore < 72){
			drawMeteors(runTime, 6);
		}
		else if(hiScore >= 72){
			drawMeteors(runTime, 7);
		}
		
		if (hiScore >= 30) {
			batcher.draw(bHole1Animation.getKeyFrame(runTime), hole1.getX(),
					hole1.getY(), hole1.getRad(), hole1.getRad(),
					hole1.getRad() * 2, hole1.getRad() * 2, 1, 1, 0f);
		}
		if (hiScore >= 40) {
			batcher.draw(bHole1Animation.getKeyFrame(runTime + 3),
					hole2.getX(), hole2.getY(), hole2.getRad(), hole2.getRad(),
					hole2.getRad() * 2, hole2.getRad() * 2, 1, 1, 0f);
		}
		if (hiScore >= 50) {
			batcher.draw(bHole1Animation.getKeyFrame(runTime + 10),
					hole3.getX(), hole3.getY(), hole3.getRad(), hole3.getRad(),
					hole3.getRad() * 2, hole3.getRad() * 2, 1, 1, 0f);
		}
	}
}
