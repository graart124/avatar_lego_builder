package com.example.avatarbuilder.patterns

import com.example.avatarbuilder.models.AvatarPart
import com.example.avatarbuilder.models.Body
import com.example.avatarbuilder.models.Eyebrows
import com.example.avatarbuilder.models.Eyes
import com.example.avatarbuilder.models.Mouth

interface AvatarStyleFactory {
    fun createBody(): Body
    fun createEyes(): Eyes
    fun createEyebrows(): Eyebrows
    fun createMouth(): Mouth
    fun createAllStyledAvatarParts(): List<AvatarPart> {
        return listOf(
            createBody(),
            createEyes(),
            createEyebrows(),
            createMouth()
        )
    }
}

enum class Style {
    Original,
    Superhero,
    Slay,
    Horny
}

class SuperheroAvatarStyleFactory : AvatarStyleFactory {
    override fun createBody(): Body = Body.Superhero

    override fun createEyes(): Eyes = Eyes.Normal

    override fun createEyebrows(): Eyebrows = Eyebrows.Thick

    override fun createMouth(): Mouth = Mouth.OpenSmile
}

class SlayAvatarStyleFactory : AvatarStyleFactory {
    override fun createBody(): Body = Body.Necklace

    override fun createEyes(): Eyes = Eyes.Lashes

    override fun createEyebrows(): Eyebrows = Eyebrows.Round

    override fun createMouth(): Mouth = Mouth.Lips
}

class OriginalAvatarStyleFactory : AvatarStyleFactory {
    override fun createBody(): Body = Body.Print

    override fun createEyes(): Eyes = Eyes.Original

    override fun createEyebrows(): Eyebrows = Eyebrows.Thin

    override fun createMouth(): Mouth = Mouth.Original
}

class HornyAvatarStyleFactory : AvatarStyleFactory {
    override fun createBody(): Body = Body.Sweater

    override fun createEyes(): Eyes = Eyes.Tired

    override fun createEyebrows(): Eyebrows = Eyebrows.Unsure

    override fun createMouth(): Mouth = Mouth.Smile
}
