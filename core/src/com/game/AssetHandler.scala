package com.game

import scala.collection.mutable.HashMap
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.Gdx

object AssetHandler {
	
	object Texture {
		val PLAYER = "player"
		val NORMAL_PLATFORM = "normPlat"
		val BOOST_PLATFORM = "boostPlat"
	}
	
	object Sound {
		
	}
	
	private val textures: HashMap[String, Texture] = new HashMap[String, Texture]()
	private val sounds: HashMap[String, Sound] = new HashMap[String, Sound]()
	
	private def loadTexture(file: String) = {
		new Texture(Gdx.files.internal(file))
	}
	
	def loadAssets() = {
		textures += Texture.PLAYER -> loadTexture("ball.png")
		textures += Texture.NORMAL_PLATFORM -> loadTexture("NormalPlatform.png")
		textures += Texture.BOOST_PLATFORM -> loadTexture("BoostPlatform.png")
	}
	
	def getTexture(texture: String) = {
		textures.get(texture).get
	}
	
	def getSound(sound: String) = {
		sounds.get(sound).get
	}
	
}
