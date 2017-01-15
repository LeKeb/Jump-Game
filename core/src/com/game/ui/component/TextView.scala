package com.game.ui.component

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.game.AssetHandler._
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.Color

/**
 * Draws text to the screen, can have custom font
 */
class TextView(x: Float, y: Float, w: Float, h: Float, t: String, f: BitmapFont) extends Component(x, y, w, h) {
  
  private val font =
    if (f == null)
      getFont(Font.DEFAULT)
     else
       f
       
  private val layout = new GlyphLayout(font, t)
  private var text = t;
  
  def setText(t: String) = {
    text = t
    layout.setText(font, t)
  }
  
  
  def draw(batch: SpriteBatch): Unit = {
    font.setColor(batch.getColor)
    val textWidth = layout.width
    font.draw(batch, text, x - textWidth / 2, y)
  }
  
  
  def touchDown(point: Vector2): Unit = {}
  
  def touchDrag(point: Vector2): Unit = {}
  
  def touchUp(point: Vector2): Unit = {}
}