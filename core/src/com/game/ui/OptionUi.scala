package com.game.ui

import com.game.Camera
import com.game.Game
import com.game.ui.component.Slider
import com.game.AssetHandler._
import com.game.ui.component.Button
import com.badlogic.gdx.Input.Keys

class OptionUi extends Ui {
  
  addComponent(
      new Button(Camera.renderWidth / 2, Camera.renderHeight / 3, 375, 188, getTexture(Texture.EXIT_BUTTON),
        () => (Game.game.enterState(Game.mainMenuState))
      )      
  )
  addComponent(
      new Slider(Camera.renderWidth / 2, Camera.renderHeight * 2 / 3, 500, 60, 0, 100, 25, 
        (i: Int) => (Game.soundSystem.setMasterVolume(i))
      )
  )
  
  override def keyDown(key: Int): Boolean = {
    super.keyDown(key)
    if (key == Keys.ESCAPE) {
      Game.game.enterState(Game.mainMenuState)
    }
    false
  }
  
}