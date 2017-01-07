package com.game

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import scala.collection.mutable.Buffer

class Animation(t: Texture, spritesOnWidth: Int, spritesOnHeight: Int, fps: Int) {
  
  private val spritesTotal = spritesOnWidth * spritesOnHeight
  private val spriteWidth = t.getWidth / spritesOnWidth
  private val spriteHeight = t.getHeight / spritesOnHeight
  private val frameTime = 1f / fps
  private var timeUntilChange = frameTime
  
  private var currentSprite: AtlasRegion = _
  private val sprites = Buffer[AtlasRegion]()
  
  for (i <- 0 until spritesTotal) {
    val x = i % spritesOnWidth
    val y = i / spritesOnHeight
    sprites += new AtlasRegion(t, x * spriteWidth, y * spriteHeight, spriteWidth, spriteHeight)
  }
  
  currentSprite = sprites.remove(0)
  sprites += currentSprite
  
  def update(delta: Float) = {
    timeUntilChange -= delta / 60f
    if (timeUntilChange <= 0) {
      currentSprite = sprites.remove(0)
      sprites += currentSprite
      timeUntilChange += frameTime
    }
  }
  
  def getImage = currentSprite
  
}