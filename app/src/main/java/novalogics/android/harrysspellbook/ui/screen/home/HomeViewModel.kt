package novalogics.android.harrysspellbook.ui.screen.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import novalogics.android.harrysspellbook.data.repository.HomeRepositoryOffline
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repositoryOffline: HomeRepositoryOffline
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        loadDataOffline()
    }


    private fun loadDataOffline() {
        _uiState.update { ui ->
            ui.copy(
                isLoading = true,
                data = repositoryOffline.getTestData(),
            )
        }
    }
}
