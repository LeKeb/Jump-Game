package com.game.state

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.Gdx
import com.game.ui.GameUi
import com.game.GameWorld
import com.game.Game
import com.game.AssetHandler._
import com.game.ui.GameOverUi
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.game.Camera
import com.game.ui.GameOverUi
import com.game.PreferenceHandler.Preferences
import com.game.AssetHandler

/**
 * The state which is the game itself
 */
class GameState extends State {
  
  //some ui:s to be used in this state
  private val ui = new GameUi(this)
  private val gameOverUi = new GameOverUi(this)
  
  private var game = new GameWorld(false) //the GameWorld
  
  private val background = new AtlasRegion(getTexture(Texture.BACKGROUND2), 0, 0, 1, 1)
  private val board = AssetHandler.getTexture(Texture.CHALK_BOARD)
  private var isPaused = false
  private val pauseState = new PauseState //from when all menus still were different states
  
  private var gameOverRegistered = false
  private var gameOverAlpha = 0f
  
  private var playTime = 0f
  
  private var hardcore = false
  
  def getGame = game
  
  /**
   * Enters the state and initialises the values for the game
   */
  override def enter() = {
    Gdx.input.setInputProcessor(ui)
    game = new GameWorld(hardcore)
    Game.soundSystem.loopMusic(getMusic(Music.GAME))
    gameOverRegistered = false
    gameOverAlpha = 0f
    playTime = 0f
  }
  
  /**
   * Exit gamestate
   */
  override def exit() = {
    isPaused = false
  }
  
  /**
   * updates the game
   */
  override def update(delta: Float) = {
    if (!isPaused && !game.isGameOver) {
      
      val rot = Gdx.input.getAccelerometerX //if device has an accelerometer it can be used for controlling the player
      if (rot != 0) {
        if (math.abs(rot) > 0.1) {
          game.getPlayer.addXVelo(-rot * 3 / 5)
        } 
      }
     
      game.update(delta) //update the game world and all the objects inside
      ui.scoreView.setText((game.getPlayer.getAllTimeHighestYCoord / 10).toInt.toString()) //set the current score visible for the player
      playTime += delta / 60
    }
    if (game.isGameOver && !gameOverRegistered) {
      //first update since game over, do everything that needs to be done when player looses, save scores etc.
      if (hardcore) {
        Preferences.hardcoreHighscore = game.getScore.max(Preferences.hardcoreHighscore)
        Preferences.hardcorePlayed = true 
      }
      else
        Preferences.highscore = game.getScore.max(Preferences.highscore)
      Preferences.timePlayed += playTime.toInt
      Preferences.timesPlayed += 1
      
      gameOverRegistered = true
      Gdx.input.setInputProcessor(gameOverUi)
      gameOverUi.scoreView.setText(game.getScore.toString())
      if (hardcore)
        gameOverUi.hiScoreView.setText(Preferences.hardcoreHighscore.toString() + " (hardcore)")
      else
        gameOverUi.hiScoreView.setText(Preferences.highscore.toString())
    } else if (game.isGameOver) {
      gameOverAlpha += (delta / 60)
    }
  }
  
  /**
   * Draws the current ui used by the gamestate
   */
  override def drawUi(batch: SpriteBatch) = {
    if (game.isGameOver) {
      val color = batch.getColor.cpy()
      batch.setColor(0, 0, 0, 0.5f * math.min(gameOverAlpha, 1))
      batch.draw(background, 0, 0, Camera.renderWidth, Camera.renderHeight)
      batch.setColor(color.r, color.g, color.b, color.a * math.min(gameOverAlpha, 1))
      batch.draw(board, Camera.renderWidth / 20, Camera.renderHeight / 10, Camera.renderWidth * 9 / 10, Camera.renderHeight * 4 / 5)
      gameOverUi.draw(batch)
      batch.setColor(color)
    } else {
      ui.draw(batch)
      if (isPaused) {
        pauseState.drawUi(batch)
      }
    }
  }
  
  /**
   * Draw the gameworld
   */
  override def drawGame(batch: SpriteBatch) = {
    game.draw(batch)
  }
  
  /**
   * Pauses the game
   */
  def pause() = {
    isPaused = true
    pauseState.enter()
    game.buttonChanged(true, false)
    game.buttonChanged(false, false)
  }
  
  /**
   * Resumes the game
   */
  def resume() = {
    isPaused = false
    pauseState.exit()
  }
  
  /**
   * Should next game start in normal or hardcore mode
   */
  def setHardcore(hard: Boolean) = {
    this.hardcore = hard
  }
  
}