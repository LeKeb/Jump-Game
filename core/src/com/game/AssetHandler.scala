package com.game

import scala.collection.mutable.HashMap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.Gdx

object AssetHandler {
	
	object Texture {
		val PLAYER = "player"
		val NORMAL_PLATFORM = "normPlat"
	}
	
	object Sound {
		
	}
	
	private val textures: HashMap[String, Texture] = new HashMap[String, Texture]()
	private val sounds: HashMap[String, Sound] = new HashMap[String, Sound]()
	
	private def loadTexture(file: String) = {
		new Texture(Gdx.files.internal(file))
	}
	
	def loadAssets() = {
		(Texture.PLAYER, loadTexture("ball.png")) -> textures
		(Texture.NORMAL_PLATFORM, loadTexture("NormalPlatform.png")) -> textures
	}
	
	def getTexture(texture: String) = {
		textures.get(texture).get
	}
	
	def getSound(sound: String) = {
		sounds.get(sound).get
	}
	
}
