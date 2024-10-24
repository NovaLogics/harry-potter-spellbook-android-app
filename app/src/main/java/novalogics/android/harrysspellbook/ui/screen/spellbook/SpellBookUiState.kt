package novalogics.android.harrysspellbook.ui.screen.spellbook

data class SpellBookUiState(
    val isLoading: Boolean = false,
    val data: String = "",
    val error: String? = null
)
