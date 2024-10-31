package novalogics.android.hexa.data.repository

import novalogics.android.hexa.data.database.entity.CharmsEntity
import novalogics.android.hexa.data.model.Spell

interface LocalDataSourceImpl {
    fun getTestData(): String
    fun getTestCharmsEntity(): CharmsEntity
    fun getListOfSpells(): List<Spell>
    suspend fun loadListOfSpells()
}
