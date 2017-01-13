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

class GameState extends State {
  
  
  private val ui = new GameUi(this)
  private val gameOverUi = new GameOverUi(this)
  private var game = new GameWorld(false)
  
  private val background = new AtlasRegion(getTexture(Texture.BACKGROUND2), 0, 0, 1, 1)
  private val board = AssetHandler.getTexture(Texture.CHALK_BOARD)
  private var isPaused = false
  private val pauseState = new PauseState
  
  private var gameOverRegistered = false
  private var gameOverAlpha = 0f
  
  private var playTime = 0f
  
  private var hardcore = false
  
  def getGame = game
  
  override def enter() = {
    Gdx.input.setInputProcessor(ui)
    game = new GameWorld(hardcore)
    Game.soundSystem.loopMusic(getMusic(Music.GAME))
    gameOverRegistered = false
    gameOverAlpha = 0f
    playTime = 0f
  }
  
  override def exit() = {
    isPaused = false
  }
  
  override def update(delta: Float) = {
    if (!isPaused && !game.isGameOver) {
      
      val rot = Gdx.input.getAccelerometerX
      if (rot != 0) {
        if (math.abs(rot) > 0.1) {
          game.getPlayer.addXVelo(-rot * 3 / 5)
        } 
      }
     
      game.update(delta)
      ui.scoreView.setText((game.getPlayer.getAllTimeHighestYCoord / 10).toInt.toString())
      playTime += delta / 60
    }
    if (game.isGameOver && !gameOverRegistered) {
      
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
  
  override def drawGame(batch: SpriteBatch) = {
    game.draw(batch)
  }
  
  def pause() = {
    isPaused = true
    pauseState.enter()
    game.buttonChanged(true, false)
    game.buttonChanged(false, false)
  }
  
  def resume() = {
    isPaused = false
    pauseState.exit()
  }
  
  def setHardcore(hard: Boolean) = {
    this.hardcore = hard
  }
  
}