package com.game

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Pixmap

/**
 * Some useful methods that are needed at a few places in the game
 */
object Utils {
  
  def screenCoordToGameCoord(point: Vector2): Vector2 = {
    new Vector2(point.x / Gdx.graphics.getWidth * Camera.renderWidth, point.y / Gdx.graphics.getHeight * Camera.renderHeight)
  }
  
  /**
   * Flips a specified pixmap
   */
  def flipPixmap(pm: Pixmap, flipX: Boolean, flipY: Boolean): Pixmap = {
    val flipped = new Pixmap(pm.getWidth, pm.getHeight, pm.getFormat)
    for (i <- 0 until pm.getWidth) {
      for (j <- 0 until pm.getHeight) {
        val dstX =
          if (flipX)
            pm.getWidth - 1 - i
          else
            i
        val dstY =
          if (flipY)
            pm.getHeight - 1 - j
          else
            j
        flipped.drawPixel(dstX, dstY, pm.getPixel(i, j))
      }
    }
    flipped
  }
  
}