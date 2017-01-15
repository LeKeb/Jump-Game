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

/**
 * The gameworld, holds all the objects
 */
class GameWorld(hard: Boolean) {
  
  private val platforms = Buffer[Platform]()
  private val items = Buffer[Item]()
  private val player = new Player(Camera.renderWidth / 2, 100, 120, 120)
  private var leftPressed = false
  private var rightPressed = false
  private val hardcore = hard
  
  private val background = new Background
  
  private var gameRendered = 0 //used for smoothing out the game start
  private var gameOver = false
  
  platforms += new NormalPlatform(Camera.renderWidth / 2, -30, Camera.renderWidth + 200, 60) //the "ground"
  
  /**
   * move the player if a button is pressed
   */
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
  
  /**
   * updates the game
   */
  def update(delta: Float) = {
    
    if (hardcore) {
      //hardcore mode, really simple game generation
      while (platforms.last.getY < player.getPos.y + Camera.renderHeight) {
        if (math.random < 0.05)
          platforms += new BoostPlatform((math.random * (Camera.renderWidth - 200)).toFloat + 100, platforms.last.getY + platforms.last.highestPossibleJump - 40, 200, 40)
        else
          platforms += new BreakablePlatform((math.random * (Camera.renderWidth - 200)).toFloat + 100, platforms.last.getY + platforms.last.highestPossibleJump - 40, 200, 40)
        
        if (math.random < 0.05)
          items += new Confuser(platforms.last.getItemPos.x, platforms.last.getItemPos.y, 80, 80)
        
      }
      
      if (math.random < 0.05)
        items += new Coconut((50f + math.random * (Camera.renderWidth - 50)).toFloat , player.getAllTimeHighestYCoord + Camera.renderHeight * 1.5f, 100, 150)
      
      player.burn(10)
      
    } else {
      //generate platforms if needed, platform type and spacing changes as the score grows
      while (platforms.last.getY < player.getPos.y + Camera.renderHeight) {
    
        val rand = math.random //to generate platforms
        var last = platforms.last
      
        val rand2 = math.random //to generate items
      
        val break = (math.log10(last.getY / 10 * 3 - 3000) - 2) / 10
        val boost = (math.log10(last.getY / 10 * 3 - 6000) - 2) / 50
      
        var minDistVar = (last.highestPossibleJump * 4 / 5 * (last.getY / 10 / 100000).min(1))
        var nextY = (last.getY + last.highestPossibleJump / 5f + minDistVar + ((last.highestPossibleJump * 4f / 5f - minDistVar) * (math.random * (math.pow(1.01, last.getY / 10 / 100)) / 3).min(1))).toFloat
      
        if (rand < break) {
          platforms += new BreakablePlatform((math.random * (Camera.renderWidth - 200)).toFloat + 100, nextY, 200, 40)
        } else if (rand < boost + break) {
          platforms += new BoostPlatform((math.random * (Camera.renderWidth - 200)).toFloat + 100, nextY, 200, 40)
        } else if (rand < boost + break + 0.01) {
          for (i <- 0 until (math.random * getScore / 1000f).toInt) {
            last = platforms.last
            minDistVar = (last.highestPossibleJump * 4 / 5 * (getScore / 50000).min(1))
            nextY = (last.getY + last.highestPossibleJump / 5f + minDistVar + ((last.highestPossibleJump * 4f / 5f - minDistVar) * (math.random * (math.pow(1.01, last.getY / 10 / 100)) / 3).min(1))).toFloat
            platforms += new BreakablePlatform((math.random * (Camera.renderWidth - 200)).toFloat + 100, nextY, 200, 40)
          }
        } else {
          platforms += new NormalPlatform((math.random * (Camera.renderWidth - 200)).toFloat + 100, nextY, 200, 40)
        }
        
        //generates items to the platforms, scales as the score grows
        val confuse = (0.01 + 0.02 * ((last.getY / 10 - 10000) / 50000)) / 2
        val fire = confuse * 2
      
        if (last.getY / 10 > 10000 && rand2 < confuse)
          items += new Confuser(last.getItemPos.x, last.getItemPos.y, 80, 80)
        else if (last.getY / 10 > 6000 && rand2 < confuse + fire && !last.isInstanceOf[BreakablePlatform])
          items += new Fire(platforms.last.getItemPos.x, platforms.last.getItemPos.y + 70, 200, 200)
      }
      
      //adds coconuts to the game, scales as the score grows
      if (getScore > 1000 && math.random < (0.001 + (getScore / 200000f) * 0.05))
        items += new Coconut((50f + math.random * (Camera.renderWidth - 50)).toFloat , player.getAllTimeHighestYCoord + Camera.renderHeight * 1.5f, 100, 150)
      
    }
    
    //move player if a button is pressed
    if (leftPressed)
      player.addXVelo(-2)
    if (rightPressed)
      player.addXVelo(2)
    
    //only update the objects if game has been rendered more than 5 times
    //this way the frames where the game is created is skipped, where the delta is really high
    if (gameRendered > 5) {
      items.foreach(_.update(delta))
      platforms.foreach(_.update(delta))
      player.update(delta)
    }
    
    //check collision between player and platforms
    for (plat <- platforms) {
      if (player.getVelo.y < 0 && player.getHitBox.isColliding(plat.getHitBox) && player.getThisJumpHighest - player.getHeight / 2 > plat.getY + plat.getHeight / 2) {
        val collision = player.getHitBox.getCollision(plat.getHitBox)
        player.move(0, collision.y2 - collision.y1)
        plat match {
          case _: NormalPlatform => player.jump(1); Game.soundSystem.playSound(getSound(Sound.JUMP))
          case _: BoostPlatform => player.jump(3); Game.soundSystem.playSound(getSound(Sound.BOOST))
          case p: BreakablePlatform => p.break(); player.jump(1); Game.soundSystem.playSound(getSound(Sound.JUMP))
        }
      }
    }
    
    val toRemove = Buffer[Item]()
    for (item <- items) {
      if (player.getHitBox.isColliding(item.getHitBox)) {
        item match {
          case _: Coconut => player.blackOut(10); Game.soundSystem.playSound(getSound(Sound.STUNNED)); toRemove += item
          case _: Confuser => player.confuse(10); Game.soundSystem.playSound(getSound(Sound.CONFUSE)); toRemove += item
          case _: Fire => player.burn(8); Game.soundSystem.playSound(getSound(Sound.SCREAM))
        }
      }
      if (player.getAllTimeHighestYCoord - 120 - (item.getY + item.getHeight) > Camera.renderHeight / 2)
        toRemove += item
    }
    items --= toRemove
    
    //remove all platforms that are below the screen
    if (player.getAllTimeHighestYCoord - 120 - (platforms.head.getY + platforms.head.getHeight) > Camera.renderHeight / 2)
      platforms.remove(0)
    if (player.getAllTimeHighestYCoord - 120 - (player.getPos.y) > Camera.renderHeight) {
      //game over
      Game.soundSystem.stopMusic()
      Game.soundSystem.playSound(getSound(Sound.GAME_OVER))
      gameOver = true
    }
  }
  
  /**
   * draw the gameworld
   */
  def draw(batch: SpriteBatch) = {
    background.draw(batch)
    platforms.foreach(_.draw(batch))
    items.foreach(_.draw(batch))
    player.draw(batch)
    gameRendered += 1
  }
  
  def getPlayer = player
  
  def isGameOver = gameOver
  
  def getScore = (player.getAllTimeHighestYCoord / 10).toInt //currents score
  
}