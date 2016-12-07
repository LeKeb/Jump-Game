package com.game

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.Gdx

object Utils {
  
  def screenCoordToGameCoord(point: Vector2): Vector2 = {
    new Vector2(point.x / Gdx.graphics.getWidth * Camera.renderWidth, point.y / Gdx.graphics.getHeight * Camera.renderHeight)
  }
  
}