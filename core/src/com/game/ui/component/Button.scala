package com.game.ui.component

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.badlogic.gdx.math.Vector2

/**
 * A button, when clicked executes an action
 */
class Button(x: Float, y: Float, w: Float, h: Float, tex: Texture, twoButtonTex: Boolean, func: () => Unit) extends Component(x, y, w, h) {
  
  private val releasedTex =
  if (twoButtonTex)
    new AtlasRegion(tex, 0, 0, tex.getWidth, tex.getHeight / 2)
  else
    new AtlasRegion(tex, 0, 0, tex.getWidth, tex.getHeight)
  
  private val pressedTex = 
  if (twoButtonTex)
    new AtlasRegion(tex, 0, tex.getHeight / 2, tex.getWidth, tex.getHeight / 2)
  else 
    null
  
  private val function = func
  
  private var isPressed = false
      
  def draw(batch: SpriteBatch) = {
    if (isPressed) {
      if (pressedTex != null)
        batch.draw(pressedTex, xCoord, yCoord, width, height)
      else {
        val col = batch.getColor
        batch.setColor(col.r * 0.3f, col.g * 0.3f, col.b * 0.3f, col.a)
        batch.draw(releasedTex, xCoord, yCoord, width, height)
        batch.setColor(col)
      }
    }
    else
      batch.draw(releasedTex, xCoord, yCoord, width, height)
  }
  
  def touchDown(point: Vector2): Unit = {
    if (containsPoint(point))
      isPressed = true
  }
  
  def touchDrag(point: Vector2): Unit = {
    if (!containsPoint(point) && isPressed)
      isPressed = false
  }
  
  def touchUp(point: Vector2): Unit = {
    if (containsPoint(point) && isPressed) {
      func()
      isPressed = false
    }
  }

  
}