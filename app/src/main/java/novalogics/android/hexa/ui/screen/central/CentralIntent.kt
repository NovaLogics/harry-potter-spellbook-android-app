package novalogics.android.hexa.ui.screen.central

import novalogics.android.hexa.ui.util.aiEngine.HexaActions

sealed class CentralIntent {
    data object LoadData : CentralIntent()
    data class UpdateTextField(val newValue: String) : CentralIntent()
    data class UserInputActions(val inputValue: String) : CentralIntent()
    data class DeviceManagerActions(val action: HexaActions) : CentralIntent()
    data object ClearError : CentralIntent()
}
