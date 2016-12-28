package com.game

import com.badlogic.gdx.audio.Sound

class SoundSystem {
  
  private var masterVolume = 100f
  
  def playSound(sound: Sound) = {
    sound.play(masterVolume / 100f)
  }
  
  def setMasterVolume(volume: Int) = {
    masterVolume = volume
  }
  
}