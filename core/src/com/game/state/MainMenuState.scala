package com.game.state

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.game.ui.MainMenuUi
import com.badlogic.gdx.Gdx
import com.game.Game
import com.game.AssetHandler

class MainMenuState extends State {
  
  private val ui = new MainMenuUi

  override def enter() = {
    Gdx.input.setInputProcessor(ui)
    Game.soundSystem.loopMusic(AssetHandler.getMusic(AssetHandler.Music.MENU))
  }
  
  override def exit() = {
    
  }
  
  override def update(delta: Float) = {
    
  }
  
  override def drawUi(batch: SpriteBatch) = {
    ui.draw(batch)
  }
  
  override def drawGame(batch: SpriteBatch) = {
    
  }
  
}