package com.game

import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music

class SoundSystem {
  
  private var soundVolume = 100f
  private var musicVolume = 100f
  
  private var musicPlaying: Music = _
  
  def loopMusic(music: Music) = {
    if (musicPlaying != null)
      musicPlaying.stop()
    music.setVolume(musicVolume / 100f)
    music.setLooping(true)
    music.play()
    musicPlaying = music
  }
  
  def stopMusic() = {
    if (musicPlaying != null) {
      musicPlaying.stop()
    }
  }
  
  def playSound(sound: Sound) = {
    sound.play(soundVolume / 100f)
  }
  
  def setMusicVolume(volume: Int) = {
    musicVolume = volume
    if (musicPlaying != null)
      musicPlaying.setVolume(musicVolume / 100f)
  }
  
  def setSoundVolume(volume: Int) = {
    soundVolume = volume
  }
  
}