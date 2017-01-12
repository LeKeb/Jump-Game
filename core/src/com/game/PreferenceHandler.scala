package com.game

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Preferences

object PreferenceHandler {
  
  object Preferences {
    var highscore = 0
    var timePlayed = 0
    var timesPlayed = 0
    var soundVolume = 0
    var musicVolume = 0
  }
  
  private var prefs: Preferences = _
  
  def loadPreferences() = {
    prefs = Gdx.app.getPreferences("JumpGamePrefs.xml")
    Preferences.highscore = prefs.getInteger("HighScore", 0)
    Preferences.timesPlayed = prefs.getInteger("TimesPlayed", 0)
    Preferences.timePlayed = prefs.getInteger("TimePlayed", 0)
    Preferences.soundVolume = prefs.getInteger("Sound", 75).min(100).max(0)
    Preferences.musicVolume = prefs.getInteger("Music", 75).min(100).max(0)
  }
  
  def savePreferences() = {
    prefs.putInteger("HighScore", Preferences.highscore)
    prefs.putInteger("TimesPlayed", Preferences.timesPlayed)
    prefs.putInteger("TimePlayed", Preferences.timePlayed)
    prefs.putInteger("Sound", Preferences.soundVolume)
    prefs.putInteger("Music", Preferences.musicVolume)
    prefs.flush()
  }
  
}