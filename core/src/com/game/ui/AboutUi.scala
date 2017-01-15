package com.game.ui

import com.game.state.State
import com.game.ui.component.Component
import com.game.ui.component.Component
import com.game.ui.component.TextView
import com.game.Camera
import com.game.ui.component.Button
import com.game.AssetHandler._
import com.game.state.MainMenuState
import com.badlogic.gdx.Input.Keys

class AboutUi(state: State) extends Ui(state) {
  
  private val ui0 = Vector[Component](
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 22 / 28, 0, 0, "How to play:", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 18 / 28, 0, 0, "Jump on the different", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 16 / 28, 0, 0, "kinds of boards as", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 14 / 28, 0, 0, "high as you can.", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 10 / 28, 0, 0, "Watch out for fires", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 8 / 28, 0, 0, "and falling coconuts!", getFont(Font.CHALK)),
      
      new Button(Camera.renderWidth * 13 / 18, Camera.renderHeight / 8, 200, 133.3f, getTexture(Texture.ARROW_RIGHT), false,
        () => {
            changeUi(1)
        }
      )
           
  )
  
  private val ui1 = Vector[Component](
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 22 / 28, 0, 0, "A game made by:", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 18 / 28, 0, 0, "-Wilhelm Bergmann", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 16 / 28, 0, 0, "-Petteri Silvanto", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 14 / 28, 0, 0, "-Anna-Maija Rauramaa", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 10 / 28, 0, 0, "Ohjelmointistudio 1", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 8 / 28, 0, 0, "Project", getFont(Font.CHALK)),
      
      new Button(Camera.renderWidth * 13 / 18, Camera.renderHeight / 8, 200, 133.3f, getTexture(Texture.ARROW_RIGHT), false,
        () => {
            changeUi(2)
        }
      ),
      
      new Button(Camera.renderWidth * 5 / 18, Camera.renderHeight / 8, 200, 133.3f, getTexture(Texture.ARROW_LEFT), false,
        () => (
            changeUi(0)
            )
      )
  )
  
  private val ui2 = Vector[Component](
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 22 / 28, 0, 0, "Images:", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 19 / 28, 0, 0, "-Wilhelm Bergmann", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 17 / 28, 0, 0, "-Petteri Silvanto", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 15 / 28, 0, 0, "-Anna-Maija Rauramaa", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 13 / 28, 0, 0, "-And other copyright", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 11 / 28, 0, 0, "free images", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 8 / 28, 0, 0, "Sounds:", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 6 / 28, 0, 0, "Freesound.org", getFont(Font.CHALK)),
      
      new Button(Camera.renderWidth * 13 / 18, Camera.renderHeight / 8, 200, 133.3f, getTexture(Texture.ARROW_RIGHT), false,
        () => {
            changeUi(3)
        }
      ),
            
      new Button(Camera.renderWidth * 5 / 18, Camera.renderHeight / 8, 200, 133.3f, getTexture(Texture.ARROW_LEFT), false,
        () => (
            changeUi(1)
            )
      )
  )
  
  private val ui3 = Vector[Component](
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 22 / 28, 0, 0, "Game made with", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 19 / 28, 0, 0, "LibGDX", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 16 / 28, 0, 0, "- a free open source", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 14 / 28, 0, 0, "- cross platform", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 12 / 28, 0, 0, "game library", getFont(Font.CHALK)),
      
      new Button(Camera.renderWidth * 5 / 18, Camera.renderHeight / 8, 200, 133.3f, getTexture(Texture.ARROW_LEFT), false,
        () => (
            changeUi(2)
            )
      )
  )
  
  private val uis = Vector(ui0, ui1, ui2, ui3)
  
  private var uiNumber = 0
  
  changeUi(0)
  
  addComponent(
    new Button(Camera.renderWidth * 5 / 18, Camera.renderHeight * 51 / 60, 200, 70, getTexture(Texture.RESUME_BUTTON), false,
        () => {
          changeUi(0)  
          parentState.asInstanceOf[MainMenuState].exitAbout()
        }
      )    
  )
  
  private def changeUi(number: Int): Unit = {
    uis(uiNumber).foreach(removeComponent(_))
    uiNumber = number
    uis(uiNumber).foreach(addComponent(_))
  }
  
  override def keyDown(key: Int) = {
    if (key == Keys.BACK || key == Keys.ESCAPE) {
      changeUi(0)  
      parentState.asInstanceOf[MainMenuState].exitAbout()
    }
    false
  }
  
}