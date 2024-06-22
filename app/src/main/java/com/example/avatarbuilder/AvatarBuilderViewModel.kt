package com.example.avatarbuilder

import androidx.lifecycle.ViewModel
import com.example.avatarbuilder.models.*
import com.example.avatarbuilder.patterns.AvatarBuilder
import com.example.avatarbuilder.patterns.FlexChecker
import com.example.avatarbuilder.patterns.FlexLevel
import com.example.avatarbuilder.patterns.HornyAvatarStyleFactory
import com.example.avatarbuilder.patterns.OriginalAvatarStyleFactory
import com.example.avatarbuilder.patterns.SlayAvatarStyleFactory
import com.example.avatarbuilder.patterns.Style
import com.example.avatarbuilder.patterns.SuperheroAvatarStyleFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class AvatarBuilderUIState(
    val avatar: Avatar = Avatar(),
    val currentAvatarPartsSection: AvatarParts = AvatarParts.Body,
    val avatarParts: List<AvatarPart> = emptyList(),
    val isStylesChecked: Boolean = false,
    val currentStyle: Style = Style.Original,
    val flexLevel: FlexLevel = FlexLevel.NotBad
)

class AvatarBuilderViewModel(
    private val bodyRepository: AvatarPartRepository<Body> = BodyRepository(),
    private val eyesRepository: AvatarPartRepository<Eyes> = EyesRepository(),
    private val eyebrowsRepository: AvatarPartRepository<Eyebrows> = EyebrowsRepository(),
    private val mouthRepository: AvatarPartRepository<Mouth> = MouthRepository(),
    private val flexChecker: FlexChecker = FlexChecker.getInstance()
) : ViewModel() {
    private val _uiState = MutableStateFlow(AvatarBuilderUIState())
    val uiState: StateFlow<AvatarBuilderUIState> = _uiState

    init {
        loadAvatarParts()
    }

    fun selectAvatarPart(avatarPart: AvatarPart) {
        val updatedAvatar = _uiState.value.avatar.copyWith(avatarPart)

        _uiState.value = _uiState.value.copy(
            avatar = updatedAvatar,
            flexLevel = flexChecker.checkAvatarFlex(updatedAvatar)
        )
    }

    fun selectAvatarPartsSection(avatarPartsSection: AvatarParts) {
        _uiState.value = _uiState.value.copy(currentAvatarPartsSection = avatarPartsSection)
        loadAvatarParts()
    }

    fun selectStyle(style: Style) {
        _uiState.value = _uiState.value.copy(currentStyle = style)
        loadAvatarParts()
    }

    private fun loadAvatarParts() {
        if (_uiState.value.isStylesChecked) {
            val styleAvatarFactory = when (_uiState.value.currentStyle) {
                Style.Original -> OriginalAvatarStyleFactory()
                Style.Superhero -> SuperheroAvatarStyleFactory()
                Style.Slay -> SlayAvatarStyleFactory()
                Style.Horny -> HornyAvatarStyleFactory()
            }

            _uiState.value = _uiState.value.copy(
                avatarParts = styleAvatarFactory.createAllStyledAvatarParts()
            )
        } else {
            val avatarPartRepository = when (_uiState.value.currentAvatarPartsSection) {
                AvatarParts.Body -> bodyRepository
                AvatarParts.Eyes -> eyesRepository
                AvatarParts.Eyebrows -> eyebrowsRepository
                AvatarParts.Mouth -> mouthRepository
            }

            _uiState.value = _uiState.value.copy(
                avatarParts = avatarPartRepository.createAllVariants()
            )
        }
    }

    fun toggleStyleChecked() {
        _uiState.value = _uiState.value.copy(isStylesChecked = !_uiState.value.isStylesChecked)
        loadAvatarParts()
    }

    fun randomizeAvatar(partsToRandomize: List<AvatarParts>) {
        val avatarBuilder = AvatarBuilder(defaultAvatar = _uiState.value.avatar)

        partsToRandomize.forEach { part ->
            when (part) {
                AvatarParts.Body -> avatarBuilder.setBody(
                    bodyRepository.createAllVariants().random()
                )

                AvatarParts.Eyes -> avatarBuilder.setEyes(
                    eyesRepository.createAllVariants().random()
                )

                AvatarParts.Eyebrows -> avatarBuilder.setEyebrows(
                    eyebrowsRepository.createAllVariants().random()
                )

                AvatarParts.Mouth -> avatarBuilder.setMouth(
                    mouthRepository.createAllVariants().random()
                )
            }
        }
        val avatar = avatarBuilder.build()
        _uiState.value = _uiState.value.copy(avatar = avatar, flexLevel = flexChecker.checkAvatarFlex(avatar))
    }
}
