package novalogics.android.harrysspellbook.ui.navigation

import androidx.annotation.StringRes
import novalogics.android.harrysspellbook.R

enum class AppScreens (@StringRes val title: Int){
    Home(title = R.string.nav_home),
    SpellBook(title = R.string.nav_spell_book),
}
