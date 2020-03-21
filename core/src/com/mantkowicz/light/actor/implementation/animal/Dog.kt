package com.mantkowicz.light.actor.implementation.animal

import com.badlogic.gdx.graphics.Texture
import com.mantkowicz.light.actor.GameBoardActor
import com.mantkowicz.light.configuration.api.PlayerConfiguration

import com.mantkowicz.light.actor.GameActorType.DOG

class Dog(configuration: PlayerConfiguration) : GameBoardActor(DOG, configuration) {

    init {

        val avatarTexture = configuration.resourcesService.assetManager.get(AVATAR_RESOURCE_NAME, Texture::class.java)
        createAvatar(avatarTexture)
    }

    override fun getDescription(): String {
        return "This is the dog that I know from the very beginning and I like him very much"
    }

    companion object {
        private val AVATAR_RESOURCE_NAME = "dog.png"
    }
}
