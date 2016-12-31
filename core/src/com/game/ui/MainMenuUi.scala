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

class MainMenuUi extends Ui {
  
  addComponent(
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 13 / 14, 0, 0, "Doodle Jump 3000")    
  )
  addComponent(
      new Button(Camera.renderWidth / 2, Camera.renderHeight * 3 / 4, 375, 188, AssetHandler.getTexture(Texture.PLAY_BUTTON),
        () => (Game.game.enterState(Game.gameState))    
      )      
  )
  addComponent(
      new Button(Camera.renderWidth / 2, Camera.renderHeight * 2 / 4, 375, 188, AssetHandler.getTexture(Texture.OPTIONS_BUTTON),
        () => (Game.game.enterState(Game.optionState))    
      )      
  )
  addComponent(
      new Button(Camera.renderWidth / 2, Camera.renderHeight * 1 / 4, 375, 188, AssetHandler.getTexture(Texture.EXIT_BUTTON),
        () => (Gdx.app.exit())    
      )      
  )
   
}