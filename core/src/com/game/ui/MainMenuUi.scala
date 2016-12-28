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

class MainMenuUi extends Ui {
  
  addComponent(
      new Button(Camera.renderWidth / 2, Camera.renderHeight * 3 / 4, 375, 188, AssetHandler.getTexture(Texture.PLAY_BUTTON),
        () => (Game.game.enterState(Game.gameState))    
      )      
  )
  addComponent(
      new Slider(Camera.renderWidth / 2, Camera.renderHeight / 4, 500, 60, 0, 100, 25, 
        (i: Int) => (Game.soundSystem.setMasterVolume(i))
      )
  )
   
}