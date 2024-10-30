package novalogics.android.hexa.ui.screen.charms

import novalogics.android.hexa.data.model.Spell

data class CharmsUiState(
    val isLoading: Boolean = false,
    val spellList: List<Spell> = emptyList(),
    val error: String? = null
)
