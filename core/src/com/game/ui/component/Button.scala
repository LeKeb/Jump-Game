package com.game.ui.component

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.badlogic.gdx.math.Vector2

class Button(x: Float, y: Float, w: Float, h: Float, tex: Texture, func: () => Unit) extends Component(x, y, w, h) {
  
  private val releasedTex = new AtlasRegion(tex, 0, 0, tex.getWidth, tex.getHeight / 2)
  private val pressedTex = new AtlasRegion(tex, 0, tex.getHeight / 2, tex.getWidth, tex.getHeight / 2)
  
  private val function = func
  
  private var isPressed = false
      
  def draw(batch: SpriteBatch) = {
    if (isPressed)
      batch.draw(pressedTex, xCoord, yCoord, width, height)
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
    if (containsPoint(point) && isPressed)
      func()
  }

  
}