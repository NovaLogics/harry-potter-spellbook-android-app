package novalogics.android.hexa.data.pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    // Generic method to save a string value using a key
    suspend fun saveString(key: DataStoreKey, value: String) {
        dataStore.edit { preferences ->
            preferences[key.key] = value
        }
    }

    // Generic method to retrieve a string value using a key
    fun getStringFlow(key: DataStoreKey): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[key.key]
        }
    }
}
