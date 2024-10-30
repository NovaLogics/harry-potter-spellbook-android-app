package novalogics.android.hexa.ui.screen.central

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import novalogics.android.hexa.data.repository.LocalDataSource
import javax.inject.Inject

@HiltViewModel
class CentralViewModel @Inject constructor(
    private val repositoryOffline: LocalDataSource
) : ViewModel() {

    private val _uiState = MutableStateFlow(CentralUiState())
    val uiState: StateFlow<CentralUiState> = _uiState

    fun reduce(currentState: CentralUiState, intent: CentralIntent): CentralUiState {
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
        // Simulate loading data
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            delay(1000) // Simulate network delay
            _uiState.value = _uiState.value.copy(isLoading = false)
        }
    }


    init {
        loadDataOffline()
    }

    private fun loadDataOffline() {
        _uiState.update { ui ->
            ui.copy(
                isLoading = false,
                data = repositoryOffline.getTestData(),
            )
        }
    }

    fun updateTextFieldValue(newValue: String) {
        _uiState.value = _uiState.value.copy(textFieldValue = newValue)
    }

    fun updateListData() {
        val data = _uiState.value.textFieldValue
        _uiState.value = _uiState.value.copy(listData = data,textFieldValue = "")
    }
}
