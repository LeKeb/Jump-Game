package com.game.state

import com.badlogic.gdx.graphics.g2d.SpriteBatch

/**
 * An abstract class that representates a state in the game, e.g. gmaestate, mainmenustate
 */
abstract class State {
  
  def enter():Unit
  
  def exit():Unit
  
  def update(delta: Float):Unit
  
  def drawGame(batch: SpriteBatch):Unit
  
  def drawUi(batch: SpriteBatch):Unit
  
}