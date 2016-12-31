package com.game.state

import com.game.ui.OptionUi
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class OptionState extends State {
  
  private val ui = new OptionUi

  override def enter() = {
    Gdx.input.setInputProcessor(ui)
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