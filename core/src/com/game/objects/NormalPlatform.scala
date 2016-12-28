package com.game.objects

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.game.AssetHandler

class NormalPlatform(x: Float, y: Float, w: Float, h: Float) extends Platform(x, y, w, h) {
  
  private val t = AssetHandler.getTexture(AssetHandler.Texture.NORMAL_PLATFORM)
  tex = new AtlasRegion(t, 0, 0, t.getWidth, t.getHeight)
  
  def highestPossibleJump = 425
  
  def update(delta: Float) = {
    
  }
  
}