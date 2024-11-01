package novalogics.android.hexa.ui.screen.central

import novalogics.android.hexa.ui.util.aiEngine.HexaActions

data class CentralUiState(
    val isLoading: Boolean = false,
    val dataAiValue: String = "✨ Welcome to Hexa! Your magical companion awaits. ✨",
    val deviceHexaActions: HexaActions = HexaActions.NONE,
    val deviceStatus: String = "",
    val userMessage: String = "",
    val textFieldValue: String = "",
    val error: String? = null
)
