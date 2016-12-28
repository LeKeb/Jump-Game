package com.game

import scala.collection.mutable.Buffer
import com.game.objects.Platform
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.game.objects.Player
import com.badlogic.gdx.Gdx
import com.game.objects.BreakablePlatform
import com.game.objects.NormalPlatform
import com.game.objects.BoostPlatform
import com.game.AssetHandler._

class GameWorld {
  
  private val platforms = Buffer[Platform]()
  private val player = new Player(Camera.renderWidth / 2 - 40, 50, 100, 100)
  private var leftPressed = false
  private var rightPressed = false
  
  platforms += new NormalPlatform(-100, -30, Camera.renderWidth + 200, 60)
    
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
    
    while (platforms.last.getY < player.getPos.y + Camera.renderHeight) {
      val rand = math.random
      val last = platforms.last
      if (rand < 0.05) {
        platforms += new BoostPlatform((math.random * (Camera.renderWidth - 200)).toFloat, last.getY + ((last.highestPossibleJump - 80) * Math.random() + 80).toFloat, 200, 40)
      } else if (rand < 0.15) {
        platforms += new BreakablePlatform((math.random * (Camera.renderWidth - 200)).toFloat, last.getY + ((last.highestPossibleJump - 80) * Math.random() + 80).toFloat, 200, 40)
      } else {
        platforms += new NormalPlatform((math.random * (Camera.renderWidth - 200)).toFloat, last.getY + ((last.highestPossibleJump - 80) * Math.random() + 80).toFloat, 200, 40)
      }
    }
    
    if (leftPressed)
      player.addXVelo(-1)
    if (rightPressed)
      player.addXVelo(1)
    
    platforms.foreach(_.update(delta))
    player.update(delta)
    
    for (plat <- platforms) {
      if (player.getHitBox.isColliding(plat.getHitBox) && player.getVelo.y < 0 && player.getThisJumpHighest > plat.getY + plat.getHeight) {
        plat match {
          case _: NormalPlatform => player.jump(1); Game.soundSystem.playSound(getSound(Sound.JUMP))
          case _: BoostPlatform => player.jump(3); Game.soundSystem.playSound(getSound(Sound.BOOST))
          case p: BreakablePlatform => p.break(); player.jump(0.5f)
        }
      }
    }
    if (player.getAllTimeHighestYCoord - 120 - (platforms.head.getY + platforms.head.getHeight) > Camera.renderHeight / 2)
      platforms.remove(0)
    if (player.getAllTimeHighestYCoord - 120 - (player.getPos.y) > Camera.renderHeight) {
      //Game over
      Game.soundSystem.playSound(getSound(Sound.GAME_OVER))
      Game.game.enterState(Game.mainMenuState)
    }
  }
  
  def draw(batch: SpriteBatch) = {
    platforms.foreach(_.draw(batch))
    player.draw(batch)
  }
  
  def getPlayer = player
  
}