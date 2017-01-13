package com.game.ui

import com.game.state.State
import com.game.ui.component.TextView
import com.game.Camera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.game.ui.component.Button
import com.game.AssetHandler._
import com.game.Game
import com.badlogic.gdx.Input.Keys

class GameOverUi(state: State) extends Ui(state) {
  
  val scoreView = new TextView(Camera.renderWidth / 2, Camera.renderHeight * 12 / 20, 0, 0, "0", getFont(Font.CHALK))
  
  val hiScoreView = new TextView(Camera.renderWidth / 2, Camera.renderHeight * 8 / 20, 0, 0, "0", getFont(Font.CHALK))
  
  addComponent(new TextView(Camera.renderWidth / 2, Camera.renderHeight * 17 / 20, 0, 0, "Game Over!", getFont(Font.CHALK72)))
  
  addComponent(new TextView(Camera.renderWidth / 2, Camera.renderHeight * 14 / 20, 0, 0, "Score:", getFont(Font.CHALK72)))
  
  addComponent(new TextView(Camera.renderWidth / 2, Camera.renderHeight * 10 / 20, 0, 0, "Highscore:", getFont(Font.CHALK72)))
  
  addComponent(hiScoreView)
  
  addComponent(scoreView)
  
  addComponent(new Button(Camera.renderWidth / 4, Camera.renderHeight / 5, 270, 90, getTexture(Texture.PLAY_BUTTON), false,
      () => (Game.game.enterState(Game.gameState))  
    )
  )
  
  addComponent(new Button(Camera.renderWidth * 3 / 4, Camera.renderHeight / 5, 270, 90, getTexture(Texture.EXIT_BUTTON), false,
      () => (Game.game.enterState(Game.mainMenuState)) 
    )
  )
  
  override def keyDown(key: Int): Boolean = {
    super.keyDown(key)
    if (key == Keys.ESCAPE || key == Keys.BACK) {
      Game.game.enterState(Game.mainMenuState)
    }
    false
  }
  
}