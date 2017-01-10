package com.game.background

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.game.Camera
import com.game.AssetHandler._
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.graphics.OrthographicCamera
import com.game.Game
import com.badlogic.gdx.graphics.Texture.TextureWrap

class Background {
  
  private val backTex = getTexture(Texture.BACKGROUND)
  private val parallax = getTexture(Texture.PARALLAX_BACKGROUND)
  private val parallax2 = getTexture(Texture.PARALLAX_BACKGROUND2)
  private val palm = new PalmTree(Camera.renderWidth / 2, 240, 100000)
  private val camera = new OrthographicCamera(Camera.renderWidth, Camera.renderHeight)
  private val backTex2 = getTexture(Texture.BACKGROUND2)
  
  parallax.setWrap(TextureWrap.ClampToEdge, TextureWrap.Repeat)
  parallax2.setWrap(TextureWrap.ClampToEdge, TextureWrap.Repeat)
  
  def draw(batch: SpriteBatch) = {
    val mat = batch.getProjectionMatrix.cpy()
    
    camera.position.set(Camera.renderWidth / 2, Camera.renderHeight / 2, 0)
    camera.update()
    batch.setProjectionMatrix(camera.combined)
    
    batch.draw(backTex2, 0, 0, Camera.renderWidth, Camera.renderHeight, 0, -(Game.gameState.getGame.getPlayer.getAllTimeHighestYCoord / 55).toInt, backTex2.getWidth, backTex2.getHeight, false, false)
       
    batch.draw(parallax, 0, 0, Camera.renderWidth, Camera.renderHeight, 0, -(Game.gameState.getGame.getPlayer.getAllTimeHighestYCoord / 50).toInt, parallax.getWidth, parallax.getHeight, false, false)
   
    camera.position.set(Camera.renderWidth / 2, Camera.renderHeight / 2 + Game.gameState.getGame.getPlayer.getAllTimeHighestYCoord / 10, 0)
    camera.update()
    batch.setProjectionMatrix(camera.combined)
    
    palm.draw(batch)
    
    camera.position.set(Camera.renderWidth / 2, Camera.renderHeight / 2 , 0)
    camera.update()
    batch.setProjectionMatrix(camera.combined)
    
    batch.draw(parallax2, 0, 0, Camera.renderWidth, Camera.renderHeight, 0, -(Game.gameState.getGame.getPlayer.getAllTimeHighestYCoord * 4 / 5).toInt, parallax.getWidth, parallax.getHeight, false, false)
    
    batch.setProjectionMatrix(mat)
  }
  
}