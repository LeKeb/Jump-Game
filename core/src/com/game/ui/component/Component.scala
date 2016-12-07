package com.game.ui.component

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2

abstract class Component {
  
  def containsPoint(point: Vector2): Boolean
  
  def draw(batch: SpriteBatch): Unit
  
}