package com.game.ui

import com.game.Camera
import com.game.Game
import com.game.ui.component.Slider
import com.game.AssetHandler._
import com.game.ui.component.Button
import com.badlogic.gdx.Input.Keys
import com.game.ui.component.TextView

class OptionUi extends Ui {
  
  addComponent(
      new Button(Camera.renderWidth / 2, Camera.renderHeight / 3, 375, 188, getTexture(Texture.EXIT_BUTTON),
        () => (Game.game.enterState(Game.mainMenuState))
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
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 9 / 10, 0, 0, "Music level:")    
  )
  
  override def keyDown(key: Int): Boolean = {
    super.keyDown(key)
    if (key == Keys.ESCAPE || key == Keys.BACK) {
      Game.game.enterState(Game.mainMenuState)
    }
    false
  }
  
}