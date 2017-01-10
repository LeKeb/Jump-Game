package com.game.state

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.game.ui.MainMenuUi
import com.badlogic.gdx.Gdx
import com.game.Game
import com.game.AssetHandler
import com.game.ui.OptionUi
import com.game.AssetHandler.Texture
import com.game.Camera
import com.game.ui.Ui
import com.game.ui.AboutUi


class MainMenuState extends State {
  
  private val ui = new MainMenuUi(this)
  
  private val optionUi = new OptionUi(this)
  
  private val aboutUi = new AboutUi(this)
  
  private var isInOptions = false
  
  private val startView = AssetHandler.getTexture(Texture.START_VIEW)
  private val aboutBack = AssetHandler.getTexture(Texture.CHALK_BOARD)
  
  private var isInAbout = false
  
  override def enter() = {
    Gdx.input.setInputProcessor(ui)
    Game.soundSystem.loopMusic(AssetHandler.getMusic(AssetHandler.Music.MENU))
  }
  
  override def exit() = {
    
  }
  
  override def update(delta: Float) = {
    
  }
  
  override def drawUi(batch: SpriteBatch) = {
   
    batch.draw(startView, 0, 0, Camera.renderWidth, Camera.renderHeight)
    if (isInOptions)
      optionUi.draw(batch)
    else if (isInAbout) {
      batch.draw(aboutBack, Camera.renderWidth / 20, Camera.renderHeight / 20, Camera.renderWidth * 9 / 10, Camera.renderHeight * 4 / 5)
      aboutUi.draw(batch)
    }
    else
      ui.draw(batch)
  }
  
  override def drawGame(batch: SpriteBatch) = {
    
  }
  
  def enterOptions() = {
    Gdx.input.setInputProcessor(optionUi)
    isInOptions = true
  }
  
  def exitOptions() = {
    Gdx.input.setInputProcessor(ui)
    isInOptions = false
  }
  
  def enterAbout() = {
    Gdx.input.setInputProcessor(aboutUi)
    isInAbout = true
  }
  
  def exitAbout() = {
    Gdx.input.setInputProcessor(ui)
    isInAbout = false
  }
  
}