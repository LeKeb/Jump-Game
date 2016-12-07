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
  private var leftPressed = false
  private var rightPressed = false
  
  platforms += new BreakablePlatform(300, 400, 200, 30)
  platforms += new NormalPlatform(200, 500, 200, 30)
  platforms += new NormalPlatform(400, 600, 200, 30)
  platforms += new BreakablePlatform(600, 800, 200, 30)
  
  def buttonChanged(left: Boolean, pressed: Boolean) = {
    if (left) {
      if (pressed)
        leftPressed = true
      else {
        leftPressed = false
        player.setXVelo(0)
      }
    } else {
      if (pressed)
        rightPressed = true
      else {
        rightPressed = false
        player.setXVelo(0)
      }
    }
  }
  
  def update(delta: Float) = {
    
    if (Gdx.input.isTouched()) {
      platforms.filter(_.isInstanceOf[BreakablePlatform]).foreach(_.asInstanceOf[BreakablePlatform].break())
    }
    
    if (leftPressed)
      player.addVelo(-1, 0)
    if (rightPressed)
      player.addVelo(1, 0)
    
    platforms.foreach(_.update(delta))
    player.update(delta)
  }
  
  def draw(batch: SpriteBatch) = {
    platforms.foreach(_.draw(batch))
    player.draw(batch)
  }
  
  def getPlayerPos = player.getPos
  
}