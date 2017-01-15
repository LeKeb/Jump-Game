package com.game.objects

import com.game.AssetHandler
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.game.AssetHandler.Texture
import com.game.AssetHandler.Sound
import com.game.Game
import com.badlogic.gdx.graphics.Pixmap
import com.game.physics.Hitbox

/**
 * A platform that breaks when jumped on
 */
class BreakablePlatform(x: Float, y: Float, w: Float, h: Float) extends Platform(x, y, w, h) {
  
  private val t1 = AssetHandler.getTexture(Texture.BREAKABLE_PLATFORM)
  private val t2 = AssetHandler.getTexture(Texture.BREAKABLE_PLATFORM_BROKEN)
  tex = new AtlasRegion(t1, 0, 0, t1.getWidth, t1.getHeight)
  private val texLeft = new AtlasRegion(t2, 0, 0, t2.getWidth, t2.getHeight / 2)
  private val texRight = new AtlasRegion(t2, 0, t2.getHeight / 2, t2.getWidth, t2.getHeight / 2)
  
  //Creates the pixmap for pixelperfect hitdetection
  private val pixmap = new Pixmap(w.toInt, h.toInt, Pixmap.Format.RGBA8888)
  t1.getTextureData.prepare()
  pixmap.drawPixmap(tex.getTexture.getTextureData.consumePixmap(), 0, 0, t1.getWidth, t1.getHeight, 0, 0, w.toInt, h.toInt)
  
  hitbox = new Hitbox(x, y, w, h, pixmap)
  
  private val crackSound = AssetHandler.getSound(Sound.CRACK)
  
  private var broken: Boolean = false
  private var leftX = xCoord - width / 2 //the variables for each half after its broken
  private var leftY = yCoord - height / 2
  private var rightX = xCoord - width / 2
  private var rightY = yCoord - height / 2
  private var leftXVelo = 0f
  private var leftYVelo = 0f
  private var rightXVelo = 0f
  private var rightYVelo = 0f
  private var leftRot = 0f
  private var rightRot = 0f
  
  def highestPossibleJump = 400
  
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
      batch.draw(texLeft, leftX, leftY, width / 4, height / 2, width, height, 1, 1, leftRot) //draw the halfs and rotate them correctly
      batch.draw(texRight, rightX, rightY, width * 3 / 4, height / 2, width, height, 1, 1, rightRot)
    } else {
      batch.draw(tex, xCoord - width / 2, yCoord - height / 2, width, height)
    }
  }
  
  def break() = {
    //break the platform
    if (!broken) {
      broken = true
      leftXVelo = (-Math.random() * 3).toFloat
      rightXVelo = (Math.random() * 3).toFloat
      Game.soundSystem.playSound(crackSound)
      hitbox.setCoords(-10000, -10000)
    }
  }
  
}