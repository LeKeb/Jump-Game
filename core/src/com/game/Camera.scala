package com.game

import com.badlogic.gdx.graphics.OrthographicCamera

object Camera {
  val renderWidth = 720.toFloat
  val renderHeight = 1280.toFloat
}

class Camera {
  
  private var cam: OrthographicCamera = new OrthographicCamera(Camera.renderWidth, Camera.renderHeight)
  
  def update() = cam.update()
  
  def setPosition(x: Float, y: Float) = {
    cam.position.x = x
    cam.position.y = y
  }
  
  def getCamera = cam
  
}