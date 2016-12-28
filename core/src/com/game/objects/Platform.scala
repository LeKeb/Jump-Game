package com.game.objects

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.badlogic.gdx.graphics.Pixmap
import com.game.physics.Hitbox
import com.game.AssetHandler._

abstract class Platform(x: Float, y: Float, w: Float, h: Float) {

  protected var xCoord = x
  protected var yCoord = y
  protected var width = w
  protected var height = h
  
  private val t = getTexture(Texture.NORMAL_PLATFORM)
  protected var tex = new AtlasRegion(t, 0, 0, t.getWidth, t.getHeight)
  
  private val texture = tex.getTexture
  protected val pixmap = new Pixmap(w.toInt, h.toInt, Pixmap.Format.RGBA8888)
  texture.getTextureData.prepare()
  pixmap.drawPixmap(tex.getTexture.getTextureData.consumePixmap(), 0, 0, texture.getWidth, texture.getHeight, 0, 0, w.toInt, h.toInt)
  
  protected val hitbox = new Hitbox(x, y, w, h, pixmap)
  
  def update(delta: Float)
  
  def highestPossibleJump: Float
  
  def draw(batch : SpriteBatch) = {
    batch.draw(tex, xCoord, yCoord, width, height)
  //Onko syytä varmistaa, ettei hyppytasoja voi luoda pelikentän koordinaattien ulkopuolelle?
  }
  
  def getHitBox = hitbox
  
  def getY = yCoord
  
  def getHeight = height
  
}