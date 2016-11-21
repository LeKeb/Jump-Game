package com.game

import scala.collection.mutable.Buffer
import com.game.objects.Platform
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.game.objects.Player
import com.badlogic.gdx.Gdx

class GameWorld {
  
	private val platforms = Buffer[Platform]()
	private val player = new Player(Gdx.graphics.getWidth / 2 - 20, 0, 40, 40)
	
	def update(delta: Float) = {
		platforms.foreach(_.update(delta))
		player.update(delta)
	}
	
	def draw(batch: SpriteBatch) = {
		platforms.foreach(_.draw(batch))
		player.draw(batch)
	}
	
}