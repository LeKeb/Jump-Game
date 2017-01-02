package com.game.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.game.Game

object Main {
  
  def main(args: Array[String]) {
    val config = new LwjglApplicationConfiguration
    config.width = 360;
    config.height = 640;
    config.title = "The game";
    new LwjglApplication(new Game(), config);
  }
  
}