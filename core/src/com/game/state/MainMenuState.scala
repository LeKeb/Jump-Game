package com.game.state

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.game.ui.MainMenuUi
import com.badlogic.gdx.Gdx
import com.game.Game
import com.game.AssetHandler
import com.game.ui.OptionUi
import com.game.AssetHandler.Texture
import com.game.Camera
import com.game.ui.Ui
import com.game.ui.AboutUi
import com.game.ui.StatsUi
import com.game.PreferenceHandler._

/**
 * The main menu of the game
 */
class MainMenuState extends State {
  
  private val ui = new MainMenuUi(this)
  
  //all the different uis used by this state
  private val optionUi = new OptionUi(this)
  private val aboutUi = new AboutUi(this)
  private val statsUi = new StatsUi(this)
  
  private val startView = AssetHandler.getTexture(Texture.START_VIEW)
  private val menuBack = AssetHandler.getTexture(Texture.CHALK_BOARD)
  
  //to keep track of which ui needs to be drawn
  private var isInOptions = false
  private var isInAbout = false
  private var isInStats = false
  
  /**
   * enters the main menu state
   */
  override def enter() = {
    Gdx.input.setInputProcessor(ui)
    Game.soundSystem.loopMusic(AssetHandler.getMusic(AssetHandler.Music.MENU))
  }
  
  override def exit() = {
    
  }
  
  override def update(delta: Float) = {
    
  }
  
  /**
   * Draws the current ui
   */
  override def drawUi(batch: SpriteBatch) = {
   
    batch.draw(startView, 0, 0, Camera.renderWidth, Camera.renderHeight)
    if (isInOptions) {
      optionUi.draw(batch)
    } else if (isInAbout) {
      batch.draw(menuBack, Camera.renderWidth / 20, Camera.renderHeight / 20, Camera.renderWidth * 9 / 10, Camera.renderHeight * 4 / 5)
      aboutUi.draw(batch)
    } else if (isInStats) {
      batch.draw(menuBack, Camera.renderWidth / 20, Camera.renderHeight / 20, Camera.renderWidth * 9 / 10, Camera.renderHeight * 4 / 5)
      statsUi.draw(batch)
    }
    else
      ui.draw(batch)
  }
  
  override def drawGame(batch: SpriteBatch) = {
    
  }
  
  def enterOptions() = {
    Gdx.input.setInputProcessor(optionUi)
    isInOptions = true
  }
  
  def exitOptions() = {
    Gdx.input.setInputProcessor(ui)
    isInOptions = false
  }
  
  def enterAbout() = {
    Gdx.input.setInputProcessor(aboutUi)
    isInAbout = true
  }
  
  def exitAbout() = {
    Gdx.input.setInputProcessor(ui)
    isInAbout = false
  }
  
  def enterStats() = {
    Gdx.input.setInputProcessor(statsUi)
    isInStats = true
    if (Preferences.hardcorePlayed)
      statsUi.highscore.setText(Preferences.highscore.toString() + " (" + Preferences.hardcoreHighscore.toString() + ")")
    else
      statsUi.highscore.setText(Preferences.highscore.toString())
    statsUi.playTimes.setText(Preferences.timesPlayed.toString())
    var time = Preferences.timePlayed
    val hours = time / 3600 //calculate hours, minutes and seconds of playtime
    time -= hours * 3600
    val minutes = time / 60
    time -= minutes * 60
    statsUi.playTime.setText(hours + " h  " + minutes + " m  " + time + " s")
  }
  
  def exitStats() = {
    Gdx.input.setInputProcessor(ui)
    isInStats = false
  }
  
}