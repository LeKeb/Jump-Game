package com.game

import scala.collection.mutable.HashMap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.audio.Music

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
    val COCONUT = "coconut"
    val EXIT_BUTTON = "exit"
    val OPTIONS_BUTTON = "options"
    val RESUME_BUTTON = "resume"
    val PALM_TREE_TRUNK = "trunk"
    val PALM_TREE_TOP = "top"
    val BACKGROUND = "back"
    val PARALLAX_BACKGROUND = "para"
    val PARALLAX_BACKGROUND2 = "para2"
    val WARNING = "warn"
    val CONFUSER = "confuse"
    val FIRE = "fire"
    val FIRE_HITBOX = "fireHit"
    val CIRCLE = "circ"
    val START_VIEW = "startView"
    val BACKGROUND2 = "back2"
    val CHALK_BOARD = "chalkb"
    val ABOUT_BUTTON = "about"
  }
  
  object Sound {
    val CRACK = "crack"
    val JUMP = "jump"
    val BOOST = "boost"
    val GAME_OVER = "gameOver"
    val STUNNED = "stun"
    val CONFUSE = "conf"
    val SCREAM = "scream"
  }
  
  object Music {
    val MENU = "menu"
    val GAME = "game"
  }
  
  object Font {
    val DEFAULT = "def"
    val CHALK = "chalk"
  }
  
  private val textures: HashMap[String, Texture] = new HashMap[String, Texture]()
  private val sounds: HashMap[String, Sound] = new HashMap[String, Sound]()
  private val music: HashMap[String, Music] = new HashMap[String, Music]()
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
  
  private def loadMusic(file: String) = {
    Gdx.audio.newMusic(Gdx.files.internal("Music/" + file))
  }
  
  def loadAssets() = {
    textures += Texture.PLAYER -> loadTexture("player.png")
    textures += Texture.NORMAL_PLATFORM -> loadTexture("Platf_normal_skateb.png")
    textures += Texture.BOOST_PLATFORM -> loadTexture("Platf_boost.png")
    textures += Texture.BREAKABLE_PLATFORM -> loadTexture("Platf_breakable.png")
    textures += Texture.BREAKABLE_PLATFORM_BROKEN -> loadTexture("Platf_broken.png")
    textures += Texture.PLAY_BUTTON -> loadTexture("Button_play.png")
    textures += Texture.SLIDER_BACKGORUND -> loadTexture("Slider.png")
    textures += Texture.SLIDER_BUTTON -> loadTexture("Slider_button.png")
    textures += Texture.COCONUT -> loadTexture("Coco.png")
    textures += Texture.EXIT_BUTTON -> loadTexture("Button_exit_busstop.png")
    textures += Texture.OPTIONS_BUTTON -> loadTexture("Button_settings.png")
    textures += Texture.RESUME_BUTTON -> loadTexture("Button_resume.png")
    textures += Texture.PALM_TREE_TOP -> loadTexture("palmTreeTop.png")
    textures += Texture.PALM_TREE_TRUNK -> loadTexture("palmTreeTrunk.png")
    textures += Texture.BACKGROUND -> loadTexture("Background.png")
    textures += Texture.PARALLAX_BACKGROUND -> loadTexture("parallaxBackground.png")
    textures += Texture.PARALLAX_BACKGROUND2 -> loadTexture("parallaxBackground2.png")
    textures += Texture.WARNING -> loadTexture("Warning.png")
    textures += Texture.CONFUSER -> loadTexture("hypnosis.png")
    textures += Texture.FIRE -> loadTexture("fireAnimation.png")
    textures += Texture.FIRE_HITBOX -> loadTexture("FireHitbox.png")
    textures += Texture.CIRCLE -> loadTexture("Circle.png")
    textures += Texture.START_VIEW -> loadTexture("startView.png")
    textures += Texture.BACKGROUND2 -> loadTexture("background2.png")
    textures += Texture.CHALK_BOARD -> loadTexture("Board.png")
    textures += Texture.ABOUT_BUTTON -> loadTexture("Button_help.png")
    
    sounds += Sound.CRACK -> loadSound("cracking.ogg")
    sounds += Sound.JUMP -> loadSound("jump.ogg")
    sounds += Sound.BOOST -> loadSound("boost.ogg")
    sounds += Sound.GAME_OVER -> loadSound("gameOver.ogg")
    sounds += Sound.STUNNED -> loadSound("stunned.ogg")
    sounds += Sound.CONFUSE -> loadSound("portal.ogg")
    sounds += Sound.SCREAM -> loadSound("scream.ogg")
    
    music += Music.GAME -> loadMusic("music2.ogg")
    music += Music.MENU -> loadMusic("music.ogg")
    
    fonts += Font.DEFAULT -> loadFont("default.fnt")
    fonts += Font.CHALK -> loadFont("font.fnt")
  }
  
  def getTexture(texture: String) = {
    textures.get(texture).get
  }
  
  def getSound(sound: String) = {
    sounds.get(sound).get
  }
  
  def getMusic(music: String) = {
    this.music.get(music).get
  }
  
  def getFont(font: String) = {
    fonts.get(font).get
  }
  
}
