package novalogics.android.harrysspellbook.ui.navigation

import androidx.annotation.StringRes
import novalogics.android.harrysspellbook.R

enum class AppScreens (@StringRes val title: Int){
    SpellCircle(title = R.string.nav_spell_circle),
    SpellBook(title = R.string.nav_spell_book),
}
