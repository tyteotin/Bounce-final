package com.nukeandjuke.bounce;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nukeandjuke.bhelpers.AssetLoader;
import com.nukeandjuke.screens.SplashScreen;

public class BGame extends Game {
	SpriteBatch batch;
	Texture img;
	
	@Override
	public void create () {
		Gdx.app.log("ZBGame", "created");
        AssetLoader.load();
		setScreen(new SplashScreen(this));
	}

	@Override
    public void dispose() {
        super.dispose();
        AssetLoader.dispose();
    }
}
