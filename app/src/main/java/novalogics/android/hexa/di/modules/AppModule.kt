package novalogics.android.hexa.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import novalogics.android.hexa.data.database.dao.CharmsDao
import novalogics.android.hexa.data.repository.LocalDataSource
import novalogics.android.hexa.data.repository.LocalDataSourceImpl
import novalogics.android.hexa.ui.util.aiEngine.HexaAIEngine
import novalogics.android.hexa.ui.util.devicemanager.DeviceManagerUtil
import novalogics.android.hexa.ui.util.devicemanager.DeviceManagerUtilImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApplicationContext(
        @ApplicationContext context: Context
    ): Context = context

    @Provides
    @Singleton
    fun provideRepository(
        @ApplicationContext context: Context,
        charmsDao: CharmsDao
    ): LocalDataSourceImpl {
        return LocalDataSource(
            context = context,
            charmsDao = charmsDao
        )
    }


    @Provides
    @Singleton
    fun provideHexaAIEngine(
        @ApplicationContext context: Context
    ): HexaAIEngine {
        return HexaAIEngine(
            context = context
        )
    }

    @Provides
    @Singleton
    fun provideDeviceManagerUtil(
        @ApplicationContext context: Context
    ): DeviceManagerUtilImpl {
        return DeviceManagerUtil(
            context = context
        )
    }
}
