package com.example.avatarbuilder.patterns

import com.example.avatarbuilder.models.Avatar
import com.example.avatarbuilder.models.Body
import com.example.avatarbuilder.models.Eyebrows
import com.example.avatarbuilder.models.Eyes
import com.example.avatarbuilder.models.Mouth

class AvatarBuilder(
    defaultAvatar: Avatar = Avatar()
) {
    private var avatar = defaultAvatar

    fun setBody(body: Body) {
        avatar = avatar.copy(body = body)
    }

    fun setEyes(eyes: Eyes) {
        avatar = avatar.copy(eyes = eyes)
    }

    fun setEyebrows(eyebrows: Eyebrows) {
        avatar = avatar.copy(eyebrows = eyebrows)
    }

    fun setMouth(mouth: Mouth) {
        avatar = avatar.copy(mouth = mouth)
    }

    fun build(): Avatar {
        return avatar
    }
}
