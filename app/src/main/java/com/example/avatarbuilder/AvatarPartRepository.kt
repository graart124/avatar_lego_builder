package com.example.avatarbuilder

import com.example.avatarbuilder.models.*

interface AvatarPartRepository<T : AvatarPart> {
    fun createAllVariants(): List<T>
}

class BodyRepository : AvatarPartRepository<Body> {
    override fun createAllVariants() = listOf(
        Body.Default,
        Body.Print,
        Body.Sweater,
        Body.Necklace,
        Body.Superhero,
        Body.MuscleShirt
    )
}

class EyesRepository : AvatarPartRepository<Eyes> {
    override fun createAllVariants() = listOf(
        Eyes.Normal,
        Eyes.Nice,
        Eyes.Lashes,
        Eyes.Tired,
        Eyes.Original
    )
}

class EyebrowsRepository : AvatarPartRepository<Eyebrows> {
    override fun createAllVariants() = listOf(
        Eyebrows.Thick,
        Eyebrows.Thin,
        Eyebrows.Formed,
        Eyebrows.Unsure,
        Eyebrows.Round
    )
}

class MouthRepository : AvatarPartRepository<Mouth> {
    override fun createAllVariants() = listOf(
        Mouth.Line,
        Mouth.Smile,
        Mouth.Original,
        Mouth.Lips,
        Mouth.OpenSmile
    )
}
