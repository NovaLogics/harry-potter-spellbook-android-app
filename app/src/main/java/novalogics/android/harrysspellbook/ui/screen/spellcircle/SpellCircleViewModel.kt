package novalogics.android.harrysspellbook.ui.screen.spellcircle

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import novalogics.android.harrysspellbook.data.repository.LocalDataSource
import javax.inject.Inject

@HiltViewModel
class SpellCircleViewModel @Inject constructor(
    private val repositoryOffline: LocalDataSource
) : ViewModel() {

    private val _uiState = MutableStateFlow(SpellCircleUiState())
    val uiState: StateFlow<SpellCircleUiState> = _uiState

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
