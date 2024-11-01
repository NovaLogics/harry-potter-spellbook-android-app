package novalogics.android.hexa.ui.screen.central

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import novalogics.android.hexa.data.repository.LocalDataSource
import novalogics.android.hexa.ui.util.aiEngine.HexaAIEngine
import novalogics.android.hexa.ui.util.aiEngine.HexaActions
import novalogics.android.hexa.ui.util.devicemanager.DeviceManagerUtil
import javax.inject.Inject

@HiltViewModel
class CentralViewModel @Inject constructor(
    private val repositoryOffline: LocalDataSource,
    private val hexaAi : HexaAIEngine,
    private val deviceManager: DeviceManagerUtil
) : ViewModel() {

    private val _uiState = MutableStateFlow(CentralUiState())
    val uiState: StateFlow<CentralUiState> = _uiState

    private var flashlightStatusJob: Job? = null

    private fun reduce(currentState: CentralUiState, intent: CentralIntent): CentralUiState {
        return when (intent) {
            is CentralIntent.LoadData -> currentState.copy(isLoading = true)
            is CentralIntent.UpdateTextField -> currentState.copy(textFieldValue = intent.newValue)
            is CentralIntent.UserInputActions -> currentState.copy(userMessage = formattedUserMessage())
            is CentralIntent.DeviceManagerActions -> currentState.copy(deviceHexaActions = intent.action)
            is CentralIntent.ClearError -> currentState.copy(error = null)
        }
    }

    fun handleIntent(intent: CentralIntent) {
        _uiState.value = reduce(_uiState.value, intent)

        // side-effect handling
        when (intent) {
            is CentralIntent.LoadData -> loadData()
            is CentralIntent.UserInputActions -> handleUserInputActions()
            is CentralIntent.DeviceManagerActions -> deviceManagerActions(intent.action)
            else -> {}
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.update { ui -> ui.copy(isLoading = true) }
            //delay(1000)
            _uiState.update { ui -> ui.copy(isLoading = false) }
        }
    }

    private fun formattedUserMessage(): String {
        val value = _uiState.value.textFieldValue
        return if(value.isEmpty()) ""
        else "> ${_uiState.value.textFieldValue}"
    }

    private fun handleUserInputActions() {
        val userInput = _uiState.value.textFieldValue
        if(userInput.isEmpty()) return

        val response = hexaAi.getResponse(userInput)

        handleIntent(CentralIntent.DeviceManagerActions(hexaAi.getAction()))

        _uiState.update { currentUiState ->
            currentUiState.copy(
                dataAiValue = response,
                textFieldValue = ""
            )
        }
    }

    private fun deviceManagerActions(action : HexaActions) {
        when (action) {
            HexaActions.FLASHLIGHT_ON ,
            HexaActions.FLASHLIGHT_OFF -> {
                handleFlashlightActions(action)
                handleFlashlightStatus()
            }
            else -> {
                flashlightStatusJob?.cancel()
                _uiState.update { currentUiState ->
                    currentUiState.copy(
                        deviceStatus = ""
                    )
                }
            }
        }
    }

    private fun handleFlashlightActions(action : HexaActions) {
        val isFlashOn = (action == HexaActions.FLASHLIGHT_ON)
        deviceManager.deviceFlashlight(isFlashOn)
    }

    private  fun handleFlashlightStatus() {
        flashlightStatusJob =  viewModelScope.launch {
            deviceManager.getFlashlightStatus().collect {
                isFlashlightOn ->

                val status = if(isFlashlightOn) "ON" else "OFF"

                _uiState.update { currentUiState ->
                    currentUiState.copy(
                        deviceStatus = status
                    )
                }
            }
        }

    }

}
