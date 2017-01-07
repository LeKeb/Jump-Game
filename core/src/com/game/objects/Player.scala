package com.game.objects

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.game.AssetHandler._
import com.badlogic.gdx.math.Vector2
import com.game.physics.Hitbox
import com.badlogic.gdx.graphics.Pixmap

class Player(x: Float, y: Float, w: Float, h: Float) {
  
  private val blackOut = getTexture(Texture.BLACK_OUT)
  private val texture = getTexture(Texture.PLAYER)
  private val tex = new AtlasRegion(texture, 0, 0, 372, 364)
  
  private val pixmap = new Pixmap(w.toInt, h.toInt, Pixmap.Format.RGBA8888)
  tex.getTexture.getTextureData.prepare()
  pixmap.drawPixmap(tex.getTexture.getTextureData.consumePixmap(), 0, 0, texture.getWidth, texture.getHeight, 0, 0, w.toInt, h.toInt)
  
  private val hitbox = new Hitbox(x, y, w, h, pixmap)
  
  private var xCoord = x
  private var yCoord = y
  private var width = w
  private var height = h
  
  private var lookingLeft = false
  
  private var xVelo = 0.toFloat
  private var yVelo = 0.toFloat

  private var xx = 1.toFloat
  
  private var isBlackedOut = false
  private var blackOutLeft = 0f
  
  private var isConfused = false
  private var confuseLeft = 0f
  
  private var yAllTimeMax = yCoord
  private var yThisJumpHighest = yCoord

  def update(delta: Float) = {
    
    xCoord += xVelo * delta
    yCoord += yVelo * delta
    
    yVelo -= 1f * delta
    
    xVelo *= 0.9f * delta
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
    
    hitbox.setCoords(xCoord, yCoord)
    
    blackOutLeft -= delta / 60f
    confuseLeft -= delta / 60f
    
    if (blackOutLeft <= 0)
      isBlackedOut = false
    if (confuseLeft <= 0)
      isConfused = false
      
    //Pitää varmistaa, ettei pelaaja pääse ruudun ulkopuolelle. 
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
  
  def jump(multiplier: Float) {
    yThisJumpHighest = yCoord
    yVelo = 30f * multiplier
  }
  
  def draw(batch: SpriteBatch) = {
    if (!lookingLeft)
      batch.draw(tex, xCoord - width / 2, yCoord - height / 2, width, height)
    else
      batch.draw(tex, xCoord + width / 2, yCoord - height / 2, -width, height)
    if (isBlackedOut) 
      batch.draw(blackOut, xCoord + width / 2 - blackOut.getWidth / 2, yCoord + height / 2 - blackOut.getHeight / 2)
  }

  def getPos = new Vector2(xCoord, yCoord)
  
  def getVelo = new Vector2(xVelo, yVelo)
  
  def getHitBox = hitbox
  
  def getAllTimeHighestYCoord = yAllTimeMax
  
  def getThisJumpHighest = yThisJumpHighest
  
  def blackOut(time: Float) = {
    isBlackedOut = true
    blackOutLeft = time
  }
  
  def confuse(time: Float) = {
    xVelo = 0
    isConfused = true
    confuseLeft = time
  }
  
}