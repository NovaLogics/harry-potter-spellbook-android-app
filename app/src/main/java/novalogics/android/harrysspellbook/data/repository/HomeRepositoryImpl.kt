package novalogics.android.harrysspellbook.data.repository

import novalogics.android.harrysspellbook.data.model.Spell

interface HomeRepositoryImpl {
    fun getTestData(): String
    fun getJsonData(): List<Spell>
}
