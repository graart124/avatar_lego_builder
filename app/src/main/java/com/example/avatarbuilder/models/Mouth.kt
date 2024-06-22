package com.example.avatarbuilder.models

import androidx.annotation.DrawableRes
import com.example.avatarbuilder.R

sealed class Mouth(@DrawableRes override val res: Int) : AvatarPart(res) {
    data object Line : Mouth(res = R.drawable.mouth_line)
    data object Lips : Mouth(res = R.drawable.mouth_lips)
    data object OpenSmile : Mouth(res = R.drawable.mouth_open_smile)
    data object Original : Mouth(res = R.drawable.mouth_original)
    data object Smile : Mouth(res = R.drawable.mouth_smile)
    data class CustomMouth(@DrawableRes override val res: Int) : Mouth(res)
}
