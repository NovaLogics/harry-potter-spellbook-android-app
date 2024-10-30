package novalogics.android.hexa.ui.screen.spellbook

import novalogics.android.hexa.data.model.Spell

data class SpellBookUiState(
    val isLoading: Boolean = false,
    val spellList: List<Spell> = emptyList(),
    val error: String? = null
)
