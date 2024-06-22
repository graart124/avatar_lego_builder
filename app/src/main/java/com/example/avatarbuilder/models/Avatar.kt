package com.example.avatarbuilder.models

import androidx.annotation.DrawableRes

data class Avatar(
    val body: Body = Body.Default,
    val eyes: Eyes = Eyes.Normal,
    val eyebrows: Eyebrows = Eyebrows.Thick,
    val mouth: Mouth = Mouth.Line
) {
    fun containsPart(avatarPart: AvatarPart): Boolean {
        return avatarPart.res == body.res ||
                avatarPart.res == eyes.res ||
                avatarPart.res == eyebrows.res ||
                avatarPart.res == mouth.res
    }

    fun copyWith(part: AvatarPart): Avatar {
        return when (part) {
            is Body -> copy(body = part)
            is Eyes -> copy(eyes = part)
            is Eyebrows -> copy(eyebrows = part)
            is Mouth -> copy(mouth = part)
            else -> this
        }
    }
}

open class AvatarPart(@DrawableRes open val res: Int)

enum class AvatarParts() {
    Body,
    Eyes,
    Eyebrows,
    Mouth
}
