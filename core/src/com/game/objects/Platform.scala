package com.game.objects

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.badlogic.gdx.graphics.Pixmap
import com.game.physics.Hitbox
import com.game.AssetHandler._
import com.badlogic.gdx.math.Vector2

/**
 * An abstract class that all other platforms must extend
 */
abstract class Platform(x: Float, y: Float, w: Float, h: Float) {

  protected var xCoord = x
  protected var yCoord = y
  protected var width = w
  protected var height = h
  
  protected var tex: AtlasRegion = _
  
  protected var hitbox: Hitbox = _
  
  protected var itemPos = new Vector2(xCoord, yCoord + height)
  
  def update(delta: Float)
  
  def highestPossibleJump: Float
  
  def draw(batch : SpriteBatch) = {
    batch.draw(tex, xCoord - width / 2, yCoord - height / 2, width, height) //default draw method for a platform, can be overridden
  }
  
  def getHitBox = hitbox
  
  def getY = yCoord
  
  def getHeight = height
  
  def getItemPos = itemPos
}