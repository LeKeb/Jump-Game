package com.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Color
import com.game.state._
import com.badlogic.gdx.graphics.glutils.FrameBuffer
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.glutils.ShaderProgram
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.profiling.GL20Profiler
import com.badlogic.gdx.graphics.GL30
import com.game.PreferenceHandler._
import com.game.AssetHandler._

object Game {
  
  var game: Game = _
  var soundSystem: SoundSystem = _
  val gameState = new GameState
  val mainMenuState = new MainMenuState
  
}

class Game extends ApplicationAdapter {

  private var batch: SpriteBatch = _
  private var camera: Camera = _
  private var currentState: State = _
  
  private var defaultShader: ShaderProgram = _
  private var blurShader: ShaderProgram = _
  
  private var fbo1: FrameBuffer = _
  private var fbo2: FrameBuffer = _
  
  private var circleTexture: Texture = _
  
  override def create() = {
    batch = new SpriteBatch
    AssetHandler.loadAssets()
    PreferenceHandler.loadPreferences()
    
    camera = new Camera
    Game.game = this
    Game.soundSystem = new SoundSystem
    Game.soundSystem.setMusicVolume(Preferences.musicVolume)
    Game.soundSystem.setSoundVolume(Preferences.soundVolume)
    enterState(Game.mainMenuState)
    
    val vert = Gdx.files.internal("Shaders/blurShader.vert").readString()
    val frag = Gdx.files.internal("Shaders/blurShader.frag").readString()
    
    defaultShader = SpriteBatch.createDefaultShader()
    blurShader = new ShaderProgram(vert, frag)
    
    if (blurShader.getLog.length() != 0)
      println(blurShader.getLog)
    if(!blurShader.isCompiled())
      System.err.println("Shader not comiled!")

    blurShader.begin()
    blurShader.setUniformf("dir", 0f, 0f)
    blurShader.setUniformf("resolution", 1280)
    blurShader.setUniformf("radius", 2f)
    blurShader.end()
    
    fbo1 = new FrameBuffer(Pixmap.Format.RGBA8888, 720, 1280, false)
    fbo2 = new FrameBuffer(Pixmap.Format.RGBA8888, 720, 1280, false)
    
    Gdx.gl.glEnable(GL20.GL_BLEND)
    
    circleTexture = AssetHandler.getTexture(AssetHandler.Texture.CIRCLE)
  }

  override def render() = {
    
    val delta = Gdx.graphics.getDeltaTime * 60
    
    currentState.update(delta)
    
    camera.setPosition(Camera.renderWidth / 2, Game.gameState.getGame.getPlayer.getAllTimeHighestYCoord - 120)
    camera.update()
    
    if (Game.gameState.getGame.getPlayer.getBlackoutLevel > 0 && currentState == Game.gameState) {
      
      fbo1.begin()
    
      Gdx.gl.glClearColor(0, 0.7.toFloat, 1, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      
      batch.setProjectionMatrix(camera.getCamera.combined)
    
      batch.setShader(defaultShader)
    
      batch.begin();
    
      currentState.drawGame(batch)
      batch.flush()
      fbo1.end()
    
      batch.setShader(blurShader)
      blurShader.setUniformf("dir", 1f, 0f);
      blurShader.setUniformf("radius", Math.min(5f * Game.gameState.getGame.getPlayer.getBlackoutLevel, 50))
    
      camera.setPosition(Camera.renderWidth / 2 , Camera.renderHeight / 2)
      camera.update()
      batch.setProjectionMatrix(camera.getCamera.combined)
    
      fbo2.begin()
      batch.draw(fbo1.getColorBufferTexture, 0, 0)
      batch.flush()
      fbo2.end()
      
      batch.setShader(defaultShader)
      
      camera.setPosition(Camera.renderWidth / 2, Game.gameState.getGame.getPlayer.getAllTimeHighestYCoord - 120)
      camera.update()
      batch.setProjectionMatrix(camera.getCamera.combined)
      
      
      fbo1.begin()
      Gdx.gl.glColorMask(false, false, false, true)
      Gdx.gl.glClearColor(0, 0, 0, 0)
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      batch.draw(circleTexture, Game.gameState.getGame.getPlayer.getPos.x - circleTexture.getWidth / 2, Game.gameState.getGame.getPlayer.getPos.y - circleTexture.getHeight / 2)
      batch.flush()
      Gdx.gl.glColorMask(true, true, true, true)
      fbo1.end()
      
      camera.setPosition(Camera.renderWidth / 2 , Camera.renderHeight / 2)
      camera.update()
      batch.setProjectionMatrix(camera.getCamera.combined)
      
      Gdx.gl.glClearColor(0, 0, 0, 1)
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      
      batch.setShader(blurShader)
      blurShader.setUniformf("dir", 0f, 1f);
      batch.draw(fbo2.getColorBufferTexture, 0, 0)
      
      
      batch.setShader(defaultShader)
      batch.draw(fbo1.getColorBufferTexture, 0, fbo1.getHeight, fbo1.getWidth, -fbo1.getHeight)
      
      batch.end();
    
    } else {
      
      Gdx.gl.glClearColor(0, 0.7.toFloat, 1, 1);
      Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      
      batch.setProjectionMatrix(camera.getCamera.combined)
      
      batch.setShader(defaultShader)
      
      batch.begin();
      currentState.drawGame(batch)
      batch.end();
    }
    
    camera.setPosition(Camera.renderWidth / 2 , Camera.renderHeight / 2)
    camera.update()
    batch.setProjectionMatrix(camera.getCamera.combined)
    batch.setShader(defaultShader)
    
    batch.begin()
    currentState.drawUi(batch)
    batch.end();
  }
  
  def enterState(state: State) = {
    if (this.currentState != null)
      this.currentState.exit()
    this.currentState = state
    currentState.enter()
    Gdx.input.setCatchBackKey(true)
  }
  
  override def dispose() = {
    PreferenceHandler.savePreferences()
  }
  
  override def pause () = {
    PreferenceHandler.savePreferences()
  }
}