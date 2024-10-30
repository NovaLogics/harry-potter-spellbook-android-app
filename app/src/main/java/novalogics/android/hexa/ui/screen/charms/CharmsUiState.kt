package novalogics.android.hexa.ui.screen.charms

import novalogics.android.hexa.data.database.entity.CharmsEntity

data class CharmsUiState(
    val isLoading: Boolean = false,
    val spellList: List<CharmsEntity> = emptyList(),
    val error: String? = null
)
