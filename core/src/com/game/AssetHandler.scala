package com.game

import scala.collection.mutable.HashMap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont

object AssetHandler {
  
  object Texture {
    val PLAYER = "player"
    val NORMAL_PLATFORM = "normPlat"
    val BOOST_PLATFORM = "boostPlat"
    val BREAKABLE_PLATFORM = "breakPlat"
    val BREAKABLE_PLATFORM_BROKEN = "breakPlatBroken"
    val PLAY_BUTTON = "playButton"
    val SLIDER_BACKGORUND = "sliderBack"
    val SLIDER_BUTTON = "sliderButt"
    val BLACK_OUT = "black"
    val COCONUT = "coconut"
    val EXIT_BUTTON = "exit"
    val OPTIONS_BUTTON = "options"
    val RESUME_BUTTON = "resume"
  }
  
  object Sound {
    val CRACK = "crack"
    val JUMP = "jump"
    val BOOST = "boost"
    val GAME_OVER = "gameOver"
    val STUNNED = "stun"
  }
  
  object Font {
    val DEFAULT = "def"
  }
  
  private val textures: HashMap[String, Texture] = new HashMap[String, Texture]()
  private val sounds: HashMap[String, Sound] = new HashMap[String, Sound]()
  private val fonts: HashMap[String, BitmapFont] = new HashMap[String, BitmapFont]()
  
  private def loadTexture(file: String) = {
    new Texture(Gdx.files.internal("Textures/" + file))
  }
  
  private def loadSound(file: String) = {
    Gdx.audio.newSound(Gdx.files.internal("Sounds/" + file))
  }
  
  private def loadFont(file: String) = {
    new BitmapFont(Gdx.files.internal("Fonts/" + file))
  }
  
  def loadAssets() = {
    textures += Texture.PLAYER -> loadTexture("player.png")
    textures += Texture.NORMAL_PLATFORM -> loadTexture("Platform1.png")
    textures += Texture.BOOST_PLATFORM -> loadTexture("Platform3.png")
    textures += Texture.BREAKABLE_PLATFORM -> loadTexture("Platform2.png")
    textures += Texture.BREAKABLE_PLATFORM_BROKEN -> loadTexture("Platform2 broken.png")
    textures += Texture.PLAY_BUTTON -> loadTexture("PlayButton.png")
    textures += Texture.SLIDER_BACKGORUND -> loadTexture("SliderBack.png")
    textures += Texture.SLIDER_BUTTON -> loadTexture("SliderButton.png")
    textures += Texture.BLACK_OUT -> loadTexture("BlackOut.png")
    textures += Texture.COCONUT -> loadTexture("Coconut2.png")
    textures += Texture.EXIT_BUTTON -> loadTexture("ExitButton.png")
    textures += Texture.OPTIONS_BUTTON -> loadTexture("OptionsButton.png")
    textures += Texture.RESUME_BUTTON -> loadTexture("ResumeButton.png")
    
    sounds += Sound.CRACK -> loadSound("cracking.wav")
    sounds += Sound.JUMP -> loadSound("jump.wav")
    sounds += Sound.BOOST -> loadSound("boost.wav")
    sounds += Sound.GAME_OVER -> loadSound("gameOver.wav")
    sounds += Sound.STUNNED -> loadSound("stunned.wav")
    
    fonts += Font.DEFAULT -> loadFont("default.fnt")
  }
  
  def getTexture(texture: String) = {
    textures.get(texture).get
  }
  
  def getSound(sound: String) = {
    sounds.get(sound).get
  }
  
  def getFont(font: String) = {
    fonts.get(font).get
  }
  
}
