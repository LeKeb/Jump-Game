package com.game.ui.component

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.badlogic.gdx.math.Vector2

class Button(x: Float, y: Float, w: Float, h: Float, tex: Texture, func: () => Unit) extends Component {
  
  private val xCoord = x - w / 2
  private val yCoord = y - h / 2
  private val width = w
  private val height = h
  private val releasedTex = new AtlasRegion(tex, 0, 0, tex.getWidth, tex.getHeight / 2)
  private val pressedTex = new AtlasRegion(tex, 0, tex.getHeight / 2, tex.getWidth, tex.getHeight / 2)
  
  private val function = func
  
  private var isPressed = false
  
  
  
  def press() = {
    isPressed = true
  }
  
  def release() = {
    if (isPressed)
      function()
    isPressed = false
  }
  
  def reset() = {
    isPressed = false
  }
  
  def isButtonPressed = isPressed
  
  override def containsPoint(point: Vector2): Boolean = {
    val x = point.x - xCoord
    val y = point.y - yCoord
    
    x >= 0 && x < width && y >= 0 && y < height
  }
  
  def draw(batch: SpriteBatch) = {
    if (isPressed)
      batch.draw(pressedTex, xCoord, yCoord, width, height)
    else
      batch.draw(releasedTex, xCoord, yCoord, width, height)
  }
  
}