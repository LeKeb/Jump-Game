package com.game.background

import com.game.AssetHandler._
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.Texture.TextureWrap
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion

class PalmTree(x: Float, w: Float, h: Float) {
  
  private val xCoord = x
  private val width = w
  private val height = h
  
  private val trunk = getTexture(Texture.PALM_TREE_TRUNK)
  private val top = getTexture(Texture.PALM_TREE_TOP)
  
  trunk.setWrap(TextureWrap.ClampToEdge, TextureWrap.Repeat) //Texture needs to wrap around at edges
  
  /**
   * Draw the tree
   */
  def draw(batch: SpriteBatch) = {
    batch.draw(trunk, x - width / 2, 0, width, height, 0, 0, trunk.getWidth, height.toInt, false, false)
    batch.draw(top, x - top.getWidth * (width / trunk.getWidth) / 2, height, top.getWidth * (width / trunk.getWidth), top.getHeight * (width / trunk.getWidth))
  }
  
}