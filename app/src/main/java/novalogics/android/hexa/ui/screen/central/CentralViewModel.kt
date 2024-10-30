package novalogics.android.hexa.ui.screen.central

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
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
