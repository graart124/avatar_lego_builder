package com.example.avatarbuilder.patterns

import androidx.compose.ui.graphics.Color
import com.example.avatarbuilder.models.Avatar

class FlexChecker private constructor() {

    fun checkAvatarFlex(avatar: Avatar): FlexLevel {
        return listOf(
            FlexLevel.Scuff,
            FlexLevel.Cringe,
            FlexLevel.NotBad,
            FlexLevel.Slay,
            FlexLevel.Simp
        ).random()
    }

    companion object {
        @Volatile
        private var INSTANCE: FlexChecker? = null

        fun getInstance(): FlexChecker {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: FlexChecker().also { INSTANCE = it }
            }
        }
    }
}

sealed class FlexLevel(val flex: String, val color: Color) {
    data object Scuff : FlexLevel(flex = "Scuff", color = Color.Red)
    data object Cringe : FlexLevel(flex = "Cringe", color = Color.Yellow)
    data object NotBad : FlexLevel(flex = "Not bad", color = Color.Green)
    data object Slay : FlexLevel(flex = "Slay", color = Color(0xFFFFC0CB))
    data object Simp : FlexLevel(flex = "Simp", color = Color(0xFFFFA500))
}
