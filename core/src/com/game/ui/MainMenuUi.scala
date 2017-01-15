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
import com.badlogic.gdx.Input.Keys

class MainMenuUi(state: State) extends Ui(state) {
  
  
  addComponent(
      new Button(Camera.renderWidth * 12 / 19, Camera.renderHeight * 33 / 42, 350, 100f, AssetHandler.getTexture(Texture.PLAY_BUTTON), false,
        () => {
          Game.gameState.setHardcore(false)
          Game.game.enterState(Game.gameState)
        }
      )      
  )
  addComponent(
      new Button(Camera.renderWidth * 12 / 19, Camera.renderHeight * 28 / 42, 350, 100f, AssetHandler.getTexture(Texture.OPTIONS_BUTTON), false,
        () => (parentState.asInstanceOf[MainMenuState].enterOptions())    
      )      
  )
  addComponent(
      new Button(Camera.renderWidth * 7 / 19, Camera.renderHeight * 21 / 80, 450, 133f, AssetHandler.getTexture(Texture.EXIT_BUTTON), false,
        () => (Gdx.app.exit())    
      )      
  )
  addComponent(
      new Button(Camera.renderWidth * 12 / 19, Camera.renderHeight * 18 / 42, 350, 100f, AssetHandler.getTexture(Texture.ABOUT_BUTTON), false,
        () => (parentState.asInstanceOf[MainMenuState].enterAbout())    
      )      
  )
  addComponent(
      new Button(Camera.renderWidth * 12 / 19, Camera.renderHeight * 23 / 42, 350, 100f, AssetHandler.getTexture(Texture.STATS_BUTTON), false,
        () => (parentState.asInstanceOf[MainMenuState].enterStats())    
      )      
  )
  addComponent(
      new Button(418, 1192, 124, 84, AssetHandler.getTexture(Texture.M), false,
        () => {
          Game.gameState.setHardcore(true)
          Game.game.enterState(Game.gameState)
        }    
      )      
  )
  override def keyDown(key: Int) = {
    if (key == Keys.BACK || key == Keys.ESCAPE) {
      Gdx.app.exit()
    }
    false
  }
  
}