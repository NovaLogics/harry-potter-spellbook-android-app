package novalogics.android.harrysspellbook.ui.screen.spellbook

import novalogics.android.harrysspellbook.data.model.Spell

data class SpellBookUiState(
    val isLoading: Boolean = false,
    val spellList: List<Spell> = emptyList(),
    val error: String? = null
)
