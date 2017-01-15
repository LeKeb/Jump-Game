package com.game.ui

import com.game.state.State
import com.badlogic.gdx.Input.Keys
import com.game.state.MainMenuState
import com.game.ui.component.TextView
import com.game.Camera
import com.game.AssetHandler._
import com.game.ui.component.Button

class StatsUi(state: State) extends Ui(state) {
  
  val highscore = new TextView(Camera.renderWidth / 2, Camera.renderHeight * 15 / 28, 0, 0, "0", getFont(Font.CHALK))
  val playTimes = new TextView(Camera.renderWidth / 2, Camera.renderHeight * 11 / 28, 0, 0, "0", getFont(Font.CHALK))
  val playTime = new TextView(Camera.renderWidth / 2, Camera.renderHeight * 7 / 28, 0, 0, "0", getFont(Font.CHALK))
  
  addComponent(
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 21 / 28, 0, 0, "Your stats:", getFont(Font.CHALK72))
  )
  
  addComponent(
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 17 / 28, 0, 0, "Highscore:", getFont(Font.CHALK))
  )
  
  addComponent(
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 13 / 28, 0, 0, "Times played:", getFont(Font.CHALK))
  )
  
  addComponent(
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 9 / 28, 0, 0, "Total playtime:", getFont(Font.CHALK))
  )
  
  addComponent(highscore)
  addComponent(playTime)
  addComponent(playTimes)
  
  addComponent(
    new Button(Camera.renderWidth / 2, Camera.renderHeight * 4 / 28, 200, 90, getTexture(Texture.MENUBUT), false,
        () => {
          parentState.asInstanceOf[MainMenuState].exitStats()
        }
      )    
  )
  
  override def keyDown(key: Int) = {
    if (key == Keys.BACK || key == Keys.ESCAPE) {
      parentState.asInstanceOf[MainMenuState].exitStats()
    }
    false
  }
  
}