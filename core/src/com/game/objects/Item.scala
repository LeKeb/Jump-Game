package com.game.objects

import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.game.physics.Hitbox
import com.badlogic.gdx.graphics.g2d.SpriteBatch

abstract class Item(x: Float, y: Float, w: Float, h: Float) {
  
  protected var xCoord = x
  protected var yCoord = y
  protected var width = w
  protected var height = h
  
  protected val tex: AtlasRegion
  protected val hitbox: Hitbox
  
  def update(delta: Float)
  
  def draw(batch : SpriteBatch) = {
    batch.draw(tex, xCoord, yCoord, width, height)
  }
  
  def getHitBox = hitbox
  
  def getY = yCoord
  
  def getHeight = height
  
}