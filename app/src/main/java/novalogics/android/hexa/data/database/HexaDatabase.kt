package novalogics.android.hexa.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import novalogics.android.hexa.data.database.dao.CharmsDao
import novalogics.android.hexa.data.database.entity.CharmsEntity

@Database(
    entities = [CharmsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class HexaDatabase : RoomDatabase () {

    abstract fun charmsDao(): CharmsDao
}
