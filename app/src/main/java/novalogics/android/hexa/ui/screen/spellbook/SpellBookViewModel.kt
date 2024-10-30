package novalogics.android.hexa.ui.screen.spellbook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import novalogics.android.hexa.data.repository.LocalDataSource
import novalogics.android.hexa.util.Constants.DELAY_2_SECONDS
import javax.inject.Inject

@HiltViewModel
class SpellBookViewModel @Inject constructor(
    private val localRepository: LocalDataSource
) : ViewModel() {

    private val _uiState = MutableStateFlow(SpellBookUiState())
    val uiState: StateFlow<SpellBookUiState> = _uiState

    init {
        loadDataOffline()
    }

    private fun loadDataOffline() {
        _uiState.update { ui ->
            ui.copy(isLoading = true)
        }

        viewModelScope.launch {
            // Load data on a background thread
            val data = withContext(Dispatchers.IO) {
                localRepository.getListOfSpells()
            }

            delay(DELAY_2_SECONDS)

            // Update UI state with data and set isLoading to false
            _uiState.update { ui ->
                ui.copy(
                    isLoading = false,
                    spellList = data
                )
            }
        }
    }
}
