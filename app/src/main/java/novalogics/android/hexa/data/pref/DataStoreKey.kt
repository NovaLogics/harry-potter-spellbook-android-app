package novalogics.android.hexa.data.pref

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey

sealed class DataStoreKey(val key: Preferences.Key<String>) {
    data object UpgradeToRoom : DataStoreKey(stringPreferencesKey("upgrade_to_room"))
    data object Username : DataStoreKey(stringPreferencesKey("username"))
}
