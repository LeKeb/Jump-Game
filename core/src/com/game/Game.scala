package com.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Color

class Game extends ApplicationAdapter {

  private var batch: SpriteBatch = _
  private var game: GameWorld = _
  private var camera: Camera = _

  override def create() = {
    batch = new SpriteBatch
    AssetHandler.loadAssets()
    game = new GameWorld
    camera = new Camera
  }

  override def render() = {

    game.update(Gdx.graphics.getDeltaTime)

    camera.setPosition(Camera.renderWidth / 2 , Camera.renderHeight / 2 + game.getPlayerPos.y)
    camera.update()

    batch.setProjectionMatrix(camera.getCamera.combined)

    Gdx.gl.glClearColor(0, 0.7.toFloat, 1, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    batch.begin();
    game.draw(batch)
    batch.end();
  }

}