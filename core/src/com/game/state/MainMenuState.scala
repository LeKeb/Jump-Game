package com.game.state

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.game.ui.MainMenuUi
import com.badlogic.gdx.Gdx
import com.game.Game
import com.game.AssetHandler
import com.game.ui.OptionUi

class MainMenuState extends State {
  
  private val ui = new MainMenuUi(this)
  
  private val optionUi = new OptionUi(this)
  
  private var isInOptions = false
  
  override def enter() = {
    Gdx.input.setInputProcessor(ui)
    Game.soundSystem.loopMusic(AssetHandler.getMusic(AssetHandler.Music.MENU))
  }
  
  override def exit() = {
    
  }
  
  override def update(delta: Float) = {
    
  }
  
  override def drawUi(batch: SpriteBatch) = {
    if (isInOptions)
      optionUi.draw(batch)
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
  
}