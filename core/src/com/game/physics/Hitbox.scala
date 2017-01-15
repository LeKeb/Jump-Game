package com.game.physics

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.Pixmap
import com.game.Utils

/**
 * Hitbox to be used in collision detection
 */
class Hitbox(x:Float, y:Float, w: Float, h: Float, pixm: Pixmap) {
  
  private var xCoord = x - w / 2
  private var yCoord = y - h / 2
  private var width = w
  private var height = h
  private var pixmap = Utils.flipPixmap(pixm, false, true)
  
  /**
   * Semi fast method of checking pixelperfect collision between this and another hitbox
   */
  def isColliding(hb: Hitbox): Boolean = {
    
    if ((xCoord + width <= hb.xCoord || xCoord > hb.xCoord + hb.width) ||
        (yCoord + height <= hb.yCoord || yCoord > hb.yCoord + hb.height)) {
      false
      
    } else {
      //only do pixel checking if bounding rectangles collide
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
  
  /**
   * Returns the collision itself between this and another hitbox or null if no collision occured
   */
  def getCollision(hb: Hitbox): Collision = {
    if ((xCoord + width <= hb.xCoord || xCoord > hb.xCoord + hb.width) ||
        (yCoord + height <= hb.yCoord || yCoord > hb.yCoord + hb.height)) {
      null
    } else {
      val x1 = Math.max(xCoord, hb.xCoord)
      val x2 = Math.min(xCoord + width, hb.xCoord + hb.width)
      val y1 = Math.max(yCoord, hb.yCoord)
      val y2 = Math.min(yCoord + height, hb.yCoord + hb.height)
      
      val collision1 = new Pixmap((x2 - x1).toInt, (y2 - y1).toInt, Pixmap.Format.RGBA8888)
      val collision2 = new Pixmap((x2 - x1).toInt, (y2 - y1).toInt, Pixmap.Format.RGBA8888)
      collision1.drawPixmap(pixmap, Math.min(0, xCoord - x1).toInt, Math.min(0, yCoord - y1).toInt)
      collision2.drawPixmap(hb.pixmap, Math.min(0, hb.xCoord - x1).toInt, Math.min(0, hb.yCoord - y1).toInt)
      
      var cx1 = collision1.getWidth.toFloat
      var cx2 = 0f
      var cy1 = collision1.getHeight.toFloat
      var cy2 = 0f
      
      for (i <- 0 until collision1.getWidth) {
        for (j <- 0 until collision1.getHeight) {
          if ((collision1.getPixel(i, j) & 0xff) != 0 && (collision2.getPixel(i, j) & 0xff) != 0) {
            cx1 = cx1.min(i)
            cx2 = cx2.max(i)
            cy1 = cy1.min(j)
            cy2 = cy2.max(j)
          }
        }
      }
      
      if (cx1 < collision1.getWidth)
        new Collision(cx1, cx2, cy1, cy2)
      else
        null
    }
  }
  
  /**
   * update the position of the hitbox
   */
  def setCoords(x: Float, y: Float) = {
    xCoord = x - w / 2
    yCoord = y - h / 2
  }
  
}