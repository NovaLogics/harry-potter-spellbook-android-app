package novalogics.android.hexa.ui.screen.central

data class CentralUiState(
    val isLoading: Boolean = false,
    val data: String = "",
    val listData: String = "-",
    val textFieldValue: String = "",
    val error: String? = null
)
