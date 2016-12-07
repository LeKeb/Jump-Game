package com.game.objects

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
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
  
  private var xVelo = 0.toFloat
  private var yVelo = 0.toFloat

  private var xx = 1.toFloat

  def update(delta: Float) = {
    //Testipätkä alla.

    xCoord += xVelo
    yCoord += yVelo
    
    //Ideana on tehdä alle metodit, joita kutsutaan edellä.

    def jump = {
      //Hyödynnä sin-funktiota välillä 0, pi
    }

    //Pitää varmistaa, ettei pelaaja pääse ruudun ulkopuolelle. 
  }

  //Hitbox
  
  def setXVelo(x: Float) = {
    xVelo = x
  }
  
  def addVelo(x: Float, y: Float) = {
    xVelo = Math.min( Math.max(xVelo + x, -15), 15)
    yVelo = Math.min( Math.max(yVelo + y, -15), 15)
  }
  
  def draw(batch: SpriteBatch) = {
    batch.draw(tex, xCoord, yCoord, width, height)
  }

  def getPos = new Vector2(xCoord, yCoord)

}