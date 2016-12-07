package com.game.ui

import com.game.Utils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.Gdx
import com.game.ui.component.Button
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.Input.Keys
import com.game.Game

class GameUi extends Ui {
  
  private val buttons = Vector[Button](
     
  )
  
  def draw(batch: SpriteBatch) = {
    buttons.foreach(_.draw(batch))
  }
  
  override def keyDown(key: Int): Boolean = {
    if (key == Keys.A || key == Keys.LEFT) {
      Game.gameState.getGame.buttonChanged(true, true)
    } else if (key == Keys.D || key == Keys.RIGHT) {
      Game.gameState.getGame.buttonChanged(false, true)
    }
    false
  }
  
  override def keyUp(key: Int): Boolean = {
    if (key == Keys.A || key == Keys.LEFT) {
      Game.gameState.getGame.buttonChanged(true, false)
    } else if (key == Keys.D || key == Keys.RIGHT) {
      Game.gameState.getGame.buttonChanged(false, false)
    }
    false
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