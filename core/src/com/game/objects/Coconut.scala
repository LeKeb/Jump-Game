package com.game.objects

import com.game.physics.Hitbox
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.game.AssetHandler._
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.game.Game
import com.game.Camera
import com.badlogic.gdx.graphics.OrthographicCamera

/**
 * Coconut item, if hit by player part of the sceen is blurred and vision gets worse
 */
class Coconut(x: Float, y: Float, w: Float, h: Float) extends Item(x, y, w, h){
  
  private val texture = getTexture(Texture.COCONUT)
  private val warnTex = getTexture(Texture.WARNING)
  
  //Creates the pixmap for pixelperfect hitdetection
  private val pixmap = new Pixmap(w.toInt, h.toInt, Pixmap.Format.RGBA8888)
  texture.getTextureData.prepare()
  pixmap.drawPixmap(texture.getTextureData.consumePixmap(), 0, 0, texture.getWidth, texture.getHeight, 0, 0, w.toInt, h.toInt)
  
  protected val hitbox = new Hitbox(x, y, w, h, pixmap)
  
  protected val tex: AtlasRegion = new AtlasRegion(texture, 0, 0, texture.getWidth, texture.getHeight)
  
  private var xVelo = 0f
  private var yVelo = 0f
  
  private val cam = new OrthographicCamera(Camera.renderWidth, Camera.renderHeight)
  
  cam.position.set(Camera.renderWidth / 2, Camera.renderHeight / 2, 0)
  cam.update()
  
  override def draw(batch: SpriteBatch) = {
    batch.draw(tex, xCoord - width / 2, yCoord - height / 2, width, height)
    if (yCoord > Game.gameState.getGame.getPlayer.getAllTimeHighestYCoord) {
      val mat = batch.getProjectionMatrix.cpy()
      batch.setProjectionMatrix(cam.combined)
      batch.draw(warnTex, xCoord - 16.5f, Camera.renderHeight - 120, 33, 100) //Draw an exclamation mark before the coconut actually arrives on screen
      batch.setProjectionMatrix(mat)
    }
  }
  
  def update(delta: Float): Unit = {
    xCoord += xVelo * delta
    yCoord += yVelo * delta
    
    yVelo = Math.max(yVelo - 1 * delta, -15)
    
    hitbox.setCoords(xCoord, yCoord)
  }

}