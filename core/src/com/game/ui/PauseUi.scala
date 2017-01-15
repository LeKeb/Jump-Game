package com.game.ui

import com.game.ui.component.Button
import com.game.Camera
import com.game.Game
import com.game.AssetHandler._
import com.badlogic.gdx.Input.Keys
import com.game.ui.component.TextView
import com.game.state.State
import com.game.state.PauseState

class PauseUi(state: State) extends Ui(state) {
  
  addComponent(
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 7 / 8, 0, 0, "Paused", null)    
  )
  addComponent(
      new Button(Camera.renderWidth / 2, Camera.renderHeight * 5 / 8, 400, 133.3f, getTexture(Texture.RESUME_BUTTON), false,
        () => (Game.gameState.resume())
      )      
  )
  addComponent(
      new Button(Camera.renderWidth / 2, Camera.renderHeight * 3 / 8, 400, 133.3f, getTexture(Texture.OPTIONS_BUTTON), false,
        () => (parentState.asInstanceOf[PauseState].enterOptions())
      )      
  )
  addComponent(
      new Button(Camera.renderWidth / 2, Camera.renderHeight / 8, 400, 130.3f, getTexture(Texture.EXIT_BUTTON),false ,
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