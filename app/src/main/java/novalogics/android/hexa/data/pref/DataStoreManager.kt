package novalogics.android.hexa.data.pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    // Generic method to save a string value using a key
    suspend fun saveString(key: DataStoreStringKey, value: String) {
        dataStore.edit { preferences ->
            preferences[key.key as Preferences.Key<String>]  = value
        }
    }

    // Generic method to retrieve a string value using a key
    fun getStringFlow(key: DataStoreStringKey): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[key.key] as String
        }
    }


    val getJsonToRoomUpgradeState: Flow<Boolean> = dataStore.data
        .map { pref ->
            pref[DataStoreBooleanKey.UpgradeToRoom.key] ?: false
        }

    suspend fun saveJsonToRoomUpgradeState(value: Boolean) {
        dataStore.edit { preferences ->
            preferences[DataStoreBooleanKey.UpgradeToRoom.key] = value
        }
    }
}
