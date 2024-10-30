package novalogics.android.hexa.ui.screen.central

sealed class CentralIntent {
    data object LoadData : CentralIntent()
    data class UpdateTextField(val newValue: String) : CentralIntent()
    data object ClearError : CentralIntent()
}
