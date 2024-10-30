package novalogics.android.hexa.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import novalogics.android.hexa.data.database.entity.CharmsEntity

@Dao
interface CharmsDao {

    @Insert
    suspend fun insertAll(data: List<CharmsEntity>)

    @Query("SELECT * FROM charms")
    suspend fun getAllData(): List<CharmsEntity>

    @Query("SELECT * FROM charms WHERE spell_id = :id")
    suspend fun getDataById(id: Int): CharmsEntity

}
