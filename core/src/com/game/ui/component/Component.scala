package com.game.ui.component

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.Gdx

/**
 * A class to representate different ui components
 */
abstract class Component(x: Float, y: Float, w: Float, h: Float) {
  
  protected val xCoord = x - w / 2
  protected val yCoord = y - h / 2
  protected val width = w
  protected val height = h
  
  protected def containsPoint(point: Vector2): Boolean = {
    val x = point.x - xCoord
    val y = point.y - yCoord
    
    x >= 0 && x < width && y >= 0 && y < height
  }
  
  def draw(batch: SpriteBatch): Unit
  
  def touchDown(point: Vector2): Unit
  
  def touchUp(point: Vector2): Unit

  def touchDrag(point: Vector2): Unit
}