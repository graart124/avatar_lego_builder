package com.example.avatarbuilder.models

import androidx.annotation.DrawableRes
import com.example.avatarbuilder.R

sealed class Eyes(@DrawableRes override val res: Int) : AvatarPart(res) {
    data object Normal : Eyes(res = R.drawable.eyes_normal)
    data object Lashes : Eyes(res = R.drawable.eyes_lashes)
    data object Nice : Eyes(res = R.drawable.eyes_nice)
    data object Original : Eyes(res = R.drawable.eyes_original)
    data object Tired : Eyes(res = R.drawable.eyes_tired)
    data class CustomEyes(@DrawableRes override val res: Int) : Eyes(res)
}
