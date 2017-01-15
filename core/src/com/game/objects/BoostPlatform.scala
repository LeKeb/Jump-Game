package com.game.objects

import com.game.AssetHandler
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.badlogic.gdx.graphics.Pixmap
import com.game.physics.Hitbox

/**
 * A platform that boosts the players jump
 */
class BoostPlatform(x: Float, y: Float, w: Float, h: Float) extends Platform(x, y, w, h) {

  private val t = AssetHandler.getTexture(AssetHandler.Texture.BOOST_PLATFORM)
  tex = new AtlasRegion(t, 0, 0, t.getWidth, t.getHeight)
  
  //Creates the pixmap for pixelperfect hitdetection
  private val texture = tex.getTexture
  private val pixmap = new Pixmap(w.toInt, h.toInt, Pixmap.Format.RGBA8888)
  texture.getTextureData.prepare()
  pixmap.drawPixmap(tex.getTexture.getTextureData.consumePixmap(), 0, 0, texture.getWidth, texture.getHeight, 0, 0, w.toInt, h.toInt)
  
  hitbox = new Hitbox(x, y, w, h, pixmap)
  
  def highestPossibleJump = 400
  
  def update(delta: Float) = {

  }

}