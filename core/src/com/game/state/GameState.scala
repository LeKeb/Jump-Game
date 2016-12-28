package com.game.state

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.Gdx
import com.game.ui.GameUi
import com.game.GameWorld

class GameState extends State {
  
  
  private val ui = new GameUi
  private var game = new GameWorld
  
  
  def getGame = game
  
  override def enter() = {
    Gdx.input.setInputProcessor(ui)
    game = new GameWorld
  }
  
  override def exit() = {
    
  }
  
  override def update(delta: Float) = {
    game.update(delta)
  }
  
  override def drawUi(batch: SpriteBatch) = {
    ui.draw(batch)
  }
  
  override def drawGame(batch: SpriteBatch) = {
    game.draw(batch)
  }
  
}