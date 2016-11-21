package com.game.objects

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion

abstract class Platform(x: Float, y: Float, w: Float, h: Float) {

	protected var xCoord = x
	protected var yCoord = y
	protected var width = w
	protected var height = h
	
	protected val tex: AtlasRegion
	
	def update(delta: Float)
	
	def draw(batch : SpriteBatch) = {
		batch.draw(tex, xCoord, yCoord, width, height)
	}
	
}