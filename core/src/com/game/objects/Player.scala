package com.game.objects

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.game.AssetHandler._
import com.badlogic.gdx.math.Vector2
import com.game.physics.Hitbox
import com.badlogic.gdx.graphics.Pixmap
import scala.collection.mutable.Buffer
import com.game.Animation
import com.game.Camera
import com.game.Utils

/**
 * The player itself
 */
class Player(x: Float, y: Float, w: Float, h: Float) {
  
  private val texture = getTexture(Texture.PLAYER)
  private val tex = new AtlasRegion(texture, 0, 0, 372, 364)
  private val fire = new Animation(getTexture(Texture.FIRE), 8, 8, 30)
  
  private val pixmap = new Pixmap(w.toInt, h.toInt, Pixmap.Format.RGBA8888)
  tex.getTexture.getTextureData.prepare()
  pixmap.drawPixmap(tex.getTexture.getTextureData.consumePixmap(), 0, 0, texture.getWidth, texture.getHeight, 0, 0, w.toInt, h.toInt)
    
  private val rightHitbox = new Hitbox(x, y, w, h, pixmap)
  private val leftHitbox = new Hitbox(x, y, w, h, Utils.flipPixmap(pixmap, true, false))
  
  private var xCoord = x
  private var yCoord = y
  private var width = w
  private var height = h
  
  private var lookingLeft = false
  
  private var xVelo = 0.toFloat
  private var yVelo = 0.toFloat

  private var xx = 1.toFloat
  
  private var blackOuts = Buffer[Float]() //to keep track of all the different times of the different blackouts
  
  private var isConfused = false
  private var confuseLeft = 0f
  
  private var isBurning = false
  private var burnLeft = 0f
  
  private var yAllTimeMax = yCoord //used for various things, mainly score tracking
  private var yThisJumpHighest = yCoord //used for determing highest point of current jump

  def update(delta: Float) = {
    
    var multiplier = 1f //used as a boost or slow down for players speed
    
    if (isBurning)
      multiplier = 1.5f
    
    xCoord += xVelo * delta * multiplier
    yCoord += yVelo * delta * multiplier
    
    //wrap players position the the opposite side of window if it goes outside
    if (xCoord + width / 2 < 0) 
      xCoord = Camera.renderWidth + width / 2
    if (xCoord - width / 2 > Camera.renderWidth)
      xCoord = -width / 2
    
    yVelo -= (1f * delta * multiplier) //gravity
    
    xVelo *= 0.9f //friction
    if (xVelo.abs < 0.01)
      xVelo = 0
    
    if (xVelo != 0) {
      if (xVelo > 0) {
        lookingLeft = false
      } else {
        lookingLeft = true
      }
    }
    
    yThisJumpHighest = Math.max(yCoord, yThisJumpHighest)
    yAllTimeMax = Math.max(yCoord, yAllTimeMax) 
    
    rightHitbox.setCoords(xCoord, yCoord)
    leftHitbox.setCoords(xCoord, yCoord)
    
    blackOuts = blackOuts.map(_ -delta / 60f).filter(_ > 0)
    confuseLeft -= delta / 60f
    burnLeft -= delta / 60f
    
    if (confuseLeft <= 0)
      isConfused = false
    if (burnLeft <= 0)
      isBurning = false
    
    if (isBurning)
      fire.update(delta) //update the fire animation
    
    if (confuseLeft > 0) {
      lookingLeft = ((confuseLeft * 5).toInt % 2) == 1 //flip the direction player looks towards for a "confuse effect"
    }
  }

  
  def setXVelo(x: Float) = {
    if (isConfused)
      xVelo = -Math.min( Math.max(x, -15), 15)
    else
      xVelo = Math.min( Math.max(x, -15), 15)
  }
  
  def addXVelo(x: Float) = {
    if (isConfused)
      xVelo = Math.min( Math.max(xVelo - x, -15), 15)
    else
      xVelo = Math.min( Math.max(xVelo + x, -15), 15)
  }
  
  def move(x: Float, y: Float) = {
    xCoord += x
    yCoord += y
  }
  
  def jump(multiplier: Float) {
    yThisJumpHighest = yCoord
    yVelo = 30f * multiplier
  }
  
  def draw(batch: SpriteBatch) = {
    if (!lookingLeft)
      batch.draw(tex, xCoord - width / 2, yCoord - height / 2, width, height)
    else
      batch.draw(tex, xCoord + width / 2, yCoord - height / 2, -width, height)
    if (isBurning) {
      if (!lookingLeft)
        batch.draw(fire.getImage, xCoord - width * 3 / 5, yCoord - height / 3, width * 9 / 10, height * 2f)
      else
        batch.draw(fire.getImage, xCoord + width * 3 / 5, yCoord - height / 3, -width * 9 / 10, height * 2f)
    }
  }
  
  def getHeight = height
  
  def getPos = new Vector2(xCoord, yCoord)
  
  def getVelo = new Vector2(xVelo, yVelo)
  
  def getHitBox = {
    if (lookingLeft)
      leftHitbox
    else
      rightHitbox
  }
  
  def getAllTimeHighestYCoord = yAllTimeMax
  
  def getThisJumpHighest = yThisJumpHighest
  
  def blackOut(time: Float) = {
    blackOuts += time
  }
  
  def getBlackoutLevel = blackOuts.length
  
  def confuse(time: Float) = {
    xVelo = 0
    isConfused = true
    confuseLeft = time
  }
  
  def burn(time: Float) = {
    isBurning = true
    burnLeft = time
  }
  
  def burning = isBurning
  
}