package com.game.objects

import com.game.AssetHandler
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion

class BoostPlatform(x: Float, y: Float, w: Float, h: Float) extends Platform(x, y, w, h) {

  private val t = AssetHandler.getTexture(AssetHandler.Texture.BOOST_PLATFORM)
  protected val tex = new AtlasRegion(t, 0, 0, t.getWidth, t.getHeight)

  def update(delta: Float) = {

  }

}