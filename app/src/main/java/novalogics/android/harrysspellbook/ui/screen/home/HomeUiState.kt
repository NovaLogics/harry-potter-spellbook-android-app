package novalogics.android.harrysspellbook.ui.screen.home

data class HomeUiState(
    val isLoading: Boolean = false,
    val data: String? = null,
    val error: String? = null
)
