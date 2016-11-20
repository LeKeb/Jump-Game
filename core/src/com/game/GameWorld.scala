package com.game

import scala.collection.mutable.Buffer
import com.game.objects.Platform
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.game.objects.Player
import com.badlogic.gdx.Gdx

class GameWorld {
  
	private val platforms = Buffer[Platform]()
	private val player = new Player(Gdx.graphics.getWidth - 60, 0, 120, 360)
	
	def update(delta: Float) = {
		
	}
	
	def draw(batch: SpriteBatch) = {
		
	}
	
}