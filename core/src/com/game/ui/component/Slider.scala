package com.game.ui.component

import com.game.AssetHandler._
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.graphics.Color

/**
 * A slider that can be used to control some value in the game
 */
class Slider(x: Float, y: Float, w: Float, h: Float, min: Int, max: Int, start: Int, spacing: Int, func: (Int) => Unit) extends Component(x, y, w, h) {
  
  private val minVal = min
  private val maxVal = max
  private val space = spacing
  
  private val backTex = getTexture(Texture.SLIDER_BACKGORUND)
  private val buttTex = getTexture(Texture.SLIDER_BUTTON)
  
  private var value = start
  private var isTouching = false
  
  def draw(batch: SpriteBatch): Unit = {
    batch.draw(backTex, xCoord, yCoord, (value.toFloat / (maxVal - minVal)) * width, 
        height, 0, 0, (value.toFloat / (maxVal - minVal)), 1)
    val color = batch.getColor()
    batch.setColor(new Color(0, 0, 0, 0.7f))
    batch.draw(backTex, xCoord + (value.toFloat / (maxVal - minVal)) * width, yCoord, width - (value.toFloat / (maxVal - minVal)) * width, height,
        (value.toFloat / (maxVal - minVal)), 0, 1, 1)
    batch.setColor(color)
    batch.draw(buttTex, xCoord + (value.toFloat / (maxVal - minVal)) * width - height * 0.6f, yCoord - height * 0.12f, height * 1.2f, height * 1.2f)
  }
  
  def touchDown(point: Vector2): Unit = {
    if (containsPoint(point)) {
      value = Math.min(maxVal, Math.max(0, ((point.x - xCoord) / width) * maxVal)).toInt
      isTouching = true
    }
  }
  
  def touchDrag(point: Vector2): Unit = {
    if (isTouching) {
      value = Math.min(maxVal, Math.max(0, ((point.x - xCoord) / width) * maxVal)).toInt
      func(value)
    }
  }
  
  def touchUp(point: Vector2): Unit = {
    if (isTouching) {
      func(value)
      isTouching = false
    }
  }

  
}