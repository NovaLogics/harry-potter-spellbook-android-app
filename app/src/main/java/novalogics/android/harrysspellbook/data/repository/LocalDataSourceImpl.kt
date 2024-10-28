package novalogics.android.harrysspellbook.data.repository

import novalogics.android.harrysspellbook.data.model.Spell

interface LocalDataSourceImpl {
    fun getTestData(): String
    fun getListOfSpells(): List<Spell>
}
