package com.game

import scala.collection.mutable.Buffer
import com.game.objects.Platform
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.game.objects.Player
import com.badlogic.gdx.Gdx
import com.game.objects.BreakablePlatform
import com.game.objects.NormalPlatform

class GameWorld {
  
	private val platforms = Buffer[Platform]()
	private val player = new Player(Camera.renderWidth / 2 - 40, 0, 80, 80)
	
	platforms += new BreakablePlatform(300, 400, 200, 30)
	platforms += new NormalPlatform(200, 500, 200, 30)
	platforms += new NormalPlatform(400, 600, 200, 30)
	platforms += new BreakablePlatform(600, 800, 200, 30)
	
	def update(delta: Float) = {
		
		if (Gdx.input.isTouched()) {
			platforms.filter(_.isInstanceOf[BreakablePlatform]).foreach(_.asInstanceOf[BreakablePlatform].break())
		}
		
		platforms.foreach(_.update(delta))
		player.update(delta)
	}
	
	def draw(batch: SpriteBatch) = {
		platforms.foreach(_.draw(batch))
		player.draw(batch)
	}
	
	def getPlayerPos = player.getPos
	
}