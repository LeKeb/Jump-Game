package com.game.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.game.Game
import com.badlogic.gdx.Files.FileType

object Main {
  
  def main(args: Array[String]) {
    val config = new LwjglApplicationConfiguration
    config.width = 360;
    config.height = 640;
    config.title = "Surf Man";
    config.addIcon("Textures/Icon32x32.png", FileType.Internal)
    new LwjglApplication(new Game(), config);
  }
  
}