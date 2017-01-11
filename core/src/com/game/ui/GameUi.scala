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
import com.game.state.State
import com.game.state.GameState

class GameUi(state: State) extends Ui(state) {
  
  val scoreView = new TextView(Camera.renderWidth / 2, Camera.renderHeight * 19 / 20, 0, 0, "0", getFont(Font.DIGIT))
  
  addComponent(scoreView)
  
  addComponent(new Button(
      Camera.renderWidth / 6, Camera.renderHeight * 23 / 25, Camera.renderWidth / 8, Camera.renderWidth / 8, getTexture(Texture.PAUSE_BUTTON), false,
      () => (parentState.asInstanceOf[GameState].pause())
    )
  
  )
  
  override def keyDown(key: Int): Boolean = {
    super.keyDown(key)
    if (key == Keys.A || key == Keys.LEFT) {
      Game.gameState.getGame.buttonChanged(true, true)
    } else if (key == Keys.D || key == Keys.RIGHT) {
      Game.gameState.getGame.buttonChanged(false, true)
    } else if (key == Keys.ESCAPE || key == Keys.BACK) {
      parentState.asInstanceOf[GameState].pause()
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