package com.game.ui

import com.game.ui.component.Button
import com.game.Camera
import com.game.AssetHandler
import com.game.AssetHandler.Texture
import com.game.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.Gdx
import com.game.Utils

class MainMenuUi extends Ui {
  
  private val buttons = Vector[Button](
    new Button(Camera.renderWidth / 2, Camera.renderHeight * 3 / 4, 375, 188, AssetHandler.getTexture(Texture.PLAY_BUTTON),
    () => (Game.game.enterState(Game.gameState))    
    )  
  )
  
  def draw(batch: SpriteBatch) = {
    buttons.foreach(_.draw(batch))
  }
    
  override def touchDown(x: Int, y: Int, pointer: Int, button: Int): Boolean = {
    val point = Utils.screenCoordToGameCoord(new Vector2(x, Gdx.graphics.getHeight - y))
    for (i <- buttons) {
      if (i.containsPoint(point)) {
        i.press()
        return true
      }
    }
    false
  }
  
  override def touchDragged(x: Int, y: Int, pointer: Int): Boolean = {
    val point = Utils.screenCoordToGameCoord(new Vector2(x, Gdx.graphics.getHeight - y))
    for (i <- buttons) {
      if (i.isButtonPressed && !i.containsPoint(point)) {
        i.reset()
      }
    }
    false
  }
  
  override def touchUp(x: Int, y: Int, pointer: Int, button: Int): Boolean = {
    val point = Utils.screenCoordToGameCoord(new Vector2(x, Gdx.graphics.getHeight - y))
    for (i <- buttons) {
      if (i.containsPoint(point)) {
        i.release()
        return true
      }
    }
    false
  }
  

}