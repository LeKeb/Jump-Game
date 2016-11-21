package com.game.objects

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.game.AssetHandler

class NormalPlatform(x: Float, y: Float, w: Float, h: Float) extends Platform(x, y, w, h) {
  
	val tex = new AtlasRegion(AssetHandler.getTexture(AssetHandler.Texture.NORMAL_PLATFORM), 0, 0, 200, 30)
	
	def update(delta: Float) = {
		
	}
	
}