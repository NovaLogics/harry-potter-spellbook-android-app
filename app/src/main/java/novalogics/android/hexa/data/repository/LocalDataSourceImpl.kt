package novalogics.android.hexa.data.repository

import novalogics.android.hexa.data.model.Spell

interface LocalDataSourceImpl {
    fun getTestData(): String
    fun getListOfSpells(): List<Spell>
}
