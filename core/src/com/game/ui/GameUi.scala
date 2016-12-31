package com.game.ui

import com.game.Utils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.Gdx
import com.game.ui.component.Button
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.Input.Keys
import com.game.Game
import com.game.AssetHandler._
import com.game.Camera
import com.game.ui.component.TextView

class GameUi extends Ui {
  
  val scoreView = new TextView(Camera.renderWidth / 2, Camera.renderHeight * 19 / 20, 0, 0, "0")
  
  addComponent(scoreView)
  
  override def keyDown(key: Int): Boolean = {
    super.keyDown(key)
    if (key == Keys.A || key == Keys.LEFT) {
      Game.gameState.getGame.buttonChanged(true, true)
    } else if (key == Keys.D || key == Keys.RIGHT) {
      Game.gameState.getGame.buttonChanged(false, true)
    } else if (key == Keys.ESCAPE || key == Keys.BACK) {
      Game.gameState.pause()
    }
    false
  }
  
  override def keyUp(key: Int): Boolean = {
    super.keyUp(key)
    if (key == Keys.A || key == Keys.LEFT) {
      Game.gameState.getGame.buttonChanged(true, false)
    } else if (key == Keys.D || key == Keys.RIGHT) {
      Game.gameState.getGame.buttonChanged(false, false)
    }
    false
  }
    
}