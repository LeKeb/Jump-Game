package com.game.ui

import com.game.ui.component.Button
import com.game.Camera
import com.game.AssetHandler
import com.game.AssetHandler.Texture
import com.game.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.Gdx
import com.game.Utils
import com.game.ui.component.Slider
import com.game.ui.component.TextView
import com.game.state.State
import com.game.state.MainMenuState

class MainMenuUi(state: State) extends Ui(state) {
  
  
  addComponent(
      new Button(Camera.renderWidth / 2, Camera.renderHeight * 16 / 21, 460, 133.3f, AssetHandler.getTexture(Texture.PLAY_BUTTON), false,
        () => (Game.game.enterState(Game.gameState))    
      )      
  )
  addComponent(
      new Button(Camera.renderWidth / 2, Camera.renderHeight * 13 / 21, 460, 133.3f, AssetHandler.getTexture(Texture.OPTIONS_BUTTON), false,
        () => (parentState.asInstanceOf[MainMenuState].enterOptions())    
      )      
  )
  addComponent(
      new Button(Camera.renderWidth * 7 / 19, Camera.renderHeight * 1 / 4, 492, 133.3f, AssetHandler.getTexture(Texture.EXIT_BUTTON), false,
        () => (Gdx.app.exit())    
      )      
  )
   
}