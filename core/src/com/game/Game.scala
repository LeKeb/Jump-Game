package com.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Color
import com.game.state._

object Game {
  
  var game: Game = _
  var soundSystem: SoundSystem = _
  val gameState = new GameState
  val mainMenuState = new MainMenuState
  
}

class Game extends ApplicationAdapter {

  private var batch: SpriteBatch = _
  private var camera: Camera = _
  private var currentState: State = _

  override def create() = {
    batch = new SpriteBatch
    AssetHandler.loadAssets()
    camera = new Camera
    Game.game = this
    Game.soundSystem = new SoundSystem
    currentState = Game.mainMenuState
    currentState.enter()
  }

  override def render() = {
    
    val delta = Gdx.graphics.getDeltaTime * 60
    currentState.update(delta)
    
    camera.setPosition(Camera.renderWidth / 2, Game.gameState.getGame.getPlayer.getAllTimeHighestYCoord - 120)
    camera.update()
    
    Gdx.gl.glClearColor(0, 0.7.toFloat, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    
    batch.setProjectionMatrix(camera.getCamera.combined)
    
    batch.begin();
    currentState.drawGame(batch)
    batch.end();
    
    camera.setPosition(Camera.renderWidth / 2 , Camera.renderHeight / 2)
    camera.update()
    batch.setProjectionMatrix(camera.getCamera.combined)
    
    batch.begin()
    currentState.drawUi(batch)
    batch.end();
  }
  
  def enterState(state: State) = {
    this.currentState.exit()
    this.currentState = state
    currentState.enter()
  }
  
}