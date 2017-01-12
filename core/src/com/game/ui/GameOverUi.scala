package com.game.ui

import com.game.state.State
import com.game.ui.component.TextView
import com.game.Camera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.game.ui.component.Button
import com.game.AssetHandler._
import com.game.Game

class GameOverUi(state: State) extends Ui(state) {
  
  val scoreView = new TextView(Camera.renderWidth / 2, Camera.renderHeight * 12 / 20, 0, 0, "0", getFont(Font.CHALK72))
  
  val hiScoreView = new TextView(Camera.renderWidth / 2, Camera.renderHeight * 8 / 20, 0, 0, "0", getFont(Font.CHALK72))
  
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
  
}