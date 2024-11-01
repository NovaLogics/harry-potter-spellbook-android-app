package novalogics.android.hexa.ui.screen.central

import novalogics.android.hexa.ui.util.aiEngine.HexaActions

data class CentralUiState(
    val isLoading: Boolean = false,
    val dataAiValue: String = "✨ Welcome to Hexa! \nYour magical companion awaits. ✨",
    val deviceHexaActions: HexaActions = HexaActions.NONE,
    val deviceStatus: String = "",
    val userMessage: String = "✨ \uD83E\uDD89 ✨",
    val textFieldValue: String = "",
    val error: String? = null
)
