package com.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20

class Game extends ApplicationAdapter {
	
	private var batch: SpriteBatch = _
	private var game: GameWorld = _ 
	
	override def create() = {
		batch = new SpriteBatch
		AssetHandler.loadAssets()
		game = new GameWorld
	}
	
	override def render() = {
		
		game.update(Gdx.graphics.getDeltaTime)
		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		game.draw(batch)
		batch.end();
	}
	
}