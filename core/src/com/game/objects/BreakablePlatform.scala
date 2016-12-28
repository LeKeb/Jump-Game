package com.game.objects

import com.game.AssetHandler
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.game.AssetHandler.Texture
import com.game.AssetHandler.Sound
import com.game.Game

class BreakablePlatform(x: Float, y: Float, w: Float, h: Float) extends Platform(x, y, w, h) {
  
  private val t1 = AssetHandler.getTexture(Texture.BREAKABLE_PLATFORM)
  private val t2 = AssetHandler.getTexture(Texture.BREAKABLE_PLATFORM_BROKEN)
  tex = new AtlasRegion(t1, 0, 0, t1.getWidth, t1.getHeight)
  private val texLeft = new AtlasRegion(t2, 0, 0, t2.getWidth, t2.getHeight / 2)
  private val texRight = new AtlasRegion(t2, 0, t2.getHeight / 2, t2.getWidth, t2.getHeight / 2)
  
  private val crackSound = AssetHandler.getSound(Sound.CRACK)
  
  private var broken: Boolean = false
  private var leftX = xCoord
  private var leftY = yCoord
  private var rightX = xCoord
  private var rightY = yCoord
  private var leftXVelo = 0.toFloat
  private var leftYVelo = 0.toFloat
  private var rightXVelo = 0.toFloat
  private var rightYVelo = 0.toFloat
  private var leftRot = 0.toFloat
  private var rightRot = 0.toFloat
  
  def highestPossibleJump = 75
  
  def update(delta: Float) = {
    if (broken) {
      leftX += leftXVelo
      leftY += leftYVelo
      rightX += rightXVelo
      rightY += rightYVelo
      
      leftYVelo -= 0.5.toFloat
      rightYVelo -= 0.5.toFloat
      
      leftRot -= (Math.PI / 2).toFloat
      rightRot += (Math.PI / 2).toFloat
    }
  }
  
  override def draw(batch: SpriteBatch) = {
    if (broken) {
      batch.draw(texLeft, leftX, leftY, width / 4, height / 2, width, height, 1, 1, leftRot)
      batch.draw(texRight, rightX, rightY, width * 3 / 4, height / 2, width, height, 1, 1, rightRot)
    } else {
      batch.draw(tex, xCoord, yCoord, width, height)
    }
  }
  
  def break() = {
    if (!broken) {
      broken = true
      leftXVelo = (-Math.random() * 3).toFloat
      rightXVelo = (Math.random() * 3).toFloat
      Game.soundSystem.playSound(crackSound)
      hitbox.setCoords(-10000, -10000)
    }
  }
  
}