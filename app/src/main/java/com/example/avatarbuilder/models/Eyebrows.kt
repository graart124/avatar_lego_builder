package com.example.avatarbuilder.models

import androidx.annotation.DrawableRes
import com.example.avatarbuilder.R

sealed class Eyebrows(@DrawableRes override val res: Int) : AvatarPart(res) {
    data object Thick : Eyebrows(res = R.drawable.eyebrows_thick)
    data object Formed : Eyebrows(res = R.drawable.eyebrows_formed)
    data object Round : Eyebrows(res = R.drawable.eyebrows_round)
    data object Thin : Eyebrows(res = R.drawable.eyebrows_thin)
    data object Unsure : Eyebrows(res = R.drawable.eyebrows_unsure)
    data class CustomEyebrows(@DrawableRes override val res: Int) : Eyebrows(res)
}
