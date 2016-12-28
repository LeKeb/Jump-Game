package com.game.objects

import com.game.physics.Hitbox
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.game.AssetHandler._
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class Coconut(x: Float, y: Float, w: Float, h: Float) extends Item(x, y, w, h){
  
  private val texture = getTexture(Texture.COCONUT)
  
  private val pixmap = new Pixmap(w.toInt, h.toInt, Pixmap.Format.RGBA8888)
  texture.getTextureData.prepare()
  pixmap.drawPixmap(texture.getTextureData.consumePixmap(), 0, 0, texture.getWidth, texture.getHeight, 0, 0, w.toInt, h.toInt)
  
  protected val hitbox = new Hitbox(x, y, w, h, pixmap)
  
  protected val tex: AtlasRegion = new AtlasRegion(texture, 0, 0, 192, 242)
  
  private var xVelo = 0f
  private var yVelo = 0f
  
  private var angle = 0f
  
  override def draw(batch: SpriteBatch) = {
    batch.draw(tex, xCoord, yCoord, width, height)
  }
  
  def update(delta: Float): Unit = {
    xCoord += xVelo * delta
    yCoord += yVelo * delta
    angle += 4f * delta
    
    yVelo = Math.max(yVelo - 1 * delta, -15)
    
    hitbox.setCoords(xCoord, yCoord)
  }

}