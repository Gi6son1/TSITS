package com.tsits.screenhandler;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tsits.screens.GameScreen;
import com.tsits.screens.HelpScreen;
import com.tsits.screens.LoadingScreen;
import com.tsits.screens.MainMenuScreen;

public class Tsits extends Game {

    public SpriteBatch batch;
    public BitmapFont fontTitle;
    public BitmapFont font;

    private LoadingScreen loadingScreen;
    private MainMenuScreen menuScreen;
    private GameScreen gameScreen;
    private HelpScreen helpScreen;

    public final static int MENU = 0;
    public final static int GAME = 1;
    public final static int LOADING = 2;
    public final static int HELP = 3;

    @Override
    public void create() {
        batch = new SpriteBatch();

        //Creates the Title font
        FileHandle romanBFont = new FileHandle("fonts/RomanTitle.fnt");
        Texture romanBoldPng = new Texture("fonts/RomanTitle.png");
        TextureRegion romanBPng = new TextureRegion(romanBoldPng);
        fontTitle = new BitmapFont(romanBFont, romanBPng);

        //Creates the ordinary font
        FileHandle oFont = new FileHandle("fonts/FelixTilting.fnt");
        Texture oFontPng = new Texture("fonts/FelixTilting.png");
        TextureRegion ordFontPng = new TextureRegion(oFontPng);
        font = new BitmapFont(oFont, ordFontPng);


        this.setScreen(new LoadingScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    public void changeScreen(int screen){
        switch(screen){
            case MENU:
                if(menuScreen == null) menuScreen = new MainMenuScreen(this);
                this.setScreen(menuScreen);
                break;
            case GAME:
                if(gameScreen == null) gameScreen = new GameScreen(this);
                this.setScreen(gameScreen);
                break;
            case LOADING:
                if(loadingScreen == null) loadingScreen = new LoadingScreen(this);
                this.setScreen(loadingScreen);
                break;
            case HELP:
                if(helpScreen == null) helpScreen = new HelpScreen(this);
                this.setScreen(helpScreen);
                break;
        }
    }
}
