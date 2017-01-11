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

class GameOverScreen(state: State) extends Ui(state) {

  addComponent(
    new TextView(Camera.renderWidth / 2, Camera.renderHeight * 13 / 14, 0, 0, "Your points")) //Add the points here
  
  addComponent(
    new Button(Camera.renderWidth / 2, Camera.renderHeight / 3, 400, 133.3f, getTexture(Texture.RESUME_BUTTON), false,
      () => (parentState.asInstanceOf[MainMenuState].exitOptions()) //Should return to main menu here
      )
    )
  addComponent () //Add a nice background image here  

}