package com.game.physics

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.Pixmap

class Hitbox(x:Float, y:Float, w: Float, h: Float, pixm: Pixmap) {
  
  private var xCoord = x
  private var yCoord = y
  private var width = w
  private var height = h
  private var pixmap = pixm
  
  def isColliding(hb: Hitbox): Boolean = {
    if ((xCoord + width <= hb.xCoord || xCoord > hb.xCoord + hb.width) ||
        (yCoord + height <= hb.yCoord || yCoord > hb.yCoord + hb.height)) {
      false
    } else {
      val x1 = Math.max(xCoord, hb.xCoord)
      val x2 = Math.min(xCoord + width, hb.xCoord + hb.width)
      val y1 = Math.max(yCoord, hb.yCoord)
      val y2 = Math.min(yCoord + height, hb.yCoord + hb.height)
      
      val collision1 = new Pixmap((x2 - x1).toInt, (y2 - y1).toInt, Pixmap.Format.RGBA8888)
      val collision2 = new Pixmap((x2 - x1).toInt, (y2 - y1).toInt, Pixmap.Format.RGBA8888)
      collision1.drawPixmap(pixmap, Math.min(0, xCoord - x1).toInt, Math.min(0, yCoord - y1).toInt)
      collision2.drawPixmap(hb.pixmap, Math.min(0, hb.xCoord - x1).toInt, Math.min(0, hb.yCoord - y1).toInt)
      for (i <- 0 until collision1.getWidth) {
        for (j <- 0 until collision1.getHeight) {
            if ((collision1.getPixel(i, j) & 0xff) != 0 && (collision2.getPixel(i, j) & 0xff) != 0)
              return true
        }
      }
      false
    }
  }
  
  def setCoords(x: Float, y: Float) = {
    xCoord = x
    yCoord = y
  }
  
}