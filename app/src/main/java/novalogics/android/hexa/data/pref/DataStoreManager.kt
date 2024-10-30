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

    companion object {
        val DATA_STORE_KEY = stringPreferencesKey("hexa_pref")
    }

    suspend fun saveExample(value: String) {
        dataStore.edit { preferences ->
            preferences[DATA_STORE_KEY] = value
        }
    }

    val stringDataFlow: Flow<String?> = dataStore.data
        .map { preferences ->
            preferences[DATA_STORE_KEY]
        }
}
