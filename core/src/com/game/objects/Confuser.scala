package com.game.objects

import com.game.AssetHandler._
import com.game.physics.Hitbox
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.game.Game

/**
 * An item that switches the direction player is controlled in
 */
class Confuser(x: Float, y: Float, w: Float, h: Float) extends Item(x, y, w, h) {
  
  private val texture = getTexture(Texture.CONFUSER)
  
  //Creates the pixmap for pixelperfect hitdetection
  private val pixmap = new Pixmap(w.toInt, h.toInt, Pixmap.Format.RGBA8888)
  texture.getTextureData.prepare()
  pixmap.drawPixmap(texture.getTextureData.consumePixmap(), 0, 0, texture.getWidth, texture.getHeight, 0, 0, w.toInt, h.toInt)
  
  protected val hitbox = new Hitbox(x, y, w, h, pixmap)
  
  protected val tex: AtlasRegion = new AtlasRegion(texture, 0, 0, texture.getWidth, texture.getHeight)
  
  private var angle = 0f
  
  def update(delta: Float): Unit = {
    angle += 5f * delta
  }
  
  override def draw(batch: SpriteBatch) = {
    batch.draw(tex, xCoord - width / 2, yCoord - height / 2, width / 2, height / 2, width, height, 1, 1, angle)
  }
  
}