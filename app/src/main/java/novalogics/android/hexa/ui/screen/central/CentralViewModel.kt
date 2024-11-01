package novalogics.android.hexa.ui.screen.central

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import novalogics.android.hexa.data.repository.LocalDataSource
import novalogics.android.hexa.ui.util.aiEngine.HexaAIEngine
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

    private fun reduce(currentState: CentralUiState, intent: CentralIntent): CentralUiState {
        return when (intent) {
            is CentralIntent.LoadData -> currentState.copy(isLoading = true)
            is CentralIntent.UpdateTextField -> currentState.copy(textFieldValue = intent.newValue)
            is CentralIntent.ClearError -> currentState.copy(error = null)
        }
    }

    fun handleIntent(intent: CentralIntent) {
        _uiState.value = reduce(_uiState.value, intent)

        if (intent is CentralIntent.LoadData) {
            loadData()
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            _uiState.update { ui -> ui.copy(isLoading = true) }

            //delay(1000)

            _uiState.update { ui ->
                ui.copy(
                    isLoading = false,
                   // dataAiValue = repositoryOffline.getTestData(),
                )
            }
        }
    }


    fun updateListData() {
        val data = "> "+_uiState.value.textFieldValue
        val response = hexaAi.getResponse(data)
        val action = hexaAi.getAction()
        _uiState.value = _uiState.value.copy(
            listData = data,
            actionGo = action,
            dataAiValue = response,
            textFieldValue = ""
        )
    }
}
