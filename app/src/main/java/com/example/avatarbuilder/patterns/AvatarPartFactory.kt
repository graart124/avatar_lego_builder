package com.example.avatarbuilder.patterns

import androidx.annotation.DrawableRes
import com.example.avatarbuilder.models.AvatarPart
import com.example.avatarbuilder.models.Body
import com.example.avatarbuilder.models.Eyebrows
import com.example.avatarbuilder.models.Eyes
import com.example.avatarbuilder.models.Mouth

interface CustomAvatarPartFactory<T : AvatarPart> {
    fun createPart(@DrawableRes res: Int): T
}

class CustomBodyFactory : CustomAvatarPartFactory<Body> {
    override fun createPart(res: Int): Body {
        return Body.CustomBody(res)
    }
}

class CustomEyesFactory : CustomAvatarPartFactory<Eyes> {
    override fun createPart(res: Int): Eyes {
        return Eyes.CustomEyes(res)
    }
}

class CustomEyebrowsFactory : CustomAvatarPartFactory<Eyebrows> {
    override fun createPart(res: Int): Eyebrows {
        return Eyebrows.CustomEyebrows(res)
    }
}

class CustomMouthFactory : CustomAvatarPartFactory<Mouth> {
    override fun createPart(res: Int): Mouth {
        return Mouth.CustomMouth(res)
    }
}
