package com.example.avatarbuilder.models

import androidx.annotation.DrawableRes
import com.example.avatarbuilder.R

sealed class Body(@DrawableRes override val res: Int) : AvatarPart(res = res) {
    data object Default : Body(res = R.drawable.body_default)
    data object MuscleShirt : Body(res = R.drawable.body_muscle_shirt)
    data object Necklace : Body(res = R.drawable.body_necklace)
    data object Print : Body(res = R.drawable.body_print)
    data object Superhero : Body(res = R.drawable.body_superhero)
    data object Sweater : Body(res = R.drawable.body_sweater)
    data class CustomBody(@DrawableRes override val res: Int) : Body(res)
}
