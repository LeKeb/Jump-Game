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

class GameUi extends Ui {
  
  override def keyDown(key: Int): Boolean = {
    super.keyDown(key)
    if (key == Keys.A || key == Keys.LEFT) {
      Game.gameState.getGame.buttonChanged(true, true)
    } else if (key == Keys.D || key == Keys.RIGHT) {
      Game.gameState.getGame.buttonChanged(false, true)
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