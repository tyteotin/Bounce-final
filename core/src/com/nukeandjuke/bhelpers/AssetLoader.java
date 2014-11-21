package com.nukeandjuke.bhelpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {
    public static Texture texture, logoTexture;
    public static Texture myTexture;
    public static Texture myTexture1;
    public static Texture myTexture2;
    public static Texture backgroundTexture;
    
    public static Texture blkHoleTexture1;
    public static Texture blkHoleTexture2;
    public static Texture blkHoleTexture3;
    public static Texture blkHoleTexture4;
    public static Texture blkHoleTexture5;
    public static Texture blkHoleTexture6;
    
    public static Texture gameLogo, scoreBoardTxtre, starTxtre, noStarTxtre;
    public static Texture playUpTxtre, playDownTxtre, rdy, gmOver, hiScr;
    public static Texture retryTxtre;
    public static TextureRegion bg, logo, zbLogo, scoreboard, star, noStar;

    
    public static TextureRegion playButtonUp, playButtonDown;
    public static TextureRegion scoreButtonUp, scoreButtonDown;
    public static TextureRegion novaButtonUp, novaButtonDown;
    public static TextureRegion skullUp, skullDown, bar;
    public static TextureRegion ready, gameOver, highScore, retry;
    ///////////////////////////////////////////////////////
    public static Animation ballAnimation, fireAnimation, lightAnimation;
    public static TextureRegion ball1, ball2, ball3, ball4;
    public static TextureRegion ball5, ball6, ball7, ball8;
    
    public static Sound hit;
    
    public static BitmapFont font, shadow;
    public static Preferences prefs;
    public static Animation birdAnimation;
    public static Animation explosionAnimation;
    public static Animation explosionAnimation1;
    public static Animation bHole1Animation, moonAnimation, fluxAnimation;

    
    public static TextureRegion explode1, explode2, explode3;
    public static TextureRegion explode4, explode5, explode6;
    public static TextureRegion explode7, explode8, explode9;
    public static TextureRegion explode8_1, explode8_2, explode8_3;
    public static TextureRegion bckgrnd;
    
    public static TextureRegion blkHole1;
    public static TextureRegion blkHole2;
    public static TextureRegion blkHole3;
    public static TextureRegion blkHole4;
    public static TextureRegion blkHole5;
    public static TextureRegion blkHole6;
    
    public static Texture nova;
    public static TextureRegion meteor1, meteor2, meteor3;
    public static TextureRegion fire1, fire2, fire3;
    public static TextureRegion light, light1, light2, light3, light4;
    public static TextureRegion moon1, moon2, moon3;
    public static TextureRegion flux1, flux2, flux3, flux4;
    public static TextureRegion flux5, flux6, flux7, flux8;
    public static TextureRegion flux9, flux10, flux11, flux12;
    
    public static Texture fishTxtre;
    public static TextureRegion fish;
    public static void load() {
    	fishTxtre = new Texture(Gdx.files.internal("data/counter2.png"));
    	fishTxtre.setFilter(TextureFilter.Linear, TextureFilter.Linear);
      	fish = new TextureRegion(fishTxtre, 0, 0, 343, 492);
      	fish.flip(false, true);
    	
      	nova = new Texture(Gdx.files.internal("data/novaFile1.png"));
    	nova.setFilter(TextureFilter.Linear, TextureFilter.Linear);
    	
    	meteor1 = new TextureRegion(nova, 0, 0, 200, 200);
    	meteor1.flip(false, true);
    	meteor2 = new TextureRegion(nova, 200, 0, 200, 200);
    	meteor2.flip(false, true);
    	meteor3 = new TextureRegion(nova, 400, 0, 200, 200);
    	meteor3.flip(false, true);
    	
    	fire1 = new TextureRegion(nova, 600, 0, 100, 100);
    	fire1.flip(false, true);
    	fire2 = new TextureRegion(nova, 600, 100, 100, 100);
    	fire2.flip(false, true);
    	fire3 = new TextureRegion(nova, 700, 0, 100, 100);
    	fire3.flip(false, true);
    	TextureRegion[] fires = {fire1, fire2, fire3, fire2, fire1, fire1, fire1, fire1};
    	fireAnimation = new Animation(3f, fires);
    	fireAnimation.setPlayMode(Animation.PlayMode.NORMAL);
    	
    	light = new TextureRegion(nova, 700, 100, 100, 100);
    	light.flip(false, true);
    	light1 = new TextureRegion(nova, 0, 200, 100, 100);
    	light1.flip(false, true);
    	light2 = new TextureRegion(nova, 100, 200, 100, 100);
    	light2.flip(false, true);
    	light3 = new TextureRegion(nova, 200, 200, 100, 100);
    	light3.flip(false, true);
    	light4 = new TextureRegion(nova, 300, 200, 100, 100);
    	light4.flip(false, true);
    	TextureRegion[] lights = {light, light1, light2, light3, light4, light4, light};
    	lightAnimation = new Animation(1f, lights);
    	lightAnimation.setPlayMode(Animation.PlayMode.LOOP);
    	
    	moon1 = new TextureRegion(nova, 400, 200, 100, 100);
    	moon1.flip(false, true);
    	moon2 = new TextureRegion(nova, 500, 200, 100, 100);
    	moon2.flip(false, true);
    	moon3 = new TextureRegion(nova, 600, 200, 100, 100);
    	moon3.flip(false, true);
    	TextureRegion[] moons = {moon1, moon1, moon2, moon2, moon3, moon3, moon2, moon2, moon1, moon1};
    	moonAnimation = new Animation(6f, moons);
    	moonAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
    	
    	
    	flux1 = new TextureRegion(nova, 0, 300, 100, 100);
    	flux1.flip(false, true);
   		flux2 = new TextureRegion(nova, 100, 300, 100, 100);
  		flux2.flip(false, true);
    	flux3 = new TextureRegion(nova, 200, 300, 100, 100);
    	flux3.flip(false, true);
    	flux4 = new TextureRegion(nova, 300, 300, 100, 100);
    	flux4.flip(false, true);
    	flux5 = new TextureRegion(nova, 400, 300, 100, 100);
    	flux5.flip(false, true);
    	flux6 = new TextureRegion(nova, 500, 300, 100, 100);
    	flux6.flip(false, true);
    	flux7 = new TextureRegion(nova, 600, 300, 100, 100);
    	flux7.flip(false, true);
    	flux8 = new TextureRegion(nova, 700, 300, 100, 100);
    	flux8.flip(false, true);
    	flux9 = new TextureRegion(nova, 0, 400, 100, 100);
    	flux9.flip(false, true);
    	flux10 = new TextureRegion(nova, 100, 400, 100, 100);
    	flux10.flip(false, true);
    	flux11 = new TextureRegion(nova, 200, 400, 100, 100);
    	flux11.flip(false, true);
    	flux12 = new TextureRegion(nova, 300, 400, 100, 100);
    	flux12.flip(false, true);
    	TextureRegion[] fluxs = {flux1, flux2, flux3, flux4, flux5, flux6, flux7, flux8, flux11, flux10, flux12, flux9, flux1};
    	fluxAnimation = new Animation(0.6f, fluxs);
    	fluxAnimation.setPlayMode(Animation.PlayMode.NORMAL);
    	
    	logoTexture = new Texture(Gdx.files.internal("data/logo.png"));
        logoTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        
        logo = new TextureRegion(logoTexture, 0, 0, 512, 114);
        
        texture = new Texture(Gdx.files.internal("data/texture3a.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
       
        gameLogo = new Texture(Gdx.files.internal("data/gameName.png"));
        zbLogo = new TextureRegion(gameLogo, 0, 0, 512, 128);
        zbLogo.flip(false, true);
        
        scoreBoardTxtre = new Texture(Gdx.files.internal("data/rankTable.png"));
        scoreboard = new TextureRegion(scoreBoardTxtre, 0, 0, 154, 128);
        scoreboard.flip(false, true);
        
        starTxtre = new Texture(Gdx.files.internal("data/star.png"));
        star = new TextureRegion(starTxtre, 0, 0, 154, 128);
        star.flip(false, true);
        
        noStarTxtre = new Texture(Gdx.files.internal("data/noStar.png"));
        noStar = new TextureRegion(noStarTxtre, 0, 0, 154, 128);
        noStar.flip(false, true);
                
        rdy = new Texture(Gdx.files.internal("data/ready.png"));
        ready = new TextureRegion(rdy, 0, 0, 200, 100);
        ready.flip(false, true);
        
        gmOver = new Texture(Gdx.files.internal("data/gameOver.png"));
        gameOver = new TextureRegion(gmOver, 0, 0, 200, 100);
        gameOver.flip(false, true);
        
        hiScr = new Texture(Gdx.files.internal("data/highscore.png"));
        highScore = new TextureRegion(hiScr, 0, 0, 200, 100);
        highScore.flip(false, true);
        
        retryTxtre = new Texture(Gdx.files.internal("data/retry.png"));
        retry = new TextureRegion(retryTxtre, 0, 0, 200, 100);
        retry.flip(false, true);
        
        
        
        
        
        myTexture = new Texture(Gdx.files.internal("data/explode8_1.png"));
        myTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        
        myTexture1 = new Texture(Gdx.files.internal("data/explode8_2.png"));
        myTexture1.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        
        myTexture2 = new Texture(Gdx.files.internal("data/explode8_3.png"));
        myTexture2.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        
        backgroundTexture = new Texture(Gdx.files.internal("data/bkgd.png"));
        backgroundTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        bckgrnd = new TextureRegion(backgroundTexture, 0, 0, 351, 385);
        bckgrnd.flip(false, true);
        
        blkHoleTexture1 = new Texture(Gdx.files.internal("data/bhole1_1j.png"));
        blkHoleTexture1.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        blkHole1 = new TextureRegion(blkHoleTexture1, 0, 0, 120, 120);
        blkHole1.flip(false, true);
        
        blkHoleTexture2 = new Texture(Gdx.files.internal("data/bhole1_1i.png"));
        blkHoleTexture2.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        blkHole2 = new TextureRegion(blkHoleTexture2, 0, 0, 120, 120);
        blkHole2.flip(false, true);
        
        blkHoleTexture3 = new Texture(Gdx.files.internal("data/bhole1_1h.png"));
        blkHoleTexture3.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        blkHole3 = new TextureRegion(blkHoleTexture3, 0, 0, 120, 120);
        blkHole3.flip(false, true);
        
        blkHoleTexture4 = new Texture(Gdx.files.internal("data/bhole1_1g.png"));
        blkHoleTexture4.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        blkHole4 = new TextureRegion(blkHoleTexture4, 0, 0, 120, 120);
        blkHole4.flip(false, true);
        
        blkHoleTexture5 = new Texture(Gdx.files.internal("data/bhole1_1f.png"));
        blkHoleTexture5.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        blkHole5 = new TextureRegion(blkHoleTexture5, 0, 0, 120, 120);
        blkHole5.flip(false, true);
        
        blkHoleTexture6 = new Texture(Gdx.files.internal("data/bhole1_1e.png"));
        blkHoleTexture6.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        blkHole6 = new TextureRegion(blkHoleTexture6, 0, 0, 120, 120);
        blkHole6.flip(false, true);
        
        playButtonUp = new TextureRegion(texture, 0, 83, 29, 16);
        playButtonDown = new TextureRegion(texture, 29, 83, 29, 16);
        playButtonUp.flip(false, true);
        playButtonDown.flip(false, true);
        
        scoreButtonUp = new TextureRegion(texture, 0, 99, 29, 16);
        scoreButtonDown = new TextureRegion(texture, 29, 99, 29, 16);
        scoreButtonUp.flip(false, true);
        scoreButtonDown.flip(false, true);
        
        novaButtonUp = new TextureRegion(texture, 58, 83, 29, 16);
        novaButtonDown = new TextureRegion(texture, 87, 83, 29, 16);
        novaButtonUp.flip(false, true);
        novaButtonDown.flip(false, true);
        
        bg = new TextureRegion(texture, 0, 0, 136, 43);
        bg.flip(false, true);

        ball1 = new TextureRegion(texture, 136, 19, 16, 16);
        ball1.flip(false, true);
        
        ball2 = new TextureRegion(texture, 152, 19, 16, 16);
        ball2.flip(false, true);
        
        ball3 = new TextureRegion(texture, 168, 19, 16, 16);
        ball3.flip(false, true);
        
        ball4 = new TextureRegion(texture, 184, 19, 16, 16);
        ball4.flip(false, true);
        
        ball5 = new TextureRegion(texture, 200, 19, 16, 16);
        ball5.flip(false, true);
        
        ball6 = new TextureRegion(texture, 216, 19, 16, 16);
        ball6.flip(false, true);
        
        ball7 = new TextureRegion(texture, 168, 35, 16, 16);
        ball7.flip(false, true);
        
        ball8 = new TextureRegion(texture, 184, 35, 16, 16);
        ball8.flip(false, true);
        /////////////////////////////////////////////////////////////////
        TextureRegion[] balls = {ball1, ball2, ball3, ball4, ball5, ball6,
        						ball7, ball8};
        ballAnimation = new Animation(0.06f, balls);
        ballAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        /////////////////////////////////////////////////////////////////
        
        explode1 = new TextureRegion(texture, 168, 51, 16, 16);
        explode2 = new TextureRegion(texture, 184, 51, 16, 16);
        explode3 = new TextureRegion(texture, 200, 51, 16, 16);
        explode4 = new TextureRegion(texture, 216, 51, 16, 16);
        explode5 = new TextureRegion(texture, 232, 51, 16, 16);
        
        explode6 = new TextureRegion(texture, 168, 67, 16, 16);
        explode7 = new TextureRegion(texture, 184, 67, 16, 16);
        explode8 = new TextureRegion(texture, 200, 67, 16, 16);
        explode8_1 = new TextureRegion(myTexture, 0, 0, 64, 64);
        explode8_1.flip(false, true);
        explode8_2 = new TextureRegion(myTexture1, 0, 0, 64, 64);
        explode8_2.flip(false, true);
        explode8_3 = new TextureRegion(myTexture2, 0, 0, 64, 64);
        explode8_3.flip(false, true);
        explode9 = new TextureRegion(texture, 232, 67, 16, 16);
        /////////////////////////////////////////////////////////////////
        TextureRegion[] explosion = {explode1, explode2, explode3, explode4, explode5, explode6,
        		explode7, explode8, explode8_1};
         
        explosionAnimation = new Animation(0.1f, explosion);
        explosionAnimation.setPlayMode(Animation.PlayMode.NORMAL);
        
        TextureRegion[] explosion1 = {explode8_1, explode8_2, explode8_3};
        explosionAnimation1 = new Animation(0.1f, explosion1);
        explosionAnimation1.setPlayMode(Animation.PlayMode.NORMAL);
        
        TextureRegion[] bHole1 = {blkHole1, blkHole2, blkHole3, blkHole4,
        		blkHole5, blkHole6, blkHole5, blkHole4, blkHole3, blkHole2, blkHole1};
        bHole1Animation = new Animation(0.9f, bHole1);
        bHole1Animation.setPlayMode(Animation.PlayMode.LOOP);
        
        
        hit = Gdx.audio.newSound(Gdx.files.internal("data/hit2.wav"));
        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        font.setScale(.25f, -.25f);
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.setScale(.25f, -.25f);
        
        prefs = Gdx.app.getPreferences("Bounce");
        if(!prefs.contains("highScore")){
        	prefs.putInteger("highScore", 0);
        }
    }
    
    public static void setHighScore(int val){
    	prefs.putInteger("highScore", val);
    	prefs.flush();
    }
    
    public static int getHighScore() {
    	return prefs.getInteger("highScore");
    	
    }
    
    public static void dispose() {
        // We must dispose of the texture when we are finished.
        texture.dispose();
        logoTexture.dispose();
        myTexture.dispose();
        myTexture1.dispose();
        myTexture2.dispose();
        backgroundTexture.dispose();
        
        blkHoleTexture1.dispose();
        blkHoleTexture2.dispose();
        blkHoleTexture3.dispose();
        blkHoleTexture4.dispose();
        blkHoleTexture5.dispose();
        blkHoleTexture6.dispose();
        
        gameLogo.dispose();
        scoreBoardTxtre.dispose();
        starTxtre.dispose();
        noStarTxtre.dispose();

        rdy.dispose();
        gmOver.dispose();
        hiScr.dispose();
        retryTxtre.dispose();
        hit.dispose();
        font.dispose();
        shadow.dispose();

    }
}
