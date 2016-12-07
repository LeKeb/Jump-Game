package com.game.ui

import com.badlogic.gdx.InputProcessor

abstract class Ui extends InputProcessor {
  
  def keyDown(key: Int): Boolean = false
  
  def keyTyped(key: Char): Boolean = false
  
  def keyUp(key: Int): Boolean = false
  
  def mouseMoved(x$1: Int,x$2: Int): Boolean = false
  
  def scrolled(x$1: Int): Boolean = false
  
  def touchDown(x: Int, y: Int, pointer: Int, button: Int): Boolean = false
  
  def touchDragged(x: Int, y: Int, pointer: Int): Boolean = false
  
  def touchUp(x: Int, y: Int, pointer: Int, button: Int): Boolean = false
  
}