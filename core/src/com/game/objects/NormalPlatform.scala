package com.game.objects

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.game.AssetHandler
import com.badlogic.gdx.graphics.Pixmap
import com.game.physics.Hitbox

/**
 * A regular platform, no special effects
 */
class NormalPlatform(x: Float, y: Float, w: Float, h: Float) extends Platform(x, y, w, h) {
  
  private val t = AssetHandler.getTexture(AssetHandler.Texture.NORMAL_PLATFORM)
  tex = new AtlasRegion(t, 0, 0, t.getWidth, t.getHeight)
  
  //Creates the pixmap for pixelperfect hitdetection
  private val pixmap = new Pixmap(w.toInt, h.toInt, Pixmap.Format.RGBA8888)
  t.getTextureData.prepare()
  pixmap.drawPixmap(tex.getTexture.getTextureData.consumePixmap(), 0, 0, t.getWidth, t.getHeight, 0, 0, w.toInt, h.toInt)
  
  hitbox = new Hitbox(x, y, w, h, pixmap)
  
  def highestPossibleJump = 400
  
  def update(delta: Float) = {
    
  }
  
}