package novalogics.android.hexa.ui.screen.central

import novalogics.android.hexa.ui.util.aiEngine.HexaActions

data class CentralUiState(
    val isLoading: Boolean = false,
    val dataAiValue: String = "Welcome to app, how is your day",
    val actionGo: HexaActions = HexaActions.NONE,
    val listData: String = "-",
    val textFieldValue: String = "",
    val error: String? = null
)
