package com.game.ui

import com.badlogic.gdx.InputProcessor
import com.game.ui.component.Component
import scala.collection.mutable.Buffer
import com.game.ui.component.Component
import com.game.Utils
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.game.state.State

/**
 * An abstract class that represents a ui
 */
abstract class Ui(state: State) extends InputProcessor {
  
  private val components = Buffer[Component]()
  
  protected val parentState = state
  
  /**
   * adds a component to the ui
   */
  protected def addComponent(comp: Component): Unit = {
    components += comp
  }
  
  /**
   * removes a component from the ui
   */
  protected def removeComponent(comp: Component): Unit = {
    components -= comp
  }
  
  def keyDown(key: Int): Boolean = {
    false
  }
  
  def keyTyped(key: Char): Boolean = {
    false
  }
  
  def keyUp(key: Int): Boolean = {
    false
  }
  
  def mouseMoved(x$1: Int,x$2: Int): Boolean = {
    false
  }
  
  def scrolled(x$1: Int): Boolean = {
    false
  }
  
  def touchDown(x: Int, y: Int, pointer: Int, button: Int): Boolean = {
    val point = Utils.screenCoordToGameCoord(new Vector2(x, Gdx.graphics.getHeight - y))
    for (comp <- components) {
      comp.touchDown(point)
    }
    false
  }
  
  def touchDragged(x: Int, y: Int, pointer: Int): Boolean = {
    val point = Utils.screenCoordToGameCoord(new Vector2(x, Gdx.graphics.getHeight - y))
    for (comp <- components) {
      comp.touchDrag(point)
    }
    false
  }
  
  def touchUp(x: Int, y: Int, pointer: Int, button: Int): Boolean = {
    val point = Utils.screenCoordToGameCoord(new Vector2(x, Gdx.graphics.getHeight - y))
    for (comp <- components) {
      if (comp != null)
        comp.touchUp(point)
    }
    false
  }
  
  def draw(batch: SpriteBatch) = {
    components.foreach(_.draw(batch)) //draw all the components
  }
  
}