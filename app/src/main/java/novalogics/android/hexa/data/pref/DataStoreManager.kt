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
        val EXAMPLE_KEY = stringPreferencesKey("example_key")
    }

    suspend fun saveExample(value: String) {
        dataStore.edit { preferences ->
            preferences[EXAMPLE_KEY] = value
        }
    }

    val exampleFlow: Flow<String?> = dataStore.data
        .map { preferences ->
            preferences[EXAMPLE_KEY]
        }
}
