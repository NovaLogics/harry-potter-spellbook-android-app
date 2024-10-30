package novalogics.android.hexa.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import novalogics.android.hexa.data.database.HexaDatabase
import novalogics.android.hexa.data.database.dao.CharmsDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): HexaDatabase {
        return Room.databaseBuilder(
            context,
            HexaDatabase::class.java,
            "hexa"
        ).build()
    }

    @Provides
    fun provideYourDao(database: HexaDatabase): CharmsDao {
        return database.charmsDao()
    }
}
