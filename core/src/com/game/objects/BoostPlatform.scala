package com.game.objects

import com.game.AssetHandler
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion

class BoostPlatform(x: Float, y: Float, w: Float, h: Float) extends Platform(x, y, w, h) {
	
	val tex = new AtlasRegion(AssetHandler.getTexture(AssetHandler.Texture.BOOST_PLATFORM), 0, 0, 200, 30)
	
	def update(delta: Float) = {
		
	}
  
}