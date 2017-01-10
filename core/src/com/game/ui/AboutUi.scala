package com.game.ui

import com.game.state.State
import com.game.ui.component.Component
import com.game.ui.component.Component
import com.game.ui.component.TextView
import com.game.Camera
import com.game.ui.component.Button
import com.game.AssetHandler._
import com.game.state.MainMenuState

class AboutUi(state: State) extends Ui(state) {
  
  private val ui0 = Vector[Component](
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 22 / 28, 0, 0, "How to play:", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 18 / 28, 0, 0, "Jump on the different", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 16 / 28, 0, 0, "kinds of boards as", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 14 / 28, 0, 0, "high as you can.", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 10 / 28, 0, 0, "Watch out for fires", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 8 / 28, 0, 0, "and falling coconuts!", getFont(Font.CHALK)),
      
     
      new Button(Camera.renderWidth / 2, Camera.renderHeight / 10, 400, 133.3f, getTexture(Texture.COCONUT), false,
        () => (
            changeUi(1)
            )
      )    
           
  )
  
  private val ui1 = Vector[Component](
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 22 / 28, 0, 0, "A game made by:", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 18 / 28, 0, 0, "Wilhelm Bergmann", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 16 / 28, 0, 0, "Petteri Silvanto", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 14 / 28, 0, 0, "Anna-Maija Rauramaa", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 10 / 28, 0, 0, "Ohjelmointistudio 1", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 8 / 28, 0, 0, "Project", getFont(Font.CHALK)),
      
      new Button(Camera.renderWidth / 2, Camera.renderHeight / 10, 400, 133.3f, getTexture(Texture.CONFUSER), false,
        () => {
            changeUi(2)
          }
      ) 
  )
  
  private val ui2 = Vector[Component](
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 22 / 28, 0, 0, "Images:", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 18 / 28, 0, 0, "Wilhelm Bergmann", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 16 / 28, 0, 0, "Petteri Silvanto", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 14 / 28, 0, 0, "Anna-Maija Rauramaa", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 10 / 28, 0, 0, "Sounds:", getFont(Font.CHALK)),
      
      new TextView(Camera.renderWidth / 2, Camera.renderHeight * 8 / 28, 0, 0, "Freesound.org", getFont(Font.CHALK)),
      
      new Button(Camera.renderWidth / 2, Camera.renderHeight / 10, 400, 133.3f, getTexture(Texture.PALM_TREE_TRUNK), false,
        () => {
            changeUi(0)
            parentState.asInstanceOf[MainMenuState].exitAbout()
          }
      ) 
  )
  
  private val uis = Vector(ui0, ui1, ui2)
  
  private var uiNumber = 0
  
  changeUi(0)
  
  private def changeUi(number: Int): Unit = {
    uis(uiNumber).foreach(removeComponent(_))
    uiNumber = number
    uis(uiNumber).foreach(addComponent(_))
  }
  
}