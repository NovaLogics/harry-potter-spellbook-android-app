package novalogics.android.hexa.ui.screen.charms

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import novalogics.android.hexa.data.database.dao.CharmsDao
import novalogics.android.hexa.data.database.entity.CharmsEntity
import novalogics.android.hexa.data.pref.DataStoreManager
import novalogics.android.hexa.data.repository.LocalDataSource
import novalogics.android.hexa.util.Constants.DELAY_2_SECONDS
import javax.inject.Inject

@HiltViewModel
class CharmsViewModel @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val charmsDao: CharmsDao,
    private val localRepository: LocalDataSource
) : ViewModel() {

    private val _uiState = MutableStateFlow(CharmsUiState())
    val uiState: StateFlow<CharmsUiState> = _uiState

    private val allData = mutableStateOf<List<CharmsEntity>>(emptyList())


    init {
        viewModelScope.launch {
            _uiState.update { ui ->
                ui.copy(isLoading = true)
            }

            dataStoreManager.getJsonToRoomUpgradeState.collect { roomDataAvailable ->

                Log.d("CharmsViewModel", "Current UpgradeToRoom state: ${roomDataAvailable}")

                if (roomDataAvailable) {
                    loadCharmsFromDb()
                } else {
                    withContext(Dispatchers.IO) {
                        localRepository.loadListOfSpells()
                    }
                    dataStoreManager.saveJsonToRoomUpgradeState(true)
                }
            }
        }
    }


    private fun loadCharmsFromDb() {
        viewModelScope.launch {
            allData.value = withContext(Dispatchers.IO) {
                charmsDao.getAllData()
            }

            _uiState.update { ui ->
                ui.copy(
                    isLoading = false,
                    spellList = allData.value
                )
            }
        }
    }

}
