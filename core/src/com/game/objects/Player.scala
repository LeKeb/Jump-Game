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

  private var xx = 1.toFloat

  def update(delta: Float) = {
    //Testipätkä alla.
    // xCoord += Math.sin(xx).toFloat * 20
    // xx += 0.5.toFloat

    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
      move(-10.toFloat, 0)
    }
    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
      move(10.toFloat, 0)
    }
    if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
      move(0.toFloat, -10.toFloat)
    }
    if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
      move(0.toFloat, 10.toFloat)
    }
    //Ideana on tehdä alle metodit, joita kutsutaan edellä.

    def jump = {
      //Hyödynnä sin-funktiota välillä 0, pi
    }

    def move(moveSide: Float, moveUp: Float) = {
      xCoord += moveSide
      yCoord += moveUp
    }
    //Pitää varmistaa, ettei pelaaja pääse ruudun ulkopuolelle. 
  }

  //Hitbox

  def draw(batch: SpriteBatch) = {
    batch.draw(tex, xCoord, yCoord, width, height)
  }

  def getPos = new Vector2(xCoord, yCoord)

}