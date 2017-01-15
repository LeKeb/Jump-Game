package com.game.objects

import com.game.AssetHandler._
import com.game.physics.Hitbox
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.game.Game
import com.game.Animation

/**
 * Fire item, if player hits it he moves at 1.5 times the normal speed
 */
class Fire(x: Float, y: Float, w: Float, h: Float) extends Item(x, y, w, h) {
  
  private val animation = new Animation(getTexture(Texture.FIRE), 8, 8, 30) //fire animation
  
  private val texture = getTexture(Texture.FIRE_HITBOX)
  
  //Creates the pixmap for pixelperfect hitdetection
  private val pixmap = new Pixmap(w.toInt, h.toInt, Pixmap.Format.RGBA8888)
  texture.getTextureData.prepare()
  pixmap.drawPixmap(texture.getTextureData.consumePixmap(), 0, 0, texture.getWidth, texture.getHeight, 0, 0, w.toInt, h.toInt)
  
  protected val hitbox = new Hitbox(x, y, w, h, pixmap)
  
  protected val tex: AtlasRegion = new AtlasRegion(texture, 0, 0, texture.getWidth, texture.getHeight)
  
  def update(delta: Float): Unit = {
    animation.update(delta)
  }
  
  override def draw(batch: SpriteBatch) = {
    batch.draw(animation.getImage, xCoord - width / 2, yCoord - height / 2, width, height)
  }
  

}