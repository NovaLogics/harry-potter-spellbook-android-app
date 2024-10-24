package novalogics.android.harrysspellbook.ui.screen.spellbook

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import novalogics.android.harrysspellbook.data.repository.HomeRepositoryOffline
import javax.inject.Inject

@HiltViewModel
class SpellBookViewModel @Inject constructor(
    private val repositoryOffline: HomeRepositoryOffline
) : ViewModel() {

    private val _uiState = MutableStateFlow(SpellBookUiState())
    val uiState: StateFlow<SpellBookUiState> = _uiState

    init {
        loadDataOffline()
    }


    private fun loadDataOffline() {
        _uiState.update { ui ->
            ui.copy(
                isLoading = true,
                spellList = repositoryOffline.getJsonData(),
                data = repositoryOffline.getTestData(),
            )
        }
    }
}
