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
		val BREAKABLE_PLATFORM = "breakPlat"
		val BREAKABLE_PLATFORM_BROKEN = "breakPlatBroken"
	}
	
	object Sound {
		val CRACK = "crack"
	}
	
	private val textures: HashMap[String, Texture] = new HashMap[String, Texture]()
	private val sounds: HashMap[String, Sound] = new HashMap[String, Sound]()
	
	private def loadTexture(file: String) = {
		new Texture(Gdx.files.internal("Textures/" + file))
	}
	
	private def loadSound(file: String) = {
		Gdx.audio.newSound(Gdx.files.internal("Sounds/" + file))
	}
	
	def loadAssets() = {
		textures += Texture.PLAYER -> loadTexture("ball.png")
		textures += Texture.NORMAL_PLATFORM -> loadTexture("Platform1.png")
		textures += Texture.BOOST_PLATFORM -> loadTexture("Platform3.png")
		textures += Texture.BREAKABLE_PLATFORM -> loadTexture("Platform2.png")
		textures += Texture.BREAKABLE_PLATFORM_BROKEN -> loadTexture("Platform2 broken.png")
		
		sounds += Sound.CRACK -> loadSound("cracking.wav")
	}
	
	def getTexture(texture: String) = {
		textures.get(texture).get
	}
	
	def getSound(sound: String) = {
		sounds.get(sound).get
	}
	
}
