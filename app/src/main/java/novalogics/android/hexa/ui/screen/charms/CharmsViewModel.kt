package novalogics.android.hexa.ui.screen.charms

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import novalogics.android.hexa.data.database.dao.CharmsDao
import novalogics.android.hexa.data.database.entity.CharmsEntity
import novalogics.android.hexa.data.pref.DataStoreManager
import novalogics.android.hexa.data.repository.LocalDataSource
import javax.inject.Inject

@HiltViewModel
class CharmsViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val charmsDao: CharmsDao,
    private val localRepository: LocalDataSource
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharmsUiState())
    val uiState: StateFlow<CharmsUiState> = _uiState

    private var charmsList by mutableStateOf<List<CharmsEntity>>(emptyList())

    init {
        observeDataStore()
    }

    private fun observeDataStore() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            dataStoreManager.getJsonToRoomUpgradeState.collect {
                isRoomDataAvailable ->
                if (isRoomDataAvailable) {
                    loadCharmsFromDatabase()
                } else {
                    loadSpellsFromLocalRepository()
                }
            }
        }
    }

    private suspend fun loadSpellsFromLocalRepository() {
        withContext(Dispatchers.IO) {
            localRepository.loadListOfSpells()
            dataStoreManager.saveJsonToRoomUpgradeState(true)
        }
    }

    private fun loadCharmsFromDatabase() {
        viewModelScope.launch {
            charmsList = withContext(Dispatchers.IO) {
                charmsDao.getAllData()
            }

            _uiState.update { currentUiState ->
                currentUiState.copy(
                    isLoading = false,
                    spellList = charmsList
                )
            }
        }
    }
}
