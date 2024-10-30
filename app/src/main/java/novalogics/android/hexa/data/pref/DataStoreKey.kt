package novalogics.android.hexa.data.pref

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

sealed class DataStoreStringKey(val key: Preferences.Key<String>) {
    data object Username : DataStoreStringKey(stringPreferencesKey("username"))
}

sealed class DataStoreBooleanKey(val key: Preferences.Key<Boolean>) {
    data object UpgradeToRoom : DataStoreBooleanKey(booleanPreferencesKey("upgrade_to_room"))
}
