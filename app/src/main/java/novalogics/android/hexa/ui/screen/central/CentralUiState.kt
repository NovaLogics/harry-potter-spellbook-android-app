package novalogics.android.hexa.ui.screen.central

data class CentralUiState(
    val isLoading: Boolean = false,
    val dataAiValue: String = "Welcome to app, how is your day",
    val listData: String = "-",
    val textFieldValue: String = "",
    val error: String? = null
)
