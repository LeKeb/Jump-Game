package com.game.objects

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.game.AssetHandler
import com.badlogic.gdx.math.Vector2

class Player(x: Float, y: Float, w: Float, h: Float) {
  
  val tex = new AtlasRegion(AssetHandler.getTexture(AssetHandler.Texture.PLAYER), 0, 0, 256, 256)
  
  private var xCoord = x
  private var yCoord = y
  private var width = w
  private var height = h
  
  private var xx = 1.toFloat
  
  def update(delta: Float) = {
    xCoord += Math.sin(xx).toFloat * 10
    xx += 0.5.toFloat
  }
  
  def draw(batch: SpriteBatch) = {
    batch.draw(tex, xCoord, yCoord, width, height)
  }
  
  def getPos = new Vector2(xCoord, yCoord)
  
}