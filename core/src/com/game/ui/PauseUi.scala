package com.game.ui

import com.game.ui.component.Button
import com.game.Camera
import com.game.Game
import com.game.AssetHandler._
import com.badlogic.gdx.Input.Keys
import com.game.ui.component.TextView

class PauseUi extends Ui {
  
  addComponent(
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 7 / 8, 0, 0, "Paused")    
  )
  addComponent(
      new Button(Camera.renderWidth / 2, Camera.renderHeight * 2 / 3, 375, 188, getTexture(Texture.RESUME_BUTTON),
        () => (Game.gameState.resume())
      )      
  )
  addComponent(
      new Button(Camera.renderWidth / 2, Camera.renderHeight / 3, 375, 188, getTexture(Texture.EXIT_BUTTON),
        () => (Game.game.enterState(Game.mainMenuState))
      )      
  )
  
  override def keyDown(key: Int): Boolean = {
    super.keyDown(key)
    if (key == Keys.ESCAPE || key == Keys.BACK) {
      Game.gameState.resume()
    }
    false
  }
  
}