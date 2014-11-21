package com.nukeandjuke.screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.nukeandjuke.gameworld.GameRenderer;
import com.nukeandjuke.gameworld.GameWorld;
//import com.kilobolt.zbhelpers.InputHandler;
import com.nukeandjuke.bhelpers.InputHandler;

public class GameScreens implements Screen {
	private GameWorld world;
	private GameRenderer renderer;
	//private int midPointY;
	private float runTime = 0;
	
	public GameScreens() {
		
		float screenWidth = Gdx.graphics.getWidth();
		float screenHeight = Gdx.graphics.getHeight();
		float ratio = screenHeight / screenWidth;
		float gameWidth = 136;
		float gameHeight = gameWidth * ratio;
		int midPointY = (int) (gameHeight / 2);
	
		//Gdx.app.log("GameScreen", "Attached");
		world = new GameWorld();
		Gdx.input.setInputProcessor(new InputHandler(world));
		renderer = new GameRenderer(world, (int)gameHeight, midPointY);
		
	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		// Sets a Color to Fill the Screen with (RGB = 10, 15, 230), Opacity of 1 (100%)
        //Gdx.gl.glClearColor(10/255.0f, 15/255.0f, 230/255.0f, 1f);
        // Fills the screen with the selected color
        //Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Covert Frame rate to String, print it
        //Gdx.app.log("GameScreen FPS", (1/delta) + "");
		runTime += delta;
		world.update(delta);
		renderer.setDeltaTime(delta);
		renderer.render(delta, runTime);
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		Gdx.app.log("GameScreen", "resizing");
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		 Gdx.app.log("GameScreen", "show called");
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		Gdx.app.log("GameScreen", "hide called");     
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		Gdx.app.log("GameScreen", "pause called");
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		Gdx.app.log("GameScreen", "resume called");  
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
