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
import com.game.objects.Item
import com.game.objects.Coconut
import com.game.objects.Coconut
import com.game.background.Background
import com.game.objects.Confuser
import com.game.objects.Confuser
import com.game.objects.Fire
import com.game.objects.Fire
import com.game.objects.Fire

class GameWorld {
  
  private val platforms = Buffer[Platform]()
  private val items = Buffer[Item]()
  private val player = new Player(Camera.renderWidth / 2, 50, 120, 120)
  private var leftPressed = false
  private var rightPressed = false
  
  private val background = new Background
  
  platforms += new NormalPlatform(Camera.renderWidth / 2, -30, Camera.renderWidth + 200, 60)
  
  def buttonChanged(left: Boolean, pressed: Boolean) = {
    if (left) {
      if (pressed)
        leftPressed = true
      else {
        leftPressed = false
      }
    } else {
      if (pressed)
        rightPressed = true
      else {
        rightPressed = false
      }
    }
  }
  
  def update(delta: Float) = {
    
    while (platforms.last.getY < player.getPos.y + Camera.renderHeight) {
      val rand = math.random
      val last = platforms.last
      if (rand < 0.05) {
        platforms += new BoostPlatform((math.random * (Camera.renderWidth - 200)).toFloat + 100, last.getY + ((last.highestPossibleJump - 80) * Math.random() + 80).toFloat, 200, 40)
      } else if (rand < 0.15 || items.exists(_.isInstanceOf[Fire]) || player.burning) {
        platforms += new BreakablePlatform((math.random * (Camera.renderWidth - 200)).toFloat + 100, last.getY + ((last.highestPossibleJump - 80) * Math.random() + 80).toFloat, 200, 40)
      } else {
        platforms += new NormalPlatform((math.random * (Camera.renderWidth - 200)).toFloat + 100, last.getY + ((last.highestPossibleJump - 80) * Math.random() + 80).toFloat, 200, 40)
      }
      
      val rand2 = math.random
      if (rand2 < 0.04)
        items += new Confuser(platforms.last.getItemPos.x, platforms.last.getItemPos.y, 80, 80)
      else if (rand2 < 0.08 && platforms.last.isInstanceOf[NormalPlatform])
        items += new Fire(platforms.last.getItemPos.x, platforms.last.getItemPos.y + 75, 200, 200)
    }
    
    if (Math.random() < 0.005) {
      items += new Coconut((Math.random() * (Camera.renderWidth - 100)).toFloat, player.getAllTimeHighestYCoord + Camera.renderHeight * 1.5f, 100, 150)
    }
    
    if (leftPressed)
      player.addXVelo(-2)
    if (rightPressed)
      player.addXVelo(2)
    
    items.foreach(_.update(delta))
    platforms.foreach(_.update(delta))
    player.update(delta)
    
    for (plat <- platforms) {
      if (player.getHitBox.isColliding(plat.getHitBox) && player.getVelo.y < 0 && player.getThisJumpHighest > plat.getY + plat.getHeight) {
        plat match {
          case _: NormalPlatform => player.jump(1); Game.soundSystem.playSound(getSound(Sound.JUMP))
          case _: BoostPlatform => player.jump(3); Game.soundSystem.playSound(getSound(Sound.BOOST))
          case p: BreakablePlatform => p.break(); player.jump(1)
        }
      }
    }
    
    val toRemove = Buffer[Item]()
    for (item <- items) {
      if (player.getHitBox.isColliding(item.getHitBox)) {
        item match {
          case _: Coconut => player.blackOut(10); Game.soundSystem.playSound(getSound(Sound.STUNNED)); toRemove += item
          case _: Confuser => player.confuse(10); Game.soundSystem.playSound(getSound(Sound.CONFUSE)); toRemove += item
          case _: Fire => player.burn(10); Game.soundSystem.playSound(getSound(Sound.SCREAM))
        }
      }
      if (player.getAllTimeHighestYCoord - 120 - (item.getY + item.getHeight) > Camera.renderHeight / 2)
        toRemove += item
    }
    items --= toRemove
    
    if (player.getAllTimeHighestYCoord - 120 - (platforms.head.getY + platforms.head.getHeight) > Camera.renderHeight / 2)
      platforms.remove(0)
    if (player.getAllTimeHighestYCoord - 120 - (player.getPos.y) > Camera.renderHeight) {
      //Game over
      Game.soundSystem.playSound(getSound(Sound.GAME_OVER))
      Game.game.enterState(Game.mainMenuState)
    }
  }
  
  def draw(batch: SpriteBatch) = {
    background.draw(batch)
    platforms.foreach(_.draw(batch))
    items.foreach(_.draw(batch))
    player.draw(batch)
  }
  
  def getPlayer = player
  
}