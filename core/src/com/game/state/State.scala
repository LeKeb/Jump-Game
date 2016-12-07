package com.game.state

import com.badlogic.gdx.graphics.g2d.SpriteBatch

abstract class State {
  
  def enter():Unit
  
  def exit():Unit
  
  def update(delta: Float):Unit
  
  def drawGame(batch: SpriteBatch):Unit
  
  def drawUi(batch: SpriteBatch):Unit
  
}