package com.game.state

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.Gdx
import com.game.ui.GameUi
import com.game.GameWorld

class GameState extends State {
  
  
  private val ui = new GameUi
  private var game = new GameWorld
  
  private var isPaused = false
  private val pauseState = new PauseState
  
  def getGame = game
  
  override def enter() = {
    Gdx.input.setInputProcessor(ui)
    game = new GameWorld
  }
  
  override def exit() = {
    isPaused = false
  }
  
  override def update(delta: Float) = {
    if (!isPaused) {
      game.update(delta)
      ui.scoreView.setText(game.getPlayer.getAllTimeHighestYCoord.toInt.toString())
    }
  }
  
  override def drawUi(batch: SpriteBatch) = {
    ui.draw(batch)
    if (isPaused) {
      pauseState.drawUi(batch)
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
  
}