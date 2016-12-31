package com.game.state

import com.game.ui.GameUi
import com.game.GameWorld
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.Gdx
import com.game.Game
import com.game.ui.PauseUi
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.game.AssetHandler._
import com.game.Camera

class PauseState extends State {
  
  private val ui = new PauseUi
  
  private var gameInputProcessor: InputProcessor = _
  
  private val background = new AtlasRegion(getTexture(Texture.BLACK_OUT), 0, 0, 1, 1)
  
  override def enter() = {
    gameInputProcessor = Gdx.input.getInputProcessor
    Gdx.input.setInputProcessor(ui)
  }
  
  override def exit() = {
    Gdx.input.setInputProcessor(gameInputProcessor)
  }
  
  override def update(delta: Float) = {
    
  }
  
  override def drawUi(batch: SpriteBatch) = {
    val color = batch.getColor
    batch.setColor(0, 0, 0, 0.5f)
    batch.draw(background, 0, 0, Camera.renderWidth, Camera.renderHeight)
    batch.setColor(color)
    ui.draw(batch)
  }
  
  override def drawGame(batch: SpriteBatch) = {

  }
  
}