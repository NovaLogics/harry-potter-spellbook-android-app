package novalogics.android.hexa.ui.navigation

import androidx.annotation.StringRes
import novalogics.android.hexa.R

enum class AppScreens (@StringRes val title: Int){
    Central(title = R.string.nav_central),
    Charms(title = R.string.nav_charms),
}
