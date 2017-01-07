package com.game.ui

import com.game.Camera
import com.game.Game
import com.game.ui.component.Slider
import com.game.AssetHandler._
import com.game.ui.component.Button
import com.badlogic.gdx.Input.Keys
import com.game.ui.component.TextView
import com.game.state.State
import com.game.state.MainMenuState
import com.game.state.PauseState

class OptionUi(state: State) extends Ui(state) {
  
  addComponent(
      new Button(Camera.renderWidth / 2, Camera.renderHeight / 3, 375, 188, getTexture(Texture.EXIT_BUTTON),
        () => (
            if (parentState.isInstanceOf[MainMenuState])
              parentState.asInstanceOf[MainMenuState].exitOptions()
            else
              parentState.asInstanceOf[PauseState].exitOptions()
            )
      )      
  )
  addComponent(
      new Slider(Camera.renderWidth / 2, Camera.renderHeight * 2 / 3, 500, 60, 0, 100, 25, 
        (i: Int) => (Game.soundSystem.setSoundVolume(i))
      )
  )
  addComponent(
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 3 / 4, 0, 0, "Sound level:")    
  )
  addComponent(
      new Slider(Camera.renderWidth / 2, Camera.renderHeight * 4 / 5, 500, 60, 0, 100, 25, 
        (i: Int) => (Game.soundSystem.setMusicVolume(i))
      )
  )
  addComponent(
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 53 / 60, 0, 0, "Music level:")    
  )
  
  override def keyDown(key: Int): Boolean = {
    super.keyDown(key)
    if (key == Keys.ESCAPE || key == Keys.BACK) {
      Game.game.enterState(Game.mainMenuState)
    }
    false
  }
  
}