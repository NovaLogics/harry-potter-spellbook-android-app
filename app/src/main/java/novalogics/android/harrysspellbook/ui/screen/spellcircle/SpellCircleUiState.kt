package novalogics.android.harrysspellbook.ui.screen.spellcircle

data class SpellCircleUiState(
    val isLoading: Boolean = false,
    val data: String = "",
    val listData: String = "-",
    val textFieldValue: String = "",
    val error: String? = null
)
